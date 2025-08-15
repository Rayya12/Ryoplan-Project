package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.UserProfileDTO;
import RayCorp.Ryoplan.Model.Gender;
import RayCorp.Ryoplan.Model.User;
import RayCorp.Ryoplan.Repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoStoragePort storage;

    private static final long MAX_SIZE = 2L * 1024 * 1024;
    private static final List<String> ALLOWED = List.of("image/jpeg","image/png","image/webp");

    @Override
    @Transactional
        public User updateProfile(Long userId, UserProfileDTO dto) {
            User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            if (dto.getUsername() != null) u.setUsername(dto.getUsername());
            if (dto.getDob() != null) u.setDob(dto.getDob());
            if (dto.getGender() != null) u.setGender(Gender.valueOf(dto.getGender()));
            if (dto.getPhoneNumber() != null) u.setPhoneNumber(dto.getPhoneNumber());
            if (dto.getAddress() != null) u.setAddress(dto.getAddress());
            if (dto.getCountry() != null) u.setCountry(dto.getCountry());
            return userRepository.save(u);
        }

        @Override
        @Transactional
        public User updatePhoto(Long userId, MultipartFile file) throws RuntimeException {
            validateImage(file);
            User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

            // upload baru
            PhotoStoragePort.PhotoMeta meta;
            try {
                meta = storage.upload(userId, file.getOriginalFilename(), file.getContentType(), file.getBytes());
            } catch (Exception e) {
                throw new RuntimeException("Upload photo failed", e);
            }

            // hapus lama (setelah upload baru sukses)
            storage.delete(u.getPhotoKey());

            // set metadata
            u.setPhotoKey(meta.key());
            u.setPhotoUrl(meta.url());
            u.setPhotoContentType(meta.contentType());
            u.setPhotoSizeBytes(meta.size());
            u.setPhotoEtag(meta.etag());

            return userRepository.save(u);
        }

        @Override
        @Transactional
        public User updateProfileAndPhoto(Long userId,UserProfileDTO dto, MultipartFile file) {
            User u = updateProfile(userId, dto);
            if (file != null && !file.isEmpty()) {
                // panggil updatePhoto, tapi tanpa double save
                validateImage(file);
                PhotoStoragePort.PhotoMeta meta;
                try {
                    meta = storage.upload(userId, file.getOriginalFilename(), file.getContentType(), file.getBytes());
                } catch (Exception e) {
                    throw new RuntimeException("Upload photo failed", e);
                }
                storage.delete(u.getPhotoKey());
                u.setPhotoKey(meta.key());
                u.setPhotoUrl(meta.url());
                u.setPhotoContentType(meta.contentType());
                u.setPhotoSizeBytes(meta.size());
                u.setPhotoEtag(meta.etag());
                userRepository.save(u);
            }
            return u;
        }

        private void validateImage(MultipartFile f) {
            String ct = f.getContentType() == null ? "" : f.getContentType();
            if (f.getSize() > MAX_SIZE) throw new IllegalArgumentException("Ukuran foto maksimal 2MB");
            if (ALLOWED.stream().noneMatch(ct::equalsIgnoreCase)) throw new IllegalArgumentException("Hanya JPEG/PNG/WEBP");
        }
}



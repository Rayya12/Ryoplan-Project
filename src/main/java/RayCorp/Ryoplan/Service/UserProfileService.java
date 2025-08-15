package RayCorp.Ryoplan.Service;

import RayCorp.Ryoplan.DTO.UserProfileDTO;
import RayCorp.Ryoplan.Model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileService {
    User updateProfile(Long userId, UserProfileDTO dto);
    User updatePhoto(Long userId, MultipartFile file); // foto opsional endpoint terpisah
    User updateProfileAndPhoto(Long userId, UserProfileDTO dto, MultipartFile file);


}

package RayCorp.Ryoplan.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class PhotoStoragePortImpl implements PhotoStoragePort{
    private final S3Client s3;
    private final String bucket;
    private final String publicBaseUrl; // contoh: http://localhost:9000/ryoplan/

    public PhotoStoragePortImpl(
            S3Client s3,
            @Value("${app.s3.bucket}") String bucket,
            @Value("${app.s3.public-base-url}") String publicBaseUrl
    ) {
        this.s3 = s3;
        this.bucket = bucket;
        this.publicBaseUrl = publicBaseUrl.endsWith("/") ? publicBaseUrl : publicBaseUrl + "/";
    }

    @Override
    public PhotoStoragePort.PhotoMeta upload(Long userId, String originalFilename, String contentType, byte[] bytes) {
        String ext = getExt(originalFilename);
        String key = "users/%d/avatar_%d.%s".formatted(userId, System.currentTimeMillis(), ext);

        PutObjectRequest put = PutObjectRequest.builder()
                .bucket(bucket).key(key).contentType(contentType).build();
        s3.putObject(put, RequestBody.fromBytes(bytes));

        HeadObjectResponse head = s3.headObject(b->b.bucket(bucket).key(key));
        String url = publicBaseUrl + key;
        return new PhotoStoragePort.PhotoMeta(key, url, head.contentType(), head.contentLength(), head.eTag());
    }

    @Override
    public void delete(String key) {
        if (key == null || key.isBlank()) return;
        try {
            s3.deleteObject(DeleteObjectRequest.builder().bucket(bucket).key(key).build());
        } catch (S3Exception ignored) {}
    }

    private String getExt(String name) {
        if (name == null) return "jpg";
        int i = name.lastIndexOf('.');
        return (i < 0 || i == name.length()-1) ? "jpg" : name.substring(i+1).toLowerCase();
    }
}

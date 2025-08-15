package RayCorp.Ryoplan.Service;

public interface PhotoStoragePort {
    /** Upload baru, balikin metadata (key, url, contentType, size, etag). */
    PhotoMeta upload(Long userId, String originalFilename, String contentType, byte[] bytes);

    /** Hapus object lama kalo ada. */
    void delete(String key);

    record PhotoMeta(String key, String url, String contentType, long size, String etag) {}
}

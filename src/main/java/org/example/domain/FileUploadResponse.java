package org.example.domain;

/**
 * <p> 2023/5/8 </p>
 * 消息响应实体
 *
 * @author Fqq
 */
public class FileUploadResponse {
    /**
     * 文件前缀名，随机编码
     */
    private String fileName;
    /**
     * 文件下载的 uri
     */
    private String downloadUri;
    /**
     * 文件尺寸
     */
    private long size;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileUploadResponse{" +
                "fileName='" + fileName + '\'' +
                ", downloadUri='" + downloadUri + '\'' +
                ", size=" + size +
                '}';
    }
}

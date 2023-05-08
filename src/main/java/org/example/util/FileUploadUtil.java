package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p> 2023/5/8 </p>
 * 文件上传工具类
 *
 * @author Fqq
 */
public final class FileUploadUtil {
    private FileUploadUtil() {
    }

    /**
     * 保存文件方法
     *
     * @param fileName      文件名
     * @param multipartFile 文件
     * @return 被保存文件的随机字符串前缀命名
     */
    public static String saveFile(String fileName, MultipartFile multipartFile)
            throws IOException {
        // 文件上传保存路径（文件夹）
        Path uploadPath = Paths.get("Files-Upload");

        // 如果不存在则创建路径
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 获取 8 位随机的数字字母组合的字符串
        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            // 拼接生成文件路径
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            // 将文件复制保存到路径中
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return fileCode;
    }
}
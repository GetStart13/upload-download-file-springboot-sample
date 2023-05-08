package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 * <p> 2023/5/8 </p>
 * 文件下载工具类
 *
 * @author Fqq
 */
public final class FileDownloadUtil {
    private FileDownloadUtil() {
    }

    /**
     * 根据文件编码找到上传文件中保存的文件
     *
     * @param fileCode 8 位随机文件前缀名
     * @return 资源文件
     */
    public static Resource getFileAsResource(String fileCode) throws IOException {
        // 上传文件保存的文件夹
        Path dirPath = Paths.get("Files-Upload");
        // 找到的文件的路径
        AtomicReference<Path> foundFile = new AtomicReference<>();

        // 遍历文件列表
        try (Stream<Path> list = Files.list(dirPath)) {
            list
                    .filter(path -> path.getFileName().toString().startsWith(fileCode))
                    .findAny()
                    .ifPresent(foundFile::set);
        }

        if (foundFile.get() != null) {
            // 返回指定路径的资源文件
            return new UrlResource(foundFile.get().toUri());
        }

        // 如果找不到，返回空值
        return null;
    }
}

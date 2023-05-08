package org.example.controller;

import java.io.IOException;

import org.example.util.FileDownloadUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 2023/5/8 </p>
 * 文件下载接口
 *
 * @author Fqq
 */
@RestController
public class FileDownloadController {

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<Object> downloadFile(@PathVariable("fileCode") String fileCode) {
        // 遍历寻找文件的工具类对象

        Resource resource;
        try {
            // 查找文件
            resource = FileDownloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        // 如果找不到，抛出 404 异常
        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        // 如果找到，返回资源文件
        // 设置请求内容类型
        String contentType = "application/octet-stream";
        // 设置请求头值
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        // 响应
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}

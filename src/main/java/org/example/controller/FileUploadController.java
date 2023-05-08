package org.example.controller;

import java.io.IOException;
import java.util.Objects;

import org.example.domain.FileUploadResponse;
import org.example.util.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p> 2023/5/8 </p>
 * 文件上传接口
 *
 * @author Fqq
 */
@RestController
public class FileUploadController {

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {

        // 获取文件名
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename(),
                "file name cannot be null"));
        // 文件尺寸
        long size = multipartFile.getSize();

        // 保存上传文件
        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);

        // 消息返回
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + fileCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
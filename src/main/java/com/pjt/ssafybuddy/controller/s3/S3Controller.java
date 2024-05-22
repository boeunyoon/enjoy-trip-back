package com.pjt.ssafybuddy.controller.s3;

import com.pjt.ssafybuddy.service.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        File file = convertMultiPartToFile(multipartFile);
        String key = s3Service.uploadFile(file);
        String fileUrl = "https://ssafywabucket.s3.ap-northeast-2.amazonaws.com/" + key; // Construct the full URL
        return new ResponseEntity<>(fileUrl, HttpStatus.OK);
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<List<String>> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> multipartFiles) {
        List<File> files = multipartFiles.stream()
                .map(this::convertMultiPartToFile)
                .collect(Collectors.toList());
        List<String> keys = s3Service.uploadMultipleFiles(files);
        List<String> fileUrls = keys.stream()
                .map(key -> "https://ssafywabucket.s3.ap-northeast-2.amazonaws.com/" + key)
                .collect(Collectors.toList());
        return new ResponseEntity<>(fileUrls, HttpStatus.OK);
    }

    @GetMapping("/file/{key}")
    public ResponseEntity<byte[]> getFile(@PathVariable String key) {
        try {
            byte[] fileData = s3Service.downloadFile(key);
            HttpHeaders headers = new HttpHeaders();
            if (key.endsWith(".jpg") || key.endsWith(".jpeg")) {
                headers.setContentType(MediaType.IMAGE_JPEG);
            } else if (key.endsWith(".png")) {
                headers.setContentType(MediaType.IMAGE_PNG);
            } else if (key.endsWith(".gif")) {
                headers.setContentType(MediaType.IMAGE_GIF);
            } else {
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            }
            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> listAllFiles() {
        try {
            List<String> files = s3Service.listAllFiles();
            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try {
            file.transferTo(convFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to File", e);
        }
        return convFile;
    }
}

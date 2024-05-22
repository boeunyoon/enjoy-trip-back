package com.pjt.ssafybuddy.service.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private static final Logger logger = LoggerFactory.getLogger(S3Service.class);

    private final S3Client s3Client;
    private final String bucketName = "ssafywabucket";

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(File file) {
        String key = UUID.randomUUID().toString();
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
            logger.info("File uploaded to S3 with key: {}", key);
            return key;
        } catch (Exception e) {
            logger.error("Error uploading file to S3", e);
            throw new RuntimeException("Error uploading file to S3", e);
        }
    }

    public List<String> listAllFiles() {
        try {
            ListObjectsV2Request request = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .build();
            ListObjectsV2Response result = s3Client.listObjectsV2(request);
            return result.contents().stream()
                    .map(S3Object::key)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error listing files in S3 bucket", e);
            throw new RuntimeException("Error listing files in S3 bucket", e);
        }
    }

    public byte[] downloadFile(String key) throws IOException {
        Path tempFile = Files.createTempFile("s3-download-", key);
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.getObject(getObjectRequest, tempFile);
            logger.info("File downloaded from S3 with key: {}", key);
            return Files.readAllBytes(tempFile);
        } catch (Exception e) {
            logger.error("Error downloading file from S3 with key: {}", key, e);
            throw new RuntimeException("Error downloading file from S3", e);
        } finally {
            Files.deleteIfExists(tempFile); // Clean up the temporary file after reading
        }
    }

    public List<String> uploadMultipleFiles(List<File> files) {
        return files.stream()
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }
}

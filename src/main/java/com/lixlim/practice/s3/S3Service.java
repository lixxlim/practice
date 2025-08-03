package com.lixlim.practice.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final S3Properties s3Properties;

    /**
     *  Get List
     */
    public List<S3Object> getS3ObjectList(
            final String key,
            final String path
    ) {

        return null;
    }

    /**
     * Upload
     */
    public void upload(
            final String key,
            final String contentType,
            final byte[] fileBytes
    ) {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .contentType(contentType)
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
    }

    /**
     * Download
     */
    public S3Object download(
            final String key,
            final String downloadPath
    ) {

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .build();

        return s3Client.getObject(request, downloadPath);
    }
}

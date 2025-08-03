package com.lixlim.practice.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final S3Properties s3Properties;

    public List<S3Object> getS3FileObject() {

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(s3Properties.getBucket())
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        return response.contents();
    }

    public List<String> getS3FileList() {

        return getS3FileObject().stream()
                .map(S3Object::key)
                .toList();
    }

    public void uploadS3File(final MultipartFile file, final String key) throws IOException {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    public void checkFileExist(String key) {

        HeadObjectRequest request = HeadObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .build();

        s3Client.headObject(request);
    }

    public InputStream downloadS3File(final String key) {

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .build();

        return s3Client.getObject(request);
    }

    public void deleteS3File(final String key) {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .build();

        s3Client.deleteObject(request);
    }
}

package com.lixlim.practice.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/all")
    public ResponseEntity<List<String>> getFileList() {

        try {
            final List<String> list = s3Service.getS3FileList();

            return ResponseEntity.ok(list);

        } catch (Exception e) {
            log.error("Error = {}", e.getMessage(), e);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            final String key = file.getOriginalFilename();
            s3Service.uploadS3File(file, key);

            return ResponseEntity.ok("File is uploaded successfully");

        } catch (Exception e) {
            log.error("Error = {}", e.getMessage(), e);
        }

        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {

        try {
            s3Service.deleteS3File(fileName);

            return ResponseEntity.ok("File is deleted successfully");

        } catch (Exception e) {
            log.error("Error = {}", e.getMessage(), e);
        }

        return ResponseEntity.internalServerError().build();
    }
}

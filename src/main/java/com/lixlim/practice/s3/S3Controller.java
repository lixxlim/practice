package com.lixlim.practice.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    /**
     * S3 Item List
     */
    @GetMapping("/all")
    public ResponseEntity<S3ResponseBean> getS3ItemList(S3RequestBean reqBean) {

        return ResponseEntity.ok(null);
    }

    /**
     * Register S3 Item
     */
    @PostMapping
    public ResponseEntity<S3ResponseBean> postS3Item(S3RequestBean reqBean) {

        return ResponseEntity.ok(null);
    }

    /**
     * Delete S3 Item
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteS3Item(S3RequestBean reqBean) {

        return ResponseEntity.noContent().build();
    }
}

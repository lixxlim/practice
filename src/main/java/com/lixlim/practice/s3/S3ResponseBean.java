package com.lixlim.practice.s3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class S3ResponseBean {

    private List<S3Object> s3ObjectList;
}

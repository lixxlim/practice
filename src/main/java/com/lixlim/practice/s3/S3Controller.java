package com.lixlim.practice.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping
    public String getMainPage(){
        return "main";
    }

    @GetMapping
    public String getS3Page(){
        return "s3";
    }

    @GetMapping("about")
    public String getAboutPage() {
        return "about";
    }

    @GetMapping("contact")
    public String getContactPage() {
        return "contact";
    }
}

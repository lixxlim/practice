package com.lixlim.practice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class S3Contoller {

    private final S3Service s3Service;

    @GetMapping("/upload")
    public String uploadPage(Model model){

        List<String> files = s3Service.listFiles();
        model.addAttribute("files", files);
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadToS3(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) {

        try {
            s3Service.upload(file.getOriginalFilename(), file.getInputStream(), file.getSize(), file.getContentType());
            redirectAttributes.addFlashAttribute("message", "Upload Success: " + file.getOriginalFilename());

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Upload Fail: " + e.getMessage());
        }

        return "redirect:/upload";
    }

    @PostMapping("/upload/delete")
    public String deleteFile(@RequestParam("filename") String filename,
                             RedirectAttributes redirectAttributes) {

        try {
            s3Service.delete(filename);
            redirectAttributes.addFlashAttribute("message", "Delete Success: " + filename);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Delete Fail: " + e.getMessage());
        }

        return "redirect:/upload";
    }

    @GetMapping("/download")
    public String downloadPage(Model model){

        List<String> files = s3Service.listFiles();
        model.addAttribute("files", files);
        return "download";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFromS3(@PathVariable String filename) {

        ResponseBytes<GetObjectResponse> objectBytes = s3Service.download(filename);
        ByteArrayResource resource = new ByteArrayResource(objectBytes.asByteArray());

        return ResponseEntity.ok()
                .contentLength(objectBytes.asByteArray().length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

package com.github.xenlon.onlineresumebuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @PostMapping(value = "/post-data", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> sendPostData(@RequestBody Resume resume) throws IOException, InterruptedException {
        return resumeService.createPDF(resume);
    }

    @GetMapping("/download/{randomString}")
    @ResponseBody
    public ResponseEntity<Resource> fileDownload(@PathVariable String randomString) throws IOException, InterruptedException {
        ResponseEntity<Resource> file = resumeService.sendPDF(randomString);
        resumeService.deletePDF(randomString);
        return file;
    }

    @GetMapping("/home")
    public String data(){
        return "index.html";
    }
}


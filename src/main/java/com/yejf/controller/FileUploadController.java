package com.yejf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by tony on 2018/3/26.
 */
@Controller
public class FileUploadController {
    @RequestMapping("upload")
    public String toUpload(){

        return "upload";
    }

    @ResponseBody
    @RequestMapping("doUpload")
    public String doUpload(HttpServletRequest request, @RequestParam MultipartFile file){

        try {
            int dotIndex = file.getOriginalFilename().lastIndexOf(".");
            String prefix = file.getOriginalFilename().substring(0,dotIndex);
            String subfix = file.getOriginalFilename().substring(dotIndex);
            File dest  = new File(request.getServletContext().getRealPath("/uploadFiles/")
                    +prefix+new Date().getTime()+subfix);
            Files.createDirectories(dest.getParentFile().toPath());
            file.transferTo(dest);
            System.out.println(dest.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload success!";
    }

}

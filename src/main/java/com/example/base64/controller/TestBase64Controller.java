package com.example.base64.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Base64;
import java.util.HashMap;

@Controller
@RequestMapping("/test")
public class TestBase64Controller {


    @RequestMapping("/toIndex")
    public String  toIndex (){
        return "index";
    }




    @RequestMapping("/upload")
    public ModelAndView upload(@RequestParam("img1") MultipartFile file) throws Exception {

        byte[] fileBytes = file.getBytes();
        testBase64Code(fileBytes);
        String filename="D:/"+System.currentTimeMillis()+".jpg";
        file.transferTo(new File(filename));
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("url",filename);
        ModelAndView view = new ModelAndView("success");
        view.addAllObjects(hashMap);
        return view;
    }




    public void testBase64Code(byte[] fileBytes) throws Exception {

        String s = String.valueOf(fileBytes);
        String base64encodedString = Base64.getEncoder().encodeToString(fileBytes);
        System.out.println("Base64 比那么字符串 (基本) :" + base64encodedString);

        // 解码
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

        File file = new File("D:\\aaa.jpg");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(base64decodedBytes);
        outputStream.flush();
        outputStream.close();


    }

}


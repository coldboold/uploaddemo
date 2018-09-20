package com.wangchi.uploaddemo.controller;

import com.wangchi.uploaddemo.service.FileService;
import com.wangchi.uploaddemo.service.HttpTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test/uplod/")
public class UploadController {

    @Autowired
    private FileService fileService;
    @Autowired
    private HttpTest httpTest;

    @RequestMapping("upload.do")
    @ResponseBody
    public Map upload(@RequestParam(value = "file1", required = false) MultipartFile file1,
                      @RequestParam(value = "file2", required = false) MultipartFile file2,
                      String name,
                      HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file1,file2, path);

        Map fileMap = new HashMap();
        fileMap.put("uri", targetFileName);
        return fileMap;

    }

    @RequestMapping("test.do")
    @ResponseBody
    public void upload() throws IOException {
        httpTest.start("http//127.0.0.1:8080/test/uplod/upload.do","dfsdfsdfsdf","C:\\Intel\\test.jpg");

    }
}

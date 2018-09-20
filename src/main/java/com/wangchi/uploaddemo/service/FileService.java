package com.wangchi.uploaddemo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    public String upload(MultipartFile file1,MultipartFile file2, String path) {
        String fileName1 = file1.getOriginalFilename();
        String fileName2 = file2.getOriginalFilename();
        String fileExtensionName1 = fileName1.substring(fileName1.lastIndexOf(".") + 1);
        String fileExtensionName2 = fileName2.substring(fileName2.lastIndexOf(".") + 1);
        String uploadFileName1 = UUID.randomUUID().toString() + "." + fileExtensionName1;
        String uploadFileName2 = UUID.randomUUID().toString() + "." + fileExtensionName2;
        logger.info("开始上传文件，上传的文件名是: {}，上传的路径是: {}，新文件名：{}", fileName1, path, uploadFileName1);
        logger.info("开始上传文件，上传的文件名是: {}，上传的路径是: {}，新文件名：{}", fileName2, path, uploadFileName2);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile1 = new File(path, uploadFileName1);
        File targetFile2 = new File(path, uploadFileName2);

        try {
            file1.transferTo(targetFile1);
            file2.transferTo(targetFile2);

        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        return targetFile1.getName()+targetFile2.getName();
    }
}

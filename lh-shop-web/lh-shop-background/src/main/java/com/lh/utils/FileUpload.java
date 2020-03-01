package com.lh.utils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by laiHom on 2020/2/22.
 */
@Component
public class FileUpload {
    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    public  String getPath(MultipartFile file) {
        //1.获取到文件对象，将文件对象上传FastDFS上
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String path = null;
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extName, null);
            //2.把服务器的文件保存地址返回给客户端
            String fullPath = storePath.getFullPath();
            path = new StringBuilder(image_server).append(fullPath).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}

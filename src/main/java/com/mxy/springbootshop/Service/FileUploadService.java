package com.mxy.springbootshop.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@Slf4j
public class FileUploadService {

    public String testUploadFile(MultipartFile file) {

        log.info("图片："+file.getName());
        if(file.isEmpty()){
            return "error";
        }
        //得到原本的（前端传过来的）文件名
        String OriginalFilename = file.getOriginalFilename();
        log.info("文件名："+ OriginalFilename);
        //加上时间戳 新名字 = 时间戳.原文件后缀名
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //文件存放路径。现在存在本地，之后可以存在数据库中  路径的最后别忘了加两个\\ 如果不加，字符串拼接会拼到父目录里面去
        String filePath = "F:\\IDEA\\springboot-shop\\src\\main\\resources\\static\\img\\";
        String picturePath = "img/" + fileName;
        File dest = new File(filePath+fileName);
        //如果不存在该目录就创建文件夹
        if(!dest.getParentFile().exists())
            dest.getParentFile().mkdirs();
        try {
                //保存上传的文件
                file.transferTo(dest);
        }catch (Exception e){
                e.printStackTrace();
            return "error";   //抓到异常就上传失败
        }

        return fileName;
    }
}

package com.taotao.service;

import com.taotao.util.FastDFSUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${picAddress}")
    private String picAddress;

    @Override
    public String upload(MultipartFile file) throws Exception{
        String s = FastDFSUtils.uploadPic(file.getBytes(), file.getOriginalFilename());
        return picAddress+s;
    }

}

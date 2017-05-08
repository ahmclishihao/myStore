package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    /**
     * 将图片上传到服务器中
     * @param file
     * @return
     */
    String upload(MultipartFile file) throws Exception;
}

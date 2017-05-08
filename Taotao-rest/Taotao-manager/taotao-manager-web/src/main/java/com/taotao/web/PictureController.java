package com.taotao.web;

import com.taotao.common.bean.KindEditorUploadPic;
import com.taotao.common.util.JackSonUtil;
import com.taotao.service.PictureService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/pic")
public class PictureController {

    @Resource
    private PictureService mPictureService;

    @RequestMapping("/upload")
    // responseBody将会直接输出参数,不在使用视图解析
    @ResponseBody
    public String upload(@RequestParam("uploadFile") MultipartFile file){
        KindEditorUploadPic kindEditorUploadPic = new KindEditorUploadPic();
        try {
            String url = mPictureService.upload(file);
            kindEditorUploadPic.setError(0);
            kindEditorUploadPic.setUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            kindEditorUploadPic.setError(1);
            kindEditorUploadPic.setMessage("上传失败!");
        }
        return JackSonUtil.objectToJson(kindEditorUploadPic);
    }

}

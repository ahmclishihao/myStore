package com.taotao.common.bean;

/**
 * KindEditor上传文件的返回值
 */
public class KindEditorUploadPic {
    // 成功 为0 失败 为1
    private int error;
    // 成功时有效
    private String url;
    // 失败时有效
    private String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

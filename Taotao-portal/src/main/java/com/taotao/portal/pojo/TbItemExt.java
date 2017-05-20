package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

import org.apache.commons.lang3.StringUtils;

public class TbItemExt extends TbItem {

    private int cartItemNum;

    private String[] images;

    public int getCartItemNum() {
        return cartItemNum;
    }

    public void setCartItemNum(int cartItemNum) {
        this.cartItemNum = cartItemNum;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getImages() {
        String image = getImage();
        if (StringUtils.isNotBlank(image)) {
            return image.split(",");
        }
        return null;
    }

}

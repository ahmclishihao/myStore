package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

import org.apache.commons.lang3.StringUtils;

public class TbItemExt extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (StringUtils.isNotBlank(image)) {
            return image.split(",");
        }
        return null;
    }

}

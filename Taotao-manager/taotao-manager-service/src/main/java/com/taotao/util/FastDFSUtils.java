package com.taotao.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSUtils {
    private  static TrackerServer trackerServer;
    private  static TrackerClient trackerClient;
    private  static  StorageClient storageClient;

    static{
        try {
            ClientGlobal.init("./prop/resources.properties");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String uploadPic(byte[] data,String filename) throws Exception {
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        String[] retValue = storageClient.upload_file(data, extName, null);
        return retValue[0]+"/"+retValue[1];
    }


}

package com.koolearn.club.ocr;

import com.koolearn.club.ocr.request.GeneralOcrRequest;

/**
 * Created by lilong01 on 2018/5/25.
 */
public class OcrUtil {

    /**
     * 通用印刷体OCR
     */
    public static String ocrGeneral(ImageClient imageClient, String bucketName, String imageUrl) {
        String ret;
        GeneralOcrRequest request = new GeneralOcrRequest(bucketName, imageUrl);
        ret = imageClient.generalOcr(request);
        System.out.println("ocrGeneral:" + ret);
        return ret;
    }

    public static void main(String[] args){
        String appId = "1255817909";
        String secretId = "AKIDKYjmjajsG9C9hBsh2IPRuPl1ALewFyq9";
        String secretKey = "cUGrHWtzLjy3iJoDSWPC3zqYBDEAh7Pa";
        String bucketName = "kooclubsafebox-1255817909";
        String imageUrl = "http://kooclubsafebox-1255817909.picsh.myqcloud.com/1.png";

        ImageClient imageClient = new ImageClient(appId, secretId, secretKey);
        ocrGeneral(imageClient, bucketName, imageUrl);

    }
}

package com.koolearn.club.ocr;


import com.koolearn.club.ocr.request.GeneralOcrRequest;

/**
 * @author chengwu
 * Image提供给用户使用的API接口
 */

public interface Image {


    /**
     * OCR-通用印刷体识别
     */
    String generalOcr(GeneralOcrRequest request);

     
    /**
     * 关闭Image客户端连接池，释放涉及的资源，释放后，不能再使用Image的接口，必须重新生成一个新对象
     */
    void shutdown();

}

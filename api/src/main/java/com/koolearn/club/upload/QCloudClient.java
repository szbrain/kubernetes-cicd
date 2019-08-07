package com.koolearn.club.upload;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.InputStream;

/**
 * Created by lilong01 on 2018/5/25.
 */
public class QCloudClient {
    private Credentials cred;

    public QCloudClient(String secretId, String secretKey){
        cred = new Credentials(secretId, secretKey);
    }

    public String upload(String regionName, String bucketName, String key, InputStream inputStream, long length){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cosCred = new BasicCOSCredentials(cred.getSecretId(),cred.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cosCred, clientConfig);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度
        objectMetadata.setContentLength(length);
        // 设置 Content type, 默认是 application/octet-stream
        objectMetadata.setContentType("image/jpeg");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream,objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        return putObjectResult.getETag();

    }
}

package com.koolearn.club.web.webapp.util;

import com.koolearn.common.util.Base64;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by lilong01 on 2018/1/8.
 */
public class UploadUtil {
    //cos配置信息
    private static final String ACCESS_KEY = "AKIDrZ4MRJcSqKekaASibEckbBVYKq694az9";
    private static final String SECRET_KEY = "5wb3MRJxN2Vlob8OO7aolR3PUIq89vRZ";
    private static final String REGION_NAME = "ap-shanghai";
    private static final String BUCKET_NAME = "kooclub-1252392743";
    //腾讯云存储桶
    private static final String BUCKET_HOST = "https://kooclub-1252392743.cos.ap-shanghai.myqcloud.com";
    //万像优图处理API
    private static final String CLOUD_IMAGE_HOST = "http://koolearn-1251789266.image.myqcloud.com";

    /**
     * 中转文件
     * @return 响应结果
     */
    public static String upload(MultipartFile file, Map<String, Object> params) {
        final String serverUrl = params.get("serverUrl").toString();// 第三方服务器请求地址
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(serverUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, params.get("fileName").toString());// 文件流
            builder.addTextBody("type", params.get("type").toString());
            builder.addTextBody("project", params.get("project").toString());
            builder.addTextBody("appkey", params.get("appkey").toString());
            builder.addTextBody("cusdir",params.get("filePath").toString());
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     *  转存到腾讯云
     * @param urlStr 网络图片的URL
     * @param key 指定要上传到 COS 上的路径
     * @return
     */
    public static String uploadToQcloud(String urlStr , String key){
        InputStream inputStream;
        int length;
        try {
            URL url = new URL(urlStr);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            inputStream = conn.getInputStream();
            length = conn.getContentLength();
            uploadToQcloud(inputStream,key,length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String uploadToQcloud(InputStream inputStream, String key, int length){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(ACCESS_KEY, SECRET_KEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(REGION_NAME));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度
        objectMetadata.setContentLength(length);
        // 设置 Content type, 默认是 application/octet-stream
        objectMetadata.setContentType("image/jpeg");
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, inputStream,objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        return putObjectResult.getETag();

    }

    public static void main(String[] args){
/*        String url = "https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLDCoA9xGDXjFMcAkvsbRC6CQ9lQdYT4d4pbLbO8iawLrMcZL5o9GOYormmXPTaicaWHHOwicmgic5wVLA/0";
        String key = "/upload2.jpg";
        System.out.println(UploadUtil.uploadToQcloud(url,key));*/
        System.out.println(Base64.encode("http://kooclub-1252392743.picsh.myqcloud.com/ffc1de05c08a4bebb13fc5e375cb605d.jpg"));
    }


    public static void downloadFormQcloud(){
        return;
    }

}

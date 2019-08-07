package com.koolearn.club.upload;

/**
 * Created by lilong01 on 2018/5/25.
 */
public class Credentials {
    private final String secretId;
    private final String secretKey;

    public Credentials(String secretId, String secretKey){
        super();
        this.secretId = secretId;
        this.secretKey = secretKey;
    }


    public String getSecretId() {
        return secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }
}

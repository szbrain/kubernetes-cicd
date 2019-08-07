package com.koolearn.club.web.webapp.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by lilong01 on 2018/4/23.
 */
public class JWTUtil {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    /**
     * get jwt String of object
     * @param object
     *            the POJO object
     * @param maxAge
     *            the milliseconds of life time
     * @return the jwt token
     */
    public static <T> String sign(T object, long maxAge) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = Maps.newHashMap();
            String jsonString = JSON.toJSONString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }


    /**
     * get the object of jwt if not expired
     * @param jwt
     * @return POJO object
     */
    public static<T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String)claims.get(PAYLOAD);
                    return JSON.parseObject(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args){
        String token = sign(1000,100000);
        System.out.println(token);
        Integer id = unsign(token,Integer.class);
        System.out.println(id);
    }
}

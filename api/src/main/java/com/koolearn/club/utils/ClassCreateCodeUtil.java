package com.koolearn.club.utils;

/**
 * Created by lvyangjun on 2018/3/30.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class ClassCreateCodeUtil {
    private static final Logger log = LoggerFactory.getLogger(ClassCreateCodeUtil.class);

    /**
     * 自定义进制(0,1没有加入,容易与o,l混淆)
     */
    private static final char[] r = new char[]{'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h'};

    /**
     * (不能与自定义进制有重复)
     */
    private static final char b = 'o';

    /**
     * 进制长度
     */
    private static final int binLen = r.length;

    /**
     * 序列最小长度
     */
    private static final int s = 4;

    /**
     * 序列最小长度
     */
    private static final int s1 = 6;

    public static String toSerialCode() {
        return getCode(s);
    }


    public static String classCreateCode() {
        return getCode(s1);
    }

    public static String getCode(int codelenght) {
        char[] buf = new char[32];
        int charPos = 32;
        // System.out.println(num + "-->" + num % binLen);
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if (str.length() < codelenght) {
            StringBuilder sb = new StringBuilder();
            sb.append(b);
            Random rnd = new Random();
            for (int i = 1; i < codelenght - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
            }
            str += sb.toString();
        }
        log.info(str);

        return str;
    }

    public static long codeToId(String code) {
        char chs[] = code.toCharArray();
        long res = 0L;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < binLen; j++) {
                if (chs[i] == r[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == b) {
                break;
            }
            if (i > 0) {
                res = res * binLen + ind;
            } else {
                res = ind;
            }
            // System.out.println(ind + "-->" + res);
        }
        return res;
    }
}





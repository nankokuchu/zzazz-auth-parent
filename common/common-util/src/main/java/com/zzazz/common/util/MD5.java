package com.zzazz.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName: MD5
 * Package: com.zzazz.common.util
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 9:46
 * @Description: MD5
 * @Version: v1.0
 */
public class MD5 {
    public static String encrypt(String strSrc) {
        try {
            char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (byte b : bytes) {
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5する際にエラーが発生しました+" + e);
        }
    }
}

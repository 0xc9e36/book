package com.bie.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tan
 * @Title: HelpUtils
 * @Description: 助手类，一些函数
 * @blog www.golangsite.com
 * @date 26/02/1812:34
 */
public class HelpUtils {

    /**
     * md5加密
     * @param plantext
     * @return
     */
    public static String str2Md5(String plantext) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(plantext.getBytes());
        return toHex(messageDigest.digest());
    }

    /**
     * 16位byte[]转换为32位String
     * @param buffer
     * @return
     */
    private static String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        String str = "    admin";
        System.out.println(str.trim());
        try {
            System.out.println(str2Md5(str));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

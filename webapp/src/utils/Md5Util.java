package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ji Rui
 */
public class Md5Util {

    private static final String SALT = "b3779591641f358f";
    public static void main(String[] args) {
        System.out.println(stringToMd5("2000622"));
    }

    public static String doubleSalt(String string){
        return stringToMd5(stringToMd5(string));
    }

    /***
     * MD5加密,32位小写，不加盐
     * @param string 加密前的原文
     * @return  加密后的字符串
     */
    public static String stringToMd5(String string) {
        string = SALT +string+ SALT;
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}

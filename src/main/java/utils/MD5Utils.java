package utils;

import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dasun on 2017/7/29.
 */
public class MD5Utils {
    public static String toMD5(String origin){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("md5");
        }
        byte[] digest = md.digest(origin.getBytes());
        String md5String = bypeToHexString(digest);
        return md5String;
    }
    private static String bypeToHexString(byte[] digest) {
        if(digest==null)return null;
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int hi=(digest[i]>>4)&0x0f;
            int lo=digest[i]& 0x0f;
            sb.append(Character.forDigit(hi,16)).append(Character.forDigit(lo,16));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String result = toMD5("123456");
            System.out.println("result="+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

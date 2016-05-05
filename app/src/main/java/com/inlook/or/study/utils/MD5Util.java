package com.inlook.or.study.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String getMD5(String str) throws NoSuchAlgorithmException {
        final MessageDigest mDigest = MessageDigest.getInstance("MD5");
        mDigest.update(str.getBytes());
        return  bytesToHexString(mDigest.digest());
    }
    
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
          String hex = Integer.toHexString(0xFF & bytes[i]);
          if (hex.length() == 1) {
            sb.append('0');
          }
          sb.append(hex);
        }
        return sb.toString();
      }
}

package cn.lucode.fastdev.user.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
@Component
@Scope("singleton")
public class PasswordUtil {
    /**
     * 创建密码，固定字符(4个字符)+随机盐（8个字符）+（随机盐+密码）MD5加密（从第8到30截取22个字符）
     *  34 位加密后
     * @param password
     * @return
     */
    public String createPassword(String password) {
        StringBuffer sb = new StringBuffer();
        String tmp = "$#@&";
        String salt = getRandomString(8);
        sb.append(tmp).append(salt);
        String md5PassLong = getMd5(salt + password);
        if (md5PassLong != null && md5PassLong.length() > 30) {
            String md5PassShot = md5PassLong.substring(8, 30);
            sb.append(md5PassShot);
        }
        return sb.toString();
    }

    public boolean checkLogin(String password, String storedHash) {
        boolean result = false;
        if (storedHash.length() == 34) {
            String salt = storedHash.substring(4, 12);
            String storedPass = storedHash.substring(12, 34);
            String md5PassLong = getMd5(salt + password);
            if (md5PassLong != null && md5PassLong.length() > 30) {
                String md5PassShot = md5PassLong.substring(8, 30);
                if (md5PassShot.equals(storedPass)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PasswordUtil passwordUtil=new PasswordUtil();
        String pwd="1233";
        String has=passwordUtil.createPassword(pwd);
        System.out.println("加密后"+has);
        System.out.println("解密后"+passwordUtil.checkLogin(pwd,has));
    }

    private String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] md5Bytes = md.digest();
            return getHexStringFromMd5(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    private String getHexStringFromMd5(byte[] md5Bytes) {
        String res = "";
        for (int i = 0; i < md5Bytes.length; i++) {
            int temp = md5Bytes[i] & 0xFF;
            if (temp <= 0XF) {
                res += "0";
            }
            res += Integer.toHexString(temp);
        }
        return res;
    }
}

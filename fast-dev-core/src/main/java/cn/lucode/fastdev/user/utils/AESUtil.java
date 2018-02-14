package cn.lucode.fastdev.user.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
public class AESUtil {

    public static final String bm = "UTF-8";
    public static final String IV_STRING = "1234567890123456";
    // 必须16位
    public static String AUTHTOKEN_AES_KEY = "lucode123qwertgf";
    public static String encrypt(String dataPassword, String cleartext) {
        try {
            // 对密钥进行处理
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec zeroIv = new IvParameterSpec(initParam);

            byte[] enCodeFormat = dataPassword.getBytes("UTF-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, zeroIv);
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String decrypt(String dataPassword, String encrypted) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encryptedBytes = decoder.decode(encrypted);
            byte[] enCodeFormat = dataPassword.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] result = cipher.doFinal(encryptedBytes);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

}
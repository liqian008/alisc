package com.shan.yellowpages.base.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对称加密工具类
 * 使用固定盐料 获取32位对称加密后的数据(小写) 以及解密 使用方法看 main方法 错误返回 空字符串
 * Usage:
 * 加密：AesUtils.encrypt(content);
 * 解密：AesUtils.decrypt(content);
 *
 * @author swallow
 * Created by swallow on 2018/11/8.<br>
 */
public abstract class AesUtils {

    private static final Logger logger = LoggerFactory.getLogger(AesUtils.class);

    public static final String USER_TOKEN_SALT = "HLMY_USER_TOKEN_SALT";

    public static final String CLIENT_APP_SALT = "HLMY_CLIENT_APP_SALT";

    /**
     * 加密的 Salt 数值，固定值不可变更, 否则可能影响加密解密
     */
    private static final String defaultSalt = "HLMY_SYMMETRIC_SALT";

    /**
     * 对称加密, 盐使用默认值
     *
     * @param content 加密内容
     * @return 加密后的字符串
     */
    public static String encrypt(String content) {
        return encrypt(content, null);
    }

    /**
     * 对称加密
     *
     * @param content 加密内容
     * @param salt    自定义盐
     * @return 加密后的字符串
     */
    public static String encrypt(String content, String salt) {
        if (salt == null) {
            salt = defaultSalt;
        }

        return bytesToHexString(encrypt2bytes(content, salt));
    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @return 加密的bytes数组
     */
    private static byte[] encrypt2bytes(String content, String salt) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(salt.getBytes());
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            return cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 字节数组转换为十六进制字符串
     * @param src 字节数组
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();

        for (byte b : src) {
            String hex = Integer.toHexString(b & 0xFF);

            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString().toLowerCase();
    }

    public static String decrypt(String content) {
        return decrypt(content, null);
    }

    /**
     * 解密成字符串
     *
     * @param content 加密内容
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String salt) {
        if (salt == null) {
            salt = defaultSalt;
        }

        String source = null;

        try {
            source = new String(decrypt2bytes(content, salt));
        } catch (Exception e) {
            logger.error("[AesUtils] [decrypt] 解密出错 content:" + content, e);
        }

        return source;
    }

    /**
     * 解密成byte数组
     * @param content  加密字符串
     * @param salt 盐
     * @return  字节数组
     */
    private static byte[] decrypt2bytes(String content, String salt) {
        return decrypt(hexStringToBytes(content), salt);
    }

    /**
     * 十六进制字符串转换为字节数组
     * @param  hexString 十六进制字符串
     * @return 字节数组
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString.length() < 1)
            return null;

        byte[] result = new byte[hexString.length() / 2];

        for (int i = 0; i < hexString.length() / 2; i++) {
            int high = Integer.parseInt(hexString.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexString.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

    /**
     * 解密
     *
     * @param bytes 待解密内容
     * @return 解密字节数组
     */
    private static byte[] decrypt(byte[] bytes, String salt) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(salt.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 加密
            return cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        String content = "carl.zhao"; // 源数据
//        String salt = USER_TOKEN_SALT;
//
//        System.out.println("源数据 = " + content);
//        System.out.println("saly = " + salt);
//
//        // 加密
//        String encryptResult = AesUtils.encrypt(content, salt);
//        System.out.println("加密后的内容 = " + encryptResult);
//
//        // 解密
//        String source = AesUtils.decrypt(encryptResult, salt);
//        System.out.println("解密后的内容 = " + source);
//    }

}

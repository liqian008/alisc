package com.shan.yellowpages.base.utils.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DES对称加密工具类
 * @author bruce
 * @version 1.0
 */
public class KhDesUtil {

    private static final Logger logger = LoggerFactory.getLogger(KhDesUtil.class);

    // 进行加密的 key, 当前（最新）的key
    private final static String NOW_DES_KEY = "newkey";

    public static SecretKey secretKey;

    static {
        secretKey = createSecretKey(NOW_DES_KEY);
    }

    /**
     * 根据密匙进行DES加密
     *
     * @param key  密匙
     * @param info 要加密的信息
     * @return String 加密后的信息
     */
    public static String encryptToDES(SecretKey key, String info) {
        if (logger.isDebugEnabled()) {
            logger.debug("[KhDesUtil] [encryptToDES] enter, key = {}, info = {}", key, info);
        }
        // 定义 加密算法,可用 DES,DESede,Blowfish  
        //System.out.println("--encryptToDES--加密内容--"+info);
        String Algorithm = "DES";
        // 定义要生成的密文  
        byte[] cipherByte = null;
        String result = null;
        try {
            // 得到加密/解密器  
            Cipher c1 = Cipher.getInstance(Algorithm);
            // 用指定的密钥和模式初始化Cipher对象  
            // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)  
            c1.init(Cipher.ENCRYPT_MODE, key);
            // 对要加密的内容进行编码处理,  
            cipherByte = c1.doFinal(info.getBytes("UTF-8"));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("[KhDesUtil][encryptToDES] key: {},info:{},exception:{}", key, info, e);
            }
        }

        result = Base64.encodeBase64String(cipherByte);
        if (logger.isDebugEnabled()) {
            logger.debug("[KhDesUtil] [encryptToDES] result:{}, key: {},info:{}", result, key, info);
        }
        return result;
    }

    /**
     * 根据密匙进行DES解密
     *
     * @param key   密匙
     * @param sInfo 要解密的密文
     * @return String 返回解密后信息
     */
    public static String decryptByDES(SecretKey key, String sInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug("[KhDesUtil][decryptByDES] entering, key: {},sInfo:{}", key, sInfo);
        }
        // 定义 解密算法,  
        String Algorithm = "DES";
        byte[] cipherByte = null;
        String jieMiStr = null;
        try {
            // 得到加密/解密器  
            Cipher c1 = Cipher.getInstance(Algorithm);
            // 用指定的密钥和模式初始化Cipher对象  
            c1.init(Cipher.DECRYPT_MODE, key);
            // 对要解密的内容进行编码处理  
            //cipherByte = c1.doFinal(hex2byte(sInfo.getBytes()));
            //cipherByte = c1.doFinal(Base64.decode(sInfo));
            cipherByte = c1.doFinal(Base64.decodeBase64(sInfo));
            jieMiStr = new String(cipherByte, "UTF-8");
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("[KhDesUtil][decryptByDES] key: {}, sInfo:{},exception:{}", sInfo, key, e);
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("[KhDesUtil][encryptToDES] result:{}, key: {},sInfo:{}", jieMiStr, key, sInfo);
        }
        return jieMiStr;
    }

    /**
     * 创建密匙
     * @param secretKey 秘钥字符串
     * @return SecretKey 秘密（对称）密钥
     */
    private static SecretKey createSecretKey(String secretKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("[KhDesUtil][createSecretKey] entering, secretKey: {}", secretKey);
        }
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e1) {
            if (logger.isErrorEnabled()) {
                logger.error("[KhDesUtil][createSecretKey] secretKey: {},exception:{}", secretKey, e1);
            }
        }
        secureRandom.setSeed(secretKey.getBytes());
        // 声明KeyGenerator对象
        KeyGenerator keygen;
        // 声明 密钥对象
        SecretKey deskey = null;
        try {
            // 返回生成指定算法的秘密密钥的 KeyGenerator 对象
            keygen = KeyGenerator.getInstance("DES");
            keygen.init(secureRandom);
            // 生成一个密钥
            deskey = keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            if (logger.isErrorEnabled()) {
                logger.error("[KhDesUtil][createSecretKey] secretKey: {},exception:{}", secretKey, e);
            }
        }
        // 返回密匙
        return deskey;
    }

//    /**
//     * 二行制转字符串
//     */
//    public static String byte2hex(byte[] b) { // 一个字节的数，
//        if (logger.isDebugEnabled()) {
//            logger.debug("[KhDesUtil][byte2hex] entering");
//        }
//        // 转成16进制字符串
//        String hs = "";
//        String stmp = "";
//        String result = "";
//        for (int n = 0; n < b.length; n++) {
//            // 整数转成十六进制表示
//            stmp = (Integer.toHexString(b[n] & 0XFF));
//            if (stmp.length() == 1)
//                hs = hs + "0" + stmp;
//            else
//                hs = hs + stmp;
//            if (logger.isInfoEnabled()) {
//                logger.info("[KhDesUtil][byte2hex] hs:{},stmp:{}", hs, stmp);
//            }
//        }
//        result = hs.toUpperCase(); // 转成大写
//        if (logger.isInfoEnabled()) {
//            logger.info("[KhDesUtil][byte2hex] result:{}", result);
//        }
//        return result;
//    }
//
//    /**
//     * 十六进制转化为二进制
//     *
//     * @param b
//     * @return
//     */
//    public static byte[] hex2byte(byte[] b) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("[KhDesUtil][hex2byte] entering");
//        }
//        if ((b.length % 2) != 0)
//            throw new IllegalArgumentException("长度不是偶数");
//        byte[] b2 = new byte[b.length / 2];
//        for (int n = 0; n < b.length; n += 2) {
//            String item = new String(b, n, 2);
//            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
//            b2[n / 2] = (byte) Integer.parseInt(item, 16);
//        }
//
//        return b2;
//    }

}

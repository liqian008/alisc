package com.shan.yellowpages.base.utils.encrypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author swallow
 * Created by swallow on 2018/12/19.<br>
 */
public class RSAUtil {

    private static final String CHARSET = "UTF-8";

    private static final String STRING_RSA = "RSA";

    private static final String STRING_SHA1WithRSA = "SHA1WithRSA";

//    /**
//     * 公钥加密
//     */
//    public static byte[] encrypt(RSAPublicKey publicKey, byte[] encryptData) throws Exception {
//        Cipher cipher = Cipher.getInstance(STRING_RSA);
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//
//        return cipher.doFinal(encryptData);
//    }

//    /**
//     * 私钥加密
//     */
//    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] encryptData) throws Exception {
//        Cipher cipher = Cipher.getInstance(STRING_RSA);
//        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//        return cipher.doFinal(encryptData);
//    }
//
//    /**
//     * 公钥解密
//     */
//    public static byte[] decrypt(RSAPublicKey publicKey, byte[] decryptData) throws Exception {
//        Cipher cipher = Cipher.getInstance(STRING_RSA);
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//        return cipher.doFinal(decryptData);
//    }
//
//    /**
//     * 私钥解密
//     */
//    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] decryptData) throws Exception {
//        Cipher cipher = Cipher.getInstance(STRING_RSA);
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        return cipher.doFinal(decryptData);
//    }

    /**
     * 签名
     */
    public static String sign(RSAPrivateKey privateKey, String signData, String encode) throws Exception {
        Signature signature = Signature.getInstance(STRING_SHA1WithRSA);
        signature.initSign(privateKey);
        signature.update(signData.getBytes(encode));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 验签
     */
    public static Boolean check(RSAPublicKey publicKey, String sign, String signData, String encode) throws Exception {
        Signature signature = Signature.getInstance(STRING_SHA1WithRSA);
        signature.initVerify(publicKey);
        signature.update(signData.getBytes(encode));
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * 验签
     */
    public static Boolean check(String publicKey, String sign, String signData) throws Exception {
        Signature signature = Signature.getInstance(STRING_SHA1WithRSA);
        signature.initVerify(publicKey(publicKey));
        signature.update(signData.getBytes(RSAUtil.CHARSET));

        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * 加载公钥(文件)
     */
    public static RSAPublicKey publicKey(String filePath, String encode) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(RSAUtil.class.getResourceAsStream("/" + filePath.substring("classpath:".length())), encode));
        String lineValue;
        StringBuilder stringBuffer = new StringBuilder();
        while (null != (lineValue = bufferedReader.readLine())) {
            stringBuffer.append(lineValue);
        }
        bufferedReader.close();

        return publicKey(stringBuffer.toString());
    }

//    /**
//     * 加载私钥(文件)
//     */
//    public static RSAPrivateKey privateKey(String filePath, String encode) throws Exception {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(RSAUtil.class.getResourceAsStream("/" + filePath.substring("classpath:".length())), encode));
//        String lineValue = null;
//        StringBuffer stringBuffer = new StringBuffer();
//        while (null != (lineValue = bufferedReader.readLine())) {
//            stringBuffer.append(lineValue);
//        }
//        bufferedReader.close();
//        return privateKey(stringBuffer.toString());
//    }

    /**
     * 加载公钥(字符)
     */
    public static RSAPublicKey publicKey(String publicKey) throws Exception {
        byte[] byteBuffer = Base64.getDecoder().decode(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(STRING_RSA);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteBuffer);
        return (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
    }

//    /**
//     * 加载私钥(字符)
//     */
//    public static RSAPrivateKey privateKey(String privateKey) throws Exception {
//        byte[] byteBuffer = Base64.getDecoder().decode(privateKey);
//        KeyFactory keyFactory = KeyFactory.getInstance(STRING_RSA);
//        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(byteBuffer);
//        return (RSAPrivateKey) keyFactory.generatePrivate(pKCS8EncodedKeySpec);
//    }

    /**
     * 生成密钥
     */
    public static void key(String path) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(STRING_RSA);
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        BufferedWriter publicKeyWriter = new BufferedWriter(new FileWriter(path + "/public_key"));
        BufferedWriter privateKeyWriter = new BufferedWriter(new FileWriter(path + "/private_key"));
        publicKeyWriter.write(publicKeyString);
        privateKeyWriter.write(privateKeyString);
        publicKeyWriter.flush();
        privateKeyWriter.flush();
        publicKeyWriter.close();
        privateKeyWriter.close();
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy672o3cVYgr4GjRlRneqvok/94SFpeiOSwxlXrad4JOtOTf1+gV2MDC12IeMJWuvuHosnjFQNPyjxhcncQQPlqyTAvLBCfhQMhCeCwnOmmbiNEZoJ8l5sJuZ4RcbaEcQYYbFwyFvi0ViDRj7LrgRKvwyXL3iL7c5gwmDo2/GKWowjmgasDFqq1/r1VGsB0waTd+NePkji8Q3nwPC4YhV4Hwb4QvzGImjYxXgkkOaKL9wt7JhEcD+1qpIiiPjDkqRSeI/iv2ZqjUI940fJtu4YP+5BkNY83HbJK0Dn58P0kL22mw2ppkpjo7As6UHuZea2sIAkXZ2jNSTwS/h9cEwwwIDAQAB";
        String sign = "twXB+VbRrU8yt6jCstAenujH1zFxC8kV8TswCs4BdivteNsHHplDGDFTEx3gtClHxpIn9rPPj6eSowDD/PGlAgkM4cHHqIaaFNqHe30N/nJNrEoOfJu2pdbi+JsPhXzzwInqJLsZJ3ADHx9K9Vm3+Yx+bzeeIgqdy9zXVh3ShGjdO+0nZ6wubjKT2sPEId1+v/nVzWUfl9SpeG6EN1ZR6CfcFPe2KLuw6G4CFyQEnDtGlY8nDZClu6H24TExO7kLs4B7VrJmbOA5IX1lvw7vZUrLqju/R+PEUeyEU3Asm2KXm/t7+qR1HBbdSV/IflbF7auceCSCAUaWLr6YM3Goqw==";

        String signData = "{\"orderId\":\"GPA.3395-9619-3438-05380\",\"packageName\":\"cn.hlmy.wxgame.dnxj\",\"productId\":\"dnxj_item_1\",\"purchaseTime\":1566883496565,\"purchaseState\":0,\"developerPayload\":\"F8Z54269869BY6\",\"purchaseToken\":\"gencddjbiokeomknmccdinko.AO-J1OyMxSqIIjiFulNDdaBkYLHVv3HGd8QggYuCTBbpE9FKLDPHmXqnQ6sPmkAByRg5sPN0eJAD8Jr0Cy_9IuRgLmwrGMv1kpU8gp4XT3VU7kDYh10njZMM5nJudbMDBGZbofm0YtMB\"}";
        Boolean result = check(publicKey, sign, signData);
        System.out.println("result = " + result);

    }

//    public static void main(String[] args) throws Exception {
//        RSAPublicKey key = publicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpzyOBY5MyHjCITZkvfjj+uHBQyToGJiWX6qF892rQ8Ybsin/JAKDde1cwnlKKVQdBu7Ev6k3yKNUDWMLyP8SUa6Pzu5yNiRaStXS2PcKKDr+xqXInGurh0IWsB9o1KtrTMqaWN0Oy2lAEpWZnQ3UAsvKfu33SB0wQgfB6JVMODQIDAQAB");
//        //RSAPrivateKey keyd = privateKey("classpath:private_key",CHARSET );
//        String str = "attach=EBS47722427H5R&time_end=1545196537373";//map.toString();
//        // String sign = RSAUtil.sign(keyd, str, CHARSET);
//        String sign="eyHheQIrexqmy8Yv3AsnxgUmtRO5 iKQVhd GtVBDWQRQNch5BVhYPk54ufYTnY0Qr6psD50uVOGTE/0vMCU1xaCBZi3m jhXvEJZJCr6jc8mllZp5Gmshmg7OVlTMHr JDncVBMbuA9otxf0dUztOCX3KV/xQPXzIkhz1sACAY=";
//        //System.out.println("签名:" + sign);
//        Boolean check = RSAUtil.check(key, sign, str, RSAUtil.CHARSET);
//        System.out.println("校验签名:" + check);
//    }

}

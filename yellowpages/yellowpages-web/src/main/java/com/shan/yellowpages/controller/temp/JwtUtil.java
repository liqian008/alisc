package com.shan.yellowpages.controller.temp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Milogenius
 * @create: 2019-07-08 10:24
 * @description:
 **/
public class JwtUtil {

    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    /**
     * 生成签名,15分钟后过期
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username, String userId){
        //过期时间
        Date date = null;
        //new Date(System.currentTimeMillis());
//        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header).withClaim("loginName",username)
                .withClaim("userId",userId).withExpiresAt(date).sign(algorithm);


//        return JWT.create().withHeader(header).withAudience(username).withExpiresAt(date).sign(algorithm);
    }


    public static boolean verity(String token){
        try {

            DecodedJWT decode = JWT.decode(token);
            System.out.println("subject: " + decode.getSubject());
            System.out.println("audience: " + decode.getAudience());
            System.out.println("payload: " + decode.getPayload());
            System.out.println("header: " + decode.getHeader());
            System.out.println("expireAt: " + decode.getExpiresAt());
            System.out.println("sign: " + decode.getSignature());
            System.out.println("token: " + decode.getToken());
            System.out.println("claims: " + decode.getClaims());
            System.out.println("algo: " + decode.getAlgorithm());

//            System.err.println(decode.getAudience().get(0));

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map map  =  jwt.getClaims();
            List<String> x = jwt.getAudience();

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }

    }
}
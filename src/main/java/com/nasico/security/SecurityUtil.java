package com.nasico.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by 喵吉 on 2017/11/27.
 */
public class SecurityUtil {

    private static final  String KEY_MAC = "HmacMD5" ;

    public static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encode(key);
    }

    public static byte[] decryptBASE64(String key) throws IOException {
        return  (new BASE64Decoder()).decodeBuffer(key);
    }


    public static byte[] encuryptByAlgorithm(byte[] data,String algorithm) throws NoSuchAlgorithmException {
        //algorithm参数参照 https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#MessageDigest
        MessageDigest md5 = MessageDigest.getInstance(algorithm);
        md5.update(data);

        return md5.digest();
        //通常将MD5产生的字节数组交给BASE64再加密一把，得到相应的字符串。
    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("");

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }


}

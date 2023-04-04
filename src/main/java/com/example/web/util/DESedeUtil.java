package com.example.web.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * 3DES加密算法，主要用于加密用户id，身份证号等敏感信息,防止破解
 */
public class DESedeUtil {

    //秘钥
    public static final String  KEY = "~@#$y1a2n.&@+n@$%*(1)";
    //秘钥长度
    private static final int secretKeyLength = 24;
    //加密算法
    private static final String  ALGORITHM = "DESede";
    //编码
    private static final String CHARSET = "UTF-8";

    /**
     * 转换成十六进制字符串
     * @param key
     * @return
     */
    public static byte[] getHex(String key){
        byte[] secretKeyByte = new byte[24];
        byte[] hexByte;
        hexByte = DigestUtils.md5DigestAsHex(key.getBytes()).getBytes();
        //秘钥长度固定为24位
        System.arraycopy(hexByte,0,secretKeyByte,0,secretKeyLength);
        return secretKeyByte;
    }

    /**
     * 生成密钥，返回加密串
     * @param key 密钥
     * @param encodeStr 将加密的字符串
     * @return
     */
    public static String  encode3DES(String key,String encodeStr){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getHex(key), ALGORITHM));
            return Base64.encodeBase64String(cipher.doFinal(encodeStr.getBytes(CHARSET)));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 生成密钥,解密，并返回字符串
     * @param key 密钥
     * @param decodeStr 需要解密的字符串
     * @return
     */
    public static String decode3DES(String key, String decodeStr){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getHex(key),ALGORITHM));
            return new String(cipher.doFinal(new Base64().decode(decodeStr)),CHARSET);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String userId = "root";
        String encode = DESedeUtil.encode3DES(KEY, userId);
        String decode = DESedeUtil.decode3DES(KEY, encode);
        System.out.println("用户id>>>"+userId);
        System.out.println("用户id加密>>>"+encode);
        System.out.println("用户id解密>>>"+decode);
    }

}

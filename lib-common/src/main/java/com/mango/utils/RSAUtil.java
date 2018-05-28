package com.mango.utils;

import android.util.Base64;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class RSAUtil {

    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    public static final String RSA = "RSA";
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFyTMpZoEhwTVq/UDfF3vsorizl9BVyBw8SI06mWkujG8Fm8TAgZ0geBOqbTXfQiP09xchC8dxsVwRTNCV09wiK3glFmSVxOGglpuos2iyVqWfWXk8Q3OxcHCmro2OZYlv2zuLa6t2uu5pfhfzNKUAdw7ME4VRhM5s//pgchR+CwIDAQAB";

    /**
     * 使用公钥加密
     */
    public static byte[] encryptByPublicKey(String data, String publicKey) throws Exception {
        // 得到公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        byte[] publicKeyBytes = Base64.decode(publicKey, Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        // 加密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.ENCRYPT_MODE, pubKey);
        return cp.doFinal(data.getBytes());
    }

    /**
     * 使用私钥解密
     */
    public static byte[] decryptByPrivateKey(byte[] encrypted, String privateKey) throws Exception {

        byte[] privateKeyBytes = Base64.decode(privateKey, Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PrivateKey keyPrivate = kf.generatePrivate(keySpec);
        // 解密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.DECRYPT_MODE, keyPrivate);
        return cp.doFinal(encrypted);
    }

    public static String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}

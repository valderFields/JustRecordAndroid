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
    public static final String mi = "gvjkfeq";
    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    public static final String RSA = "RSA";
    //public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFyTMpZoEhwTVq/UDfF3vsorizl9BVyBw8SI06mWkujG8Fm8TAgZ0geBOqbTXfQiP09xchC8dxsVwRTNCV09wiK3glFmSVxOGglpuos2iyVqWfWXk8Q3OxcHCmro2OZYlv2zuLa6t2uu5pfhfzNKUAdw7ME4VRhM5s//pgchR+CwIDAQAB";
    //public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFyTMpZoEhwTVq/UDfF3vsorizl9BVyBw8SI06mWkujG8Fm8TAgZ0geBOqbTXfQiP09xchC8dxsVwRTNCV09wiK3glFmSVxOGglpuos2iyVqWfWXk8Q3OxcHCmro2OZYlv2zuLa6t2uu5pfhfzNKUAdw7ME4VRhM5s//pgchR+CwIDAQAB";
    //public static final String PRIVATE_KEY = "MIICXgIBAAKBgQDFyTMpZoEhwTVq/UDfF3vsorizl9BVyBw8SI06mWkujG8Fm8TAgZ0geBOqbTXfQiP09xchC8dxsVwRTNCV09wiK3glFmSVxOGglpuos2iyVqWfWXk8Q3OxcHCmro2OZYlv2zuLa6t2uu5pfhfzNKUAdw7ME4VRhM5s//pgchR+CwIDAQABAoGBAKvl7UhbJXov0Px2tW3vnC4TUtIL7O7RJ/Eg4VX/xVHGjitisYLaY7S3h+gEWOD8PAIIyppXAXWHv0vt5g+B/XxRXlN7MCreuoW4Vrr1T2Pt1a24Zv9vnbx95XJsefPEugJqow0BfX+ZlUsKjl8Xcfr1+vTO6hW29IADSnWSxxjZAkEA7m0Qu8oQhXjrx8wEPYLJKbyhTjEI8t3gFtS10Xq8XFNtVdzzIXrcBdI04prTGOYfBc8mZy1zo2OdiWwh3v4XBwJBANRdSKvv20f4HQ/e8wBFoNhVyrHKItOLRxxuSgi1Y3SpOq0l28D0J0aea7N/D0OTQW0fghrJPbz78hgHz6w0O90CQQCgt7JM89OZNT6yiordL9kMIlQVkiY3GGsi2LXLBFIOZkpQU8Usb1X7Moxlp/neUdjdoJqBeHj3zYF3DgXy/BJnAkB22OHjv29j3po9GJO8EjuK9rBiQXKuGyMn6R0rtH+9eTvqTOaoHLuiTOSPZstfERkrfdetL/SKQCHjivORNwZNAkEAiVCPlgZ1v9B817ieFl3XJruTcH62OpVyvS6P0rO5tZOLNc2xNtTDDNI2WYyy9sv1bx/F/VhzCUpMdZuCafSogw==";

    // public static final String PRIVATE_KEY = "MIICXgIBAAKBgQDFyTMpZoEhwTVq/UDfF3vsorizl9BVyBw8SI06mWkujG8Fm8TAgZ0geBOqbTXfQiP09xchC8dxsVwRTNCV09wiK3glFmSVxOGglpuos2iyVqWfWXk8Q3OxcHCmro2OZYlv2zuLa6t2uu5pfhfzNKUAdw7ME4VRhM5s//pgchR+CwIDAQABAoGBAKvl7UhbJXov0Px2tW3vnC4TUtIL7O7RJ/Eg4VX/xVHGjitisYLaY7S3h+gEWOD8PAIIyppXAXWHv0vt5g+B/XxRXlN7MCreuoW4Vrr1T2Pt1a24Zv9vnbx95XJsefPEugJqow0BfX+ZlUsKjl8Xcfr1+vTO6hW29IADSnWSxxjZAkEA7m0Qu8oQhXjrx8wEPYLJKbyhTjEI8t3gFtS10Xq8XFNtVdzzIXrcBdI04prTGOYfBc8mZy1zo2OdiWwh3v4XBwJBANRdSKvv20f4HQ/e8wBFoNhVyrHKItOLRxxuSgi1Y3SpOq0l28D0J0aea7N/D0OTQW0fghrJPbz78hgHz6w0O90CQQCgt7JM89OZNT6yiordL9kMIlQVkiY3GGsi2LXLBFIOZkpQU8Usb1X7Moxlp/neUdjdoJqBeHj3zYF3DgXy/BJnAkB22OHjv29j3po9GJO8EjuK9rBiQXKuGyMn6R0rtH+9eTvqTOaoHLuiTOSPZstfERkrfdetL/SKQCHjivORNwZNAkEAiVCPlgZ1v9B817ieFl3XJruTcH62OpVyvS6P0rO5tZOLNc2xNtTDDNI2WYyy9sv1bx/F/VhzCUpMdZuCafSogw==";

    public static final String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJhTpp7D3dUefLEbpyHJFugd+3" +
            "Db8VZsvNNnDDcjyOXbB2OTpFVo9Z2+tcHHbQ57Mde9ITaAs2IGQYB4nMJ6HLJvOc" +
            "/g1KNEdRa2AKDIBE1S5Lamg1hD+zmEQz26QDPVzGX2xWzkoPLC+UrLyWDMHytlk/" +
            "b9DV8AdxTkzgYX/CNwIDAQAB";

    public static final String private_key = "MIICWwIBAAKBgQCJhTpp7D3dUefLEbpyHJFugd+3Db8VZsvNNnDDcjyOXbB2OTpF" +
            "Vo9Z2+tcHHbQ57Mde9ITaAs2IGQYB4nMJ6HLJvOc/g1KNEdRa2AKDIBE1S5Lamg1" +
            "hD+zmEQz26QDPVzGX2xWzkoPLC+UrLyWDMHytlk/b9DV8AdxTkzgYX/CNwIDAQAB" +
            "AoGANDc1+soMo2BaFqzgkjturPr7KtI5X7LsZzrojg1uGNBSFFGeYn1/aKssLolQ" +
            "Q/n19JfzKBM1TpP17XnPRIk30Q/Pe8WVy6QyvZ1TItojPGtHVqeQFcwwl//5KvTr" +
            "h5X8i+pvEqWlMzUOZKVu7AUMnvnI1b4hGKAcFyoQT2k/MsECQQDSE5VRBYLmzFNd" +
            "qU2HIEuLc7Mw2rhSAzZejwHb0Pn1/LN777fPYmujtyOgbprXk07AX9t5LDb55vF9" +
            "93VT8BZJAkEAp5U4zJO4YrCX6pAtGMwXlyLkcpnSAWgbcehwcekL+qsnmRinga8h" +
            "OdsQGSXtLPIGXn/gah/hfXcME0Agwm8UfwJAeDH+N8o4eEcl16v7kLm4n4RGXUh3" +
            "N21hGT8naBasPcMlCl9AwuZkAdrSBoPiEj/VShpOX4kdt2Qcfd8tASaYCQJAfLij" +
            "ICZkmRaCybDpz0W93N68FMqEMEXR/zGV1kEyiCmve9KMgAjd+pgd4AmI/eVWdihH" +
            "1dPKVz0tgHo+p1ZScwJAUxBp9tcbT2cCjD6vproxdyhjBu6q5pnq5dUWoFiSpExP" +
            "atuMQ80IBlveTFvKQm2HNzm5RcEwoH81UxgHc/Fhmg==";


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

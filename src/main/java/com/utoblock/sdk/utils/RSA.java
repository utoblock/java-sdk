
package com.utoblock.sdk.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSA {

    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * RSA加密
     *
     * @param content      加密内容
     * @param pubKey       公钥
     * @param inputCharset
     * @return
     */
    public static String encrypt(String content, String pubKey, String inputCharset) {
        try {
            byte[] data = content.getBytes(inputCharset);
            int inputLen = data.length;
            int maxBlockSize = 245;

            RSAPublicKey rsaPublicKey = new RSAPublicKeyImpl(Base64.decode(pubKey));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            for (int remainLength = inputLen; remainLength > 0; remainLength = inputLen - offSet) {
                byte[] cache = cipher.doFinal(data, offSet, Math.min(remainLength, maxBlockSize));
                out.write(cache, 0, cache.length);
                offSet += maxBlockSize;
            }

            byte[] encryptBytes = out.toByteArray();
            return Base64.encode(encryptBytes).replaceAll("\\n", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * RSA解密
     *
     * @param content
     * @param private_key
     * @param input_charset
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

    public static String sign(String data, String privateKey, String charset) throws NoSuchAlgorithmException, Base64DecodingException, InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(getPrivateKey(privateKey));
        signature.update(data.getBytes(charset));
        byte[] result = signature.sign();
        return Base64.encode(result);
    }

    public static PrivateKey getPrivateKey(String key) throws Base64DecodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey(String key) throws Base64DecodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }
}

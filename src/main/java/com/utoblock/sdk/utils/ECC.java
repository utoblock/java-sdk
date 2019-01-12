package com.utoblock.sdk.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.*;

/**
 * 描述：ECC签名工具
 * 创建人：yoan
 * 创建日期：2018/11/12
 */
public class ECC {

    /**
     * 生成签名
     *
     * @param data
     * @param priKey
     * @param charset
     * @return
     * @throws Exception
     */
    public static String sign(String data, String priKey, String charset) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(KeyFactory.getInstance("EC").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(priKey))));
        signature.update(data.getBytes());
        byte[] signBytes = signature.sign();
        return Base64.encode(signBytes);
    }

    /**
     * 校验签名
     *
     * @param data
     * @param pubKey
     * @param charset
     * @return
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static boolean verifySign(String data, String sign, String pubKey, String charset) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initVerify(KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(Base64.decode(pubKey))));
        signature.update(data.getBytes());
        return signature.verify(Base64.decode(sign));
    }

    public static void main(String[] args) throws Exception {
        String pubKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEOOGEPf3RF2Hgp9SyhGH4u7VEHRF+fxKHg9ObiTx1g3CKid4yuQ5zGv7eE9FtaCbWjNrX8jb0xNrNOdy0R/v+SQ==";
        String data = "extras=%E4%BD%A0%E5%A5%BD%E5%95%8A&mobile=13710966390&notifyId=41093054984381ferpp5ub&timestamp=1543994192219&tradeNo=3&value=0.01";
        String sign = "MEUCIF+Nr2QHDjgaLdKcz5mZOC0huS4zQ9dxGG7L4OvP2+lPAiEAlfbCNqsNWaoz2XWh8jRPUZc1bvIpvQNLJavxR9/Mx18=";
        boolean result = verifySign(data, sign, pubKey, "utf-8");
        System.out.println(result);
    }
}

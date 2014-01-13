package cn.mixpay.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignatureUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class.getName());

    private static final String CHARSET_UTF8 = "UTF-8";

    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_MD5 = "MD5";

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * @param algorithm
     * @return
     * @throws java.security.NoSuchAlgorithmException
     *
     */
    private static PublicKey getPublicKeyFromX509(String algorithm,String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodedKey = Base64.decode(bysKey);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    public static String encrypt(String content, String key) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM_RSA, key);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);

            byte plaintext[] = content.getBytes(CHARSET_UTF8);
            byte[] output = cipher.doFinal(plaintext);

            String s = new String(Base64.encode(output));

            return s;

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }


    /**
     *
     * @param content 需签名的内容
     * @param privateKey 私钥
     * @return
     */
    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(CHARSET_UTF8));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return null;
    }

    public static String getMD5(String content) {
        String s = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(ALGORITHM_MD5);
            md.update(content.getBytes());
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return s;
    }

    /**
     *
     * @param content 待验证信息内容
     * @param sign 签名
     * @param publicKey 公钥
     * @return
     */
    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(CHARSET_UTF8));
            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return false;
    }
}

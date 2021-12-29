package com.yunlu.log.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/29
 **/
@Component
public class AESUtil {
    private static final String AES_ALGORITHM_TYPE = "AES/ECB/PKCS5Padding";

    public static String decrypt(String content) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
            String secretKey = properties.getProperty("secretKey");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher aes = Cipher.getInstance(AES_ALGORITHM_TYPE);
            aes.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] base64 = Base64.decode(content);
            byte[] bytes = aes.doFinal(base64);
            return new String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

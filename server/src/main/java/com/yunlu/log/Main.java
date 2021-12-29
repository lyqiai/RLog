package com.river.tcp;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/12/29
 **/
public class Main {
    public static void main(String[] args) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec("1234567890123456".getBytes(), "AES"));
        File file = new File("I:\\20211229");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);
        CipherOutputStream cos = new CipherOutputStream(fos, cipher);
        cos.write("hello world".getBytes());
        fos.close();
        cos.close();

        FileInputStream fis = new FileInputStream(file);
        CipherInputStream cis = new CipherInputStream(fis, cipher);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int i;
        while ((i = cis.read(bytes)) > 0) {
            bos.write(bytes, 0, i);
        }
        System.out.println(bos.toString());
        fis.close();
        bos.close();
    }
}

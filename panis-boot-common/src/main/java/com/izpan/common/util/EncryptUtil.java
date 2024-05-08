package com.izpan.common.util;

import com.izpan.common.pool.StringPools;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * 加解密工具类
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.common.util.EncryptUtil
 * @CreateTime 2023/8/7 - 23:13
 */
@SuppressWarnings({"java:S5542"})
@Slf4j
public class EncryptUtil {


    // =================================== AES Begin ===================================

    // 加密算法
    private static final String ALGORITHM = "AES";
    // 初始向量长度
    private static final int IV_LENGTH = 16;
    // 加密实例（加密算法/解密模式/填充方式）
    private static final String CIPHER_NAME = "AES/CBC/PKCS5PADDING";

    private EncryptUtil() {

    }

    /**
     * 使用AES算法对明文进行加密
     *
     * @param key       密钥
     * @param plaintext 明文
     * @return {@link String} 加密后的密文
     * @author payne.zhuang
     * @CreateTime 2023-08-08 16:11
     */
    public static String aesEncrypt(String key, String plaintext) {
        try {
            // 将密钥转换为字节数组
            byte[] rawKey = key.getBytes(StandardCharsets.UTF_8);
            // 使用密钥字节数组生成SecretKey对象
            SecretKey secretKey = new SecretKeySpec(rawKey, ALGORITHM);
            // 生成随机的初始向量
            byte[] iv = new byte[IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            // 创建Cipher对象，指定加密算法/解密模式/填充方式
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);

            // 创建IvParameterSpec对象，包含初始向量
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // 初始化Cipher对象，设置为加密模式，指定密钥和初始向量
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            // 加密明文
            byte[] encryptedData = cipher.doFinal((plaintext.getBytes()));
            // 对密文和向量进行Base64编码，并拼接在一起返回
            String encryptedDataInBase64 = Base64.getEncoder().encodeToString(encryptedData);
            String ivInBase64 = Base64.getEncoder().encodeToString(iv);
            return encryptedDataInBase64 + StringPools.COLON + ivInBase64;
            // 密钥无效时抛出此异常, 加密参数无效时抛出此异常, 当输入的明文长度不是块大小的倍数时抛出此异常,
            // 如果填充的数据无效时抛出此异常, 当指定的加密算法无效时抛出此异常, 当指定的填充机制无效时抛出此异常
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException exception) {
            log.error(exception.getMessage());
            throw new AESException("AES encryption failed", exception);
        }
    }

    /**
     * 使用AES算法对密文进行解密
     *
     * @param key        密钥
     * @param ciphertext 密文
     * @return {@link String} 解密后的明文
     * @author payne.zhuang
     * @CreateTime 2023-08-08 16:10
     */
    public static String aesDecrypt(String key, String ciphertext) {
        try {
            // 将密文拆分成加密的数据和向量
            String[] parts = ciphertext.split(StringPools.COLON);
            // 对向量进行解码
            IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));

            // 将密钥转换为字节数组
            byte[] rawKey = key.getBytes(StandardCharsets.UTF_8);
            // 使用密钥字节数组生成SecretKey对象
            SecretKey secretKey = new SecretKeySpec(rawKey, ALGORITHM);

            // 创建Cipher对象，指定加密算法/解密模式/填充方式
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);

            // 初始化Cipher对象，设置为加密模式，指定密钥和初始向量
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            // 对密文进行解密
            byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);
            byte[] original = cipher.doFinal(decodedEncryptedData);
            // 返回解密后的明文
            return new String(original);
            // 密钥无效时抛出此异常, 加密参数无效时抛出此异常, 当输入的明文长度不是块大小的倍数时抛出此异常,
            // 如果填充的数据无效时抛出此异常, 当指定的加密算法无效时抛出此异常, 当指定的填充机制无效时抛出此异常
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException exception) {
            log.error(exception.getMessage());
            throw new AESException("AES decryption failed", exception);
        }
    }

    public static class AESException extends RuntimeException {

        @Serial
        private static final long serialVersionUID = 812146906903488985L;

        public AESException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // =================================== AES End ===================================
}

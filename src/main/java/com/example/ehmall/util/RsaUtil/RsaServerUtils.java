package com.example.ehmall.util.RsaUtil;
/**
 * 对称加密服务接口
 * @author 施立豪
 */
public interface RsaServerUtils {

    /**
     * 利用RSA算法将明文加密
     *
     * @param plainText 明文(字符串)
     * @param publicKey 公钥
     * @return 密文(byte数组)
     */
    byte[] encrypt(String plainText, String publicKey);
}

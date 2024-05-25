package com.payc.tool.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    public static final String encode(String str) throws NoSuchAlgorithmException {
        String encode = str;
        StringBuilder stringbuilder = new StringBuilder();

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(encode.getBytes());
        byte[] str_encoded = md5.digest();
        for (int i = 0; i < str_encoded.length; i++) {
            if ((str_encoded[i] & 0xff) < 0x10) {
                stringbuilder.append("0");
            }
            stringbuilder.append(Long.toString(str_encoded[i] & 0xff, 16));
        }
        return stringbuilder.toString();
    }
//
//	/**
//	 * 利用MD5进行加密
//	 *
//	 * @param str
//	 *            待加密的字符串
//	 * @return 加密后的字符串
//	 * @throws NoSuchAlgorithmException
//	 * @throws UnsupportedEncodingException
//	 */
//	public static final String EncoderByMd5(String str) {
//		if (str == null) {
//			return null;
//		}
//		try {
//			// 确定计算方法
//			MessageDigest md5 = MessageDigest.getInstance("MD5");
//			BASE64Encoder base64en = new BASE64Encoder();
//			// 加密后的字符a串
//			return base64en.encode(md5.digest(str.getBytes("utf-8")));
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			return null;
//		}
//	}

    /**
     * 获取MD5加密后的字符串
     *
     * @param str 明文
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String getMD5(String str) throws Exception {
        /** 创建MD5加密对象 */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        /** 进行加密 */
        md5.update(str.getBytes());
        /** 获取加密后的字节数组 */
        byte[] md5Bytes = md5.digest();
        String res = "";
        for (int i = 0; i < md5Bytes.length; i++) {
            int temp = md5Bytes[i] & 0xFF;
            if (temp <= 0XF) { // 转化成十六进制不够两位，前面加零
                res += "0";
            }
            res += Integer.toHexString(temp);
        }
        return res;
    }

    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String getDigest(String dir) throws NoSuchAlgorithmException, IOException {
        @SuppressWarnings("resource")
        FileInputStream is = new FileInputStream(dir);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        byte[] bytes = new byte[2048];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest));
        return result;
    }
}

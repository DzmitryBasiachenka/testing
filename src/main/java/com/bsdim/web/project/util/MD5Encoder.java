package com.bsdim.web.project.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bsdim.web.project.exception.TestingRuntimeException;
import org.apache.log4j.Logger;

public final class MD5Encoder {
    private static Logger sLogger = Logger.getLogger(MD5Encoder.class);

    private MD5Encoder() {}

    public static String generateHash(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(data.getBytes());
            BigInteger number = new BigInteger(1, bytes);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            sLogger.error("Generate hash error!");
            throw new TestingRuntimeException("Generate hash error!", e);
        }
    }
}

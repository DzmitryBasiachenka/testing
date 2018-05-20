package com.bsdim.web.project.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bsdim.web.project.exception.TestingRuntimeException;
import org.apache.log4j.Logger;

/**
 * The MD5 encoder..
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public final class MD5Encoder {
    private static final int SEXTEEN_RADIX = 16;
    private static final int THIRTY_TWO = 32;

    private static Logger sLogger = Logger.getLogger(MD5Encoder.class);

    private MD5Encoder() {}

    /**
     * Generates hash.
     *
     * @param data the data.
     * @return the hase data.
     */
    public static String generateHash(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(data.getBytes());
            BigInteger number = new BigInteger(1, bytes);
            String hashtext = number.toString(SEXTEEN_RADIX);

            while (hashtext.length() < THIRTY_TWO) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            sLogger.error("Generate hash error!");
            throw new TestingRuntimeException("Generate hash error!", e);
        }
    }
}

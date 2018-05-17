package com.basaki.misc.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

/**
 * {@code ECDSA} is an example of Elliptic Curve cryptography and converting
 * public/private ley to a hex string and reconverting the string back to its
 * corresponding key.
 * classpath.
 * <p/>
 * Answers to stackoverflow questions:
 * <ol>
 * <li>https://stackoverflow.com/questions/50275351/converting-ec-publickey-hex-string-to-publickey</li>
 * </ol>
 *
 * @author Indra Basak
 * @since 05/17/18
 */
public class ECDSA {

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, NoSuchProviderException {
        // KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime256v1");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(ecSpec, random);
        KeyPair pair = keyGen.generateKeyPair();
        return pair;
    }

    public static PublicKey getPublicKey(
            byte[] pk) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pk);
        //KeyFactory kf = KeyFactory.getInstance("EC");
        KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
        PublicKey pub = kf.generatePublic(publicKeySpec);
        return pub;
    }

    public static PrivateKey getPrivateKey(
            byte[] privk) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchProviderException {
        // EncodedKeySpec privateKeySpec = new X509EncodedKeySpec(privk);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privk);
        //KeyFactory kf = KeyFactory.getInstance("EC");
        KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey = kf.generatePrivate(privateKeySpec);
        return privateKey;
    }

    public static String encodeByteToString(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    public static byte[] decodeStringToByte(String input) {
        return Base64.getDecoder().decode(input);
    }

    public static String toHexString(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

    public static byte[] toByteArray(String hexString) {
        return DatatypeConverter.parseHexBinary(hexString);
    }
}

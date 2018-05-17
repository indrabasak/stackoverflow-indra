package com.basaki.misc.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@code ECDSATest} implements unit tests for class {@code ECDSA}
 * <p/>
 *
 * @author Indra Basak
 * @since 05/17/18
 */
public class ECDSATest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Test
    public void testGenerateECDSAKeyPair() throws
            InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchProviderException {
        KeyPair pair = ECDSA.generateKeyPair();
        assertNotNull(pair);
        assertNotNull(pair.getPublic());
        assertNotNull(pair.getPrivate());
    }

    @Test
    public void testGetPublicKeyWithBase64Encoding() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeySpecException {
        KeyPair pair = ECDSA.generateKeyPair();
        assertNotNull(pair);
        assertNotNull(pair.getPublic());

        String keyStr = ECDSA.encodeByteToString(pair.getPublic().getEncoded());

        PublicKey derivedPublicKey =
                ECDSA.getPublicKey(ECDSA.decodeStringToByte(keyStr));
        assertNotNull(derivedPublicKey);
        assertEquals(pair.getPublic(), derivedPublicKey);
    }

    @Test
    public void testGetPrivateKeyWithBase64Encoding() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeySpecException {
        KeyPair pair = ECDSA.generateKeyPair();
        assertNotNull(pair);
        assertNotNull(pair.getPublic());

        String keyStr = ECDSA.encodeByteToString(pair.getPrivate().getEncoded());

        PrivateKey derivedPrivateKey =
                ECDSA.getPrivateKey(ECDSA.decodeStringToByte(keyStr));
        assertNotNull(derivedPrivateKey);
        assertEquals(pair.getPrivate(), derivedPrivateKey);
    }

    @Test
    public void testGetPublicKeyWithHexString() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeySpecException {
        KeyPair pair = ECDSA.generateKeyPair();
        assertNotNull(pair);
        assertNotNull(pair.getPublic());

        String keyStr = ECDSA.toHexString(pair.getPublic().getEncoded());

        PublicKey derivedPublicKey =
                ECDSA.getPublicKey(ECDSA.toByteArray(keyStr));
        assertNotNull(derivedPublicKey);
        assertEquals(pair.getPublic(), derivedPublicKey);
    }

    @Test
    public void testGetPrivateKeyWithHexString() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeySpecException {
        KeyPair pair = ECDSA.generateKeyPair();
        assertNotNull(pair);
        assertNotNull(pair.getPublic());

        String keyStr = ECDSA.toHexString(pair.getPrivate().getEncoded());

        PrivateKey derivedPrivateKey =
                ECDSA.getPrivateKey(ECDSA.toByteArray(keyStr));
        assertNotNull(derivedPrivateKey);
        assertEquals(pair.getPrivate(), derivedPrivateKey);
    }
}

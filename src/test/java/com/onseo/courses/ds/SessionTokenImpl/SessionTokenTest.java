package com.onseo.courses.ds.SessionTokenImpl;

import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.SignatureException;

import static org.junit.Assert.*;

public class SessionTokenTest {

    @Test
    public void createTokenTest() {
        try {
            String tempToken = SessionToken.createToken("000000001", 1L);

        } catch (SignatureException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verifyTokenTest() {
        try {
            String tempToken = SessionToken.createToken("000000001", 1L);
            TokenInfo tokenInfo = (TokenInfo) SessionToken.verifyToken(tempToken);
            Assert.assertNotNull(tokenInfo);
        } catch (SignatureException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
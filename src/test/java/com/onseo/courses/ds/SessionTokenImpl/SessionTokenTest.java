package com.onseo.courses.ds.SessionTokenImpl;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionTokenTest {

    private final static String userId = "000000001";

    @Test
    public void createTokenTest() throws Exception{
        Long durationTime = 10L;
        String tmpToken = SessionToken.createToken(userId, durationTime);
        assertNotNull(tmpToken);
    }

    @Test
    public void verifyTokenTest() throws Exception{
        Long durationTime = 30L;
        String tmpToken = SessionToken.createToken(userId, durationTime);
        TokenInfo tokenInfo = SessionToken.verifyToken(tmpToken);
        assertNotNull(tokenInfo);
        assertEquals(userId, tokenInfo.getUserId());
    }

    @Test
    public void expireTimeTest()throws Exception{
        Long durationTime = 5L;
        String tmpToken = SessionToken.createToken(userId, durationTime);
        assertFalse(SessionToken.isExpired());
        Thread.sleep(5000);
        assertTrue(SessionToken.isExpired());
    }
}

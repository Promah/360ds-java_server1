package com.onseo.courses.ds.interfaces;

import com.onseo.courses.ds.SessionTokenImpl.TokenInfo;

import java.security.InvalidKeyException;
import java.security.SignatureException;

public interface SessionTokenInterface {


    static  String createToken(String userId, Long durationTime) throws SignatureException, InvalidKeyException {
        return null;
    }

    static  TokenInfo verifyToken(String token) throws InvalidKeyException, SignatureException {
        return null;
    }

}

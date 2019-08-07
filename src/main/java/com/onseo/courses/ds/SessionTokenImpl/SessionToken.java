package com.onseo.courses.ds.SessionTokenImpl;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.onseo.courses.ds.interfaces.SessionTokenInterface;
import com.onseo.courses.ds.logger.Logging;
import com.sun.istack.internal.logging.Logger;
import net.oauth.jsontoken.Checker;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.springframework.util.StringUtils;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class    SessionToken implements SessionTokenInterface {
    private static final String AUDIENCE = "NotReallyImportant";

    private static final String ISSUER = "Onseo360ds";

    private static final String SIGNING_KEY = "LongAndHardToGuessValueWithSpecialCharacters@^($%*$%";

    private static JsonToken token;

    public static String createToken(String userId, Long durationTime) throws SignatureException, InvalidKeyException {
        Calendar calendar = Calendar.getInstance();
        HmacSHA256Signer signer = null;

        signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());

        //was local variable
        token = new JsonToken(signer);
        
        token.setAudience(AUDIENCE);
        token.setIssuedAt(new Instant(calendar.getTimeInMillis()));
        token.setExpiration(new Instant(calendar.getTimeInMillis() + 1000L*durationTime));
        System.out.println("exp time " + new Date(calendar.getTimeInMillis() + 1000L*durationTime));

        JsonObject request = new JsonObject();
        request.addProperty("userId", userId);

        JsonObject payload = token.getPayloadAsJsonObject();

        payload.add("info", request);

        return token.serializeAndSign();
    }

    public static TokenInfo verifyToken(String token) throws InvalidKeyException, SignatureException {
        TokenInfo tokenInfo = new TokenInfo();

        final Verifier verifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());

        VerifierProvider locator = (id, key) -> Lists.newArrayList(verifier);
        VerifierProviders locators = new VerifierProviders();
        locators.setVerifierProvider(SignatureAlgorithm.HS256,locator);
        Checker checker = payload -> {};
        JsonTokenParser parser = new JsonTokenParser(locators,checker);

        JsonToken jsonToken = parser.verifyAndDeserialize(token);
        System.out.println("from verif " + jsonToken.getExpiration().getMillis());

        JsonObject payload = jsonToken.getPayloadAsJsonObject();

        String issuer = payload.getAsJsonPrimitive("iss").getAsString();
        String userId = payload.getAsJsonObject("info").getAsJsonPrimitive("userId").getAsString();
        System.out.println("USER ID CREATED FROM JSON " + userId);

        if(issuer.equals(ISSUER) && !StringUtils.isEmpty(userId)){
            tokenInfo.setUserId(userId);
            return tokenInfo;
        }else{
            return null;
        }
    }

    public static void updateExpireTime(Long newDurationTime){
        Calendar calendar = Calendar.getInstance();
        if (newDurationTime > 0){
            token.setExpiration(new Instant(calendar.getTimeInMillis() + 1000L * newDurationTime));
            System.out.println("exp time " + new Date(calendar.getTimeInMillis() + 1000L*newDurationTime));
        } else{
            Logging.getLogger().warn("Error in updateExpireTime: Wrong value of token duration time = " + newDurationTime);
        }
    }

    public static boolean isValid(String receivedToken) throws SignatureException{
        return token.serializeAndSign().equals(receivedToken);
    }

    public static boolean isExpired(){
        return new DateTime().isAfter(token.getExpiration().getMillis());
    }

}

package server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dbal.specification.AccountSpecification;
import models.Account;
import server.handler.PersistenceHandler;

import java.util.Date;
import java.util.Objects;

public class JwtUtils {
    private final PersistenceHandler persistence;
    private Algorithm algorithm = Algorithm.HMAC256("supersecretchronomancerstringthatyoutotallywontguess");
    private String issuer = "oibss-chrono-omega";
    private JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
    private HashUtil hashUtil = new HashUtil();

    public JwtUtils(PersistenceHandler persistenceHandler) {
        this.persistence = persistenceHandler;
    }

    private String createToken(String email, int accountId) {
        Date date = new Date();
        long t = date.getTime();
        Date expirationTime = new Date(t + 60 * 60 * 1000); // set 60 min
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(email)
                    .withClaim("email", email)
                    .withClaim("id", accountId)
                    .withIssuedAt(date)
                    .withExpiresAt(expirationTime)
                    .sign(algorithm);
        } catch (
                JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    private DecodedJWT verifyToken(String token) {
        try {
            return verifier.verify(token);
        } catch (Exception ex) {
            return null;
        }
    }

    public String login(String email, String password) {
        Account account = persistence.getAccountRepository().findOne(AccountSpecification.getByEmail(email));
        if (account == null) return null;
        if (!hashUtil.Sha256Hash(password).equals(account.getPassword())) return null;
        return createToken(email, account.getId());
    }

    public String isLoggedIn(String token){
        DecodedJWT decodedJWT=verifyToken(token);
        if (decodedJWT==null) return null;
        String email =decodedJWT.getClaim("email").asString();
        Date date = new Date();
        if (decodedJWT.getExpiresAt().before(date)) return null;
        return email;
    }

    public int getId(String token){
        return Objects.requireNonNull(verifyToken(token)).getClaim("id").asInt();
    }

}

package br.edu.senac.auto.security.auth;

import br.edu.senac.auto.security.exception.InvalidTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;

@Component
public class GoogleTokenVerifier {

    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new JacksonFactory();
    private static final String CLIENT_ID = "1098028247547-7up3gsvjcav1ahb4v2fg5rt6icmvk7kn.apps.googleusercontent.com";

    public Payload verify(String token) throws GeneralSecurityException, IOException, InvalidTokenException {
        return GoogleTokenVerifier.verifyToken(token);
    }

    public static Payload verifyToken(String token) throws GeneralSecurityException, IOException, InvalidTokenException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setIssuers(Arrays.asList("https://accounts.google.com", "accounts.google.com"))
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken googleIdToken = null;

        try {
            googleIdToken = verifier.verify(token);
        } catch (IllegalArgumentException ex) {
        }

        if (googleIdToken == null) {
            throw new InvalidTokenException("The token is invalid");
        }

        return googleIdToken.getPayload();
    }
}

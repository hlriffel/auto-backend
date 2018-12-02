package br.edu.senac.auto.security.filters;

import br.edu.senac.auto.security.auth.GoogleTokenVerifier;
import br.edu.senac.auto.security.auth.TokenProvider;
import br.edu.senac.auto.security.exception.InvalidTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class LoginFilter implements Filter {

    private GoogleTokenVerifier googleTokenVerifier;

    @Autowired
    public LoginFilter(GoogleTokenVerifier googleTokenVerifier) {
        this.googleTokenVerifier = googleTokenVerifier;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (HttpMethod.OPTIONS.matches(((HttpServletRequest) servletRequest).getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = ((HttpServletRequest) servletRequest).getHeader("X-ID-TOKEN");
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (token != null) {
            Payload payload;

            try {
                payload = googleTokenVerifier.verify(token);

                if (payload != null) {
                    String user = payload.getSubject();
                    TokenProvider.addAuthentication(response, user);
                    filterChain.doFilter(servletRequest, response);
                    return;
                }
            } catch (GeneralSecurityException | InvalidTokenException ex) {
            }
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void destroy() {
        this.googleTokenVerifier = null;
    }
}

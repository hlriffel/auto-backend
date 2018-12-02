package br.edu.senac.auto.security.filters;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static br.edu.senac.auto.security.auth.TokenProvider.addAuthentication;
import static br.edu.senac.auto.security.auth.TokenProvider.getUserFromToken;

@Component
public class MainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Optional<String> userFromToken = getUserFromToken(request);

        if (!userFromToken.isPresent()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        request.setAttribute("userId", userFromToken.get());
        addAuthentication(response, userFromToken.get());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

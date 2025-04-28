package br.com.euandresimoes.api_gateway.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    // Required configuration class for custom filter
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 'exchange' represents the current HTTP request and response
            // 'chain' allows the request to proceed to the next filter in the chain

            // Get Authorization header from the request
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            // Checks if Authorization header exists and starts with "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Extract the token by removing "Bearer "
            String token = authHeader.replace("Bearer ", "");

            try {
                // Validate the JWT using the secret from application.yml
                Algorithm algorithm = Algorithm.HMAC256(secret);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(token);
            } catch (Exception e) {
                // Invalid or expired token
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Token is valid; continue processing the request
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}

package com.formacionbdi.springboot.app.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ExampleGlobalFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(ExampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Executing pre filter");
        exchange.getRequest().mutate().headers(h -> h.add("token","123456"));

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("Executing post filter");

            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(v -> {
                exchange.getResponse().getHeaders().add("token",v);
            });
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color","red").build());
//            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

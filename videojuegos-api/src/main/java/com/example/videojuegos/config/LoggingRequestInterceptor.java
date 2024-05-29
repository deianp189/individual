// src/main/java/com/example/videojuegos/config/LoggingRequestInterceptor.java
package com.example.videojuegos.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import java.io.IOException;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        System.out.println("Request URI: " + request.getURI());
        return execution.execute(request, body);
    }
}

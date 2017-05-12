package br.hm.example.http.client;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by helmut.guimaraes on 11/05/2017.
 */
public class AuthClienteHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private final String headerName;
    private String headerValue = "";

    public AuthClienteHttpRequestInterceptor(String headerName) {
        this.headerName = headerName;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(headerName, headerValue);
        ClientHttpResponse clientHttpResponse = execution.execute(request, body);
        if (clientHttpResponse.getHeaders().containsKey(headerName)) {
            this.headerValue = clientHttpResponse.getHeaders().get(headerName).get(0);
        }
        return clientHttpResponse;
    }
}

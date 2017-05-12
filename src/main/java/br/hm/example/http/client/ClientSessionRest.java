package br.hm.example.http.client;

import br.hm.example.api.SessionResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helmut.guimaraes on 11/05/2017.
 */

public class ClientSessionRest {

    private String headerName = "x-auth-token";
    private final URI base;
    private final RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientSessionRest.class);

    public ClientSessionRest(String baseApi) {
        this.base = UriComponentsBuilder.newInstance().fromHttpUrl(baseApi).build().toUri();
        this.restTemplate = builddRestTemplate();
    }

    public SessionResource getAttributes() {
        URI  uri = UriComponentsBuilder.newInstance().uri(base).path("/session/atribbuters").build().toUri();
        LOGGER.info("get url {}" , uri.toString());
        return restTemplate.getForObject(uri, SessionResource.class);
    }

    public void setAttribute(String key, String value) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("value", value);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity httpEntity = new HttpEntity(body,httpHeaders);

        String url = UriComponentsBuilder.newInstance().uri(base).path("/session/atribbuters/" + key).build().toUriString();
        SessionResource sessionResource = restTemplate.postForObject(url, httpEntity, SessionResource.class);
    }

    private RestTemplate builddRestTemplate() {
        RestTemplate result = new RestTemplate();

        result.setInterceptors(buildClientHttpRequestInterceptor());
        result.setMessageConverters(buildMessageConverter());
        return result;
    }

    private List<HttpMessageConverter<?>> buildMessageConverter() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        return messageConverters;
    }

    private List<ClientHttpRequestInterceptor> buildClientHttpRequestInterceptor() {
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptorList = new ArrayList();
        clientHttpRequestInterceptorList.add(new AuthClienteHttpRequestInterceptor(headerName));
        return clientHttpRequestInterceptorList;
    }


}

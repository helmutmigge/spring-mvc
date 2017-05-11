package br.hm.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by helmut.guimaraes on 08/05/2017.
 */
@Component
@PropertySource("classpath:application.properties")
public class RedisProperties {

    @Value("${spring.redis.host}")
    private String host = "localhost";

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port = 6379;


    /**
     * Redis server host.
     */
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Login password of the redis server.
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Redis server port.
     */
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

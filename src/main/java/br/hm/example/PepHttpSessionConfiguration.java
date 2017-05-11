package br.hm.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by helmut.guimaraes on 08/05/2017.
 */
@Import({RedisProperties.class, HttpSessionProperties.class})
public class PepHttpSessionConfiguration extends RedisHttpSessionConfiguration {

    public PepHttpSessionConfiguration(HttpSessionProperties httpSessionProperties) {
        setMaxInactiveIntervalInSeconds(httpSessionProperties.getMaxInactiveIntervalInSeconds());
        setRedisNamespace("pep");
        setRedisFlushMode(RedisFlushMode.ON_SAVE);
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {

    }

    @Bean
    public LettuceConnectionFactory connectionFactory(RedisProperties redisProperties) {
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new CookieHttpSessionStrategy();
    }
}

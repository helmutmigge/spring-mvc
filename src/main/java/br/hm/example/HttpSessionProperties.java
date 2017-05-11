package br.hm.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by helmut.guimaraes on 08/05/2017.
 */
@PropertySource("classpath:application.properties")
public class HttpSessionProperties {
    @Value("${spring.session.maxInactiveIntervalInSeconds}")
    private int maxInactiveIntervalInSeconds;

    public int getMaxInactiveIntervalInSeconds() {
        return maxInactiveIntervalInSeconds;
    }

    public void setMaxInactiveIntervalInSeconds(int maxInactiveIntervalInSeconds) {
        this.maxInactiveIntervalInSeconds = maxInactiveIntervalInSeconds;
    }
}

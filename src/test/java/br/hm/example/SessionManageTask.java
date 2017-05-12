package br.hm.example;

import br.hm.example.http.client.ClientSessionRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * Created by helmut.guimaraes on 12/05/2017.
 */
public class SessionManageTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionManageTask.class);

    public SessionManageTask() {
    }

    @Override
    public void run() {
        ClientSessionRest clientSessionRest = new ClientSessionRest("http://localhost:8080/spring-mvc/api");
        LOGGER.info(clientSessionRest.getAttributes().toString());
        for (int i = 0; i < 100; i++) {
            final String key  = i + "";
            final String value =  UUID.randomUUID().toString();
            LOGGER.info( "key {} value {}", key, value);
            clientSessionRest.setAttribute(key,value);

            try {
                Thread.sleep(  new Random().nextInt(10000));
            } catch (InterruptedException e) {
            }
        }
        LOGGER.info(clientSessionRest.getAttributes().toString());
    }
}
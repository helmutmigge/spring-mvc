package br.hm.example;

import br.hm.example.http.client.ClientSessionRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ClientSessionRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientSessionRestTest.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;



    @Test
    public void createClientSessionRest() {
        SessionManageTask sessionManageTask = new SessionManageTask();
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            futures.add(taskExecutor.submit(sessionManageTask));
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}

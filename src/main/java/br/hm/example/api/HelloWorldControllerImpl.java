package br.hm.example.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by helmutmigge on 18/02/2017.
 */

@Controller
@Transactional(readOnly = true)
@RequestMapping("/hello")
public class HelloWorldControllerImpl  {

    final static Logger LOGGER = LoggerFactory.getLogger(HelloWorldControllerImpl.class);


    public HelloWorldControllerImpl(){
        LOGGER.info("Constructor HelloWorldControllerImpl");
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String hello(){
        return "Hello";
    }
}

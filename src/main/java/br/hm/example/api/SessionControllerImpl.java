package br.hm.example.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by helmutmigge on 18/02/2017.
 */

@RestController
@Transactional(readOnly = true)
@RequestMapping("/session")
public class SessionControllerImpl {

    final static Logger LOGGER = LoggerFactory.getLogger(SessionControllerImpl.class);


    public SessionControllerImpl() {
        LOGGER.info("Constructor SessionControllerImpl");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionResource> getSession(HttpServletRequest request) {

        String sessionId = request.getSession().getId();
        LOGGER.info("session {}", sessionId);
        return new ResponseEntity(sessionId, HttpStatus.OK);
    }

    @GetMapping(value = "atribbuters")
    public ResponseEntity<SessionResource> sessionAttributtes(HttpServletRequest request) {
        return new ResponseEntity(buildSessionResource(request), HttpStatus.OK);
    }

    private SessionResource buildSessionResource(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getSession().getAttributeNames();
        HashMap<String, Object> attributes = new HashMap<>();

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            attributes.put(name, request.getSession().getAttribute(name));
        }
        return new SessionResource(request.getSession().getId(), attributes);
    }

    @PostMapping(value = "atribbuters/{key}")
    public ResponseEntity<SessionResource> putAttributteSession(HttpServletRequest request, @PathVariable("key") String key, @RequestParam("value") String value) {
        LOGGER.info("putAttributteSession {} {} ", key, value);
        request.getSession().setAttribute(key, value);
        return new ResponseEntity(buildSessionResource(request), HttpStatus.OK);
    }

    @GetMapping(value = "atribbuters/{key}")
    public ResponseEntity<String> getAttributteSession(HttpServletRequest request, @PathVariable("key") String key) {
        String value = (String) request.getSession().getAttribute(key);
        return new ResponseEntity(value, HttpStatus.OK);
    }

}

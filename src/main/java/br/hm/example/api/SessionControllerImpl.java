package br.hm.example.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<String> enableSession(HttpServletRequest request) {
        LOGGER.info("session antes  " + request.getRequestedSessionId());
        String sessionId = request.getSession().getId();
        LOGGER.info("session depois " + sessionId);
        return new ResponseEntity(sessionId, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity putAttributteSession(HttpServletRequest request, @RequestParam("key") String key, @RequestParam("value") String value) {
        LOGGER.info("putAttributteSession {} {} ", key, value);
        request.getSession().setAttribute(key, value);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/{key}")
    public ResponseEntity<String> putAttributteSession(HttpServletRequest request, @PathVariable("key") String key) {
        String value = (String) request.getSession().getAttribute(key);
        return new ResponseEntity(value, HttpStatus.OK);
    }
}

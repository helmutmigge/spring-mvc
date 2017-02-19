package br.hm.example.api;

import br.hm.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by helmutmigge on 18/02/2017.
 */
@Controller
@Transactional
@RequestMapping("user")
public class UserControllerImpl {
    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public @ResponseBody List<String> getUserNames() {
        return userDao.getNames();
    }
}

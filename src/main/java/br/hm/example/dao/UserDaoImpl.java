package br.hm.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by helmutmigge on 18/02/2017.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;

    public UserDaoImpl(){
    }

    @Override
    public List<String> getNames() {
        return template.queryForList("select name from users",String.class);
    }
}

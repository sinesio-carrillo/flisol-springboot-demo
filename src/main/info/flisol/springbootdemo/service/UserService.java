package info.flisol.springbootdemo.service;

import info.flisol.springbootdemo.model.User;
import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
    String getSlowData(String parameter);
}
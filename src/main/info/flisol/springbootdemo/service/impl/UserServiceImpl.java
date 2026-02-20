package info.flisol.springbootdemo.service.impl;

import info.flisol.springbootdemo.model.User;
import info.flisol.springbootdemo.repository.UserRepository;
import info.flisol.springbootdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Cacheable("slow-data")
    @Override
    public String getSlowData(String parameter) {
        log.info("--- START Executing SLOW data fetch for parameter: {} ---", parameter);
        String result =  null;
        try {
            // Simulate a slow database call or API fetch
            Thread.sleep(10000L); // 10-second delay
            result =  "This is the slow data for " + parameter;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("--- END Executing SLOW data fetch for parameter: {} ---", parameter);
        return result;
    }
}
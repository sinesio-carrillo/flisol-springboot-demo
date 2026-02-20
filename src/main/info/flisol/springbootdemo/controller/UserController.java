package info.flisol.springbootdemo.controller;

import java.util.List;
import info.flisol.springbootdemo.model.User;
import info.flisol.springbootdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/test-cache/{param}")
    public String testCache(@PathVariable String param) {
        log.info("Starting HTTP GET method (cached)");
        String result = userService.getSlowData(param);
        log.info("Ending HTTP GET method (cached)");

        return result;
    }
}
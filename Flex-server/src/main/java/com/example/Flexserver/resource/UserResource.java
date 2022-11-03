package com.example.Flexserver.resource;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response> findUserById(@PathVariable String userId) {
        return ResponseEntity.ok(this.userService.findUserById(Integer.parseInt(userId)));
    }

    @GetMapping("/login")
    public ResponseEntity<Response> findUserByUserNameAndPassword(@RequestParam(required = true) String username,@RequestParam(required = true) String password) {
        return ResponseEntity.ok(this.userService.findUserByUserNameAndPassword(username,password));
    }
}

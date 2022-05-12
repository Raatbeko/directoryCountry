package com.example.directorycountry.controller;

import com.example.directorycountry.dto.user.request.UserAuthRequest;
import com.example.directorycountry.dto.user.request.UserRequest;
import com.example.directorycountry.dto.user.response.UserResponse;
import com.example.directorycountry.dto.user.response.UserTokenResponse;
import com.example.directorycountry.exception.EmailNotBeEmptyException;
import com.example.directorycountry.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    final UserService userService;


    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) throws EmailNotBeEmptyException {
        return userService.save(request);
    }

    @SneakyThrows
    @PostMapping("/auto")
    public UserTokenResponse auto(@RequestBody UserAuthRequest request) {
        return userService.getToken(request);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

}

package com.kino.controller;

import com.kino.exceptions.EntityNotFoundException;
import com.kino.model.User;
import com.kino.utils.GenerateId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final Map<Long, User> users = new HashMap<>();
    private long nextId = 1;

    @PostMapping
    public User create(@Valid @RequestBody User user) {

        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        user.setId(nextId++);
        users.put(user.getId(), user);

        log.info("Добавлен пользователь с id={}", user.getId());

        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {

        if (user.getId() == null || !users.containsKey(user.getId())) {
            throw new EntityNotFoundException(
                    "Пользователь с id=" + user.getId() + " не найден");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        users.put(user.getId(), user);

        log.info("Обновлен пользователь с id={}", user.getId());

        return user;
    }
}
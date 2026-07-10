package com.kino.controller;

import com.kino.exceptions.EntityNotFoundException;
import com.kino.model.User;
import com.kino.utils.GenerateId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Long, User> users = new HashMap<>();

    @PostMapping
    public User add(@Valid @RequestBody User user) {
        if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        user.setId(GenerateId.INSTANCE.generateId());

        users.put(user.getId(), user);
        log.debug("пользователь с id {} добавлен", user.getId());
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }


    @PutMapping
    public User update(@Valid @RequestBody User updateUser) throws EntityNotFoundException {
        Long id = updateUser.getId();

        if (!users.containsKey(id)) {
            throw new EntityNotFoundException("Пользователь с таким id - " + id + " не найден");
        }
        User executedUser = users.get(id);
        if (Objects.nonNull(updateUser.getName())){
            executedUser.setName(updateUser.getName());
        }
        if (Objects.nonNull(updateUser.getEmail())) {
            executedUser.setEmail(updateUser.getEmail());
        }
        if (Objects.nonNull(updateUser.getLogin())) {
            executedUser.setLogin(updateUser.getLogin());
        }
        if (Objects.nonNull(updateUser.getBirthday())) {
            executedUser.setBirthday(updateUser.getBirthday());
        }

        users.put(id,executedUser);
        log.debug("пользователь с id {} поменял свои данные", id);
        return users.get(id);
    }
}

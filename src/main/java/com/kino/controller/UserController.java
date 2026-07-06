package com.kino.controller;

import com.kino.model.User;
import org.springframework.web.bind.annotation.*;
import com.kino.utils.GenerateId;

import java.util.*;

@RestController
//@RequestMapping("/users")
public class UserController {
   private Map<Long, User> users = new HashMap<>();

   @PostMapping
    public User add(@RequestBody User user){
       if ( Objects.isNull(user.getName()) || user.getName().isBlank()){
           user.setName(user.getLogin());
       }
       user.setId(GenerateId.INSTANCE.generateId());

       users.put(user.getId(), user);

       return user;
   }

//   @GetMapping
//    public List<User> getUsers(){
//       return new ArrayList<>(users.values());
//   }
    @GetMapping
    public String getUsers(){
        return "string";
    }
}

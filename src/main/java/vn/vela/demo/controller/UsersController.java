package vn.vela.demo.controller;

import java.util.List;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.vela.demo.entity.Users;
import vn.vela.demo.service.UsersService;

@RestController
@RequestMapping("api/users")
public class UsersController {

  @Autowired
  private UsersService usersService;

  @GetMapping
  public List<Users> findUsers() {
    return usersService.findAll();
  }

  @GetMapping("{id}")
  public Users getUser(@PathVariable("id") Long id) {
    return usersService.findById(id);
  }

}
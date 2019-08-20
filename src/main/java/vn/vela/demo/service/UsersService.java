package vn.vela.demo.service;

import java.util.List;
import vn.vela.demo.entity.Users;

public interface UsersService {

  List<Users> findAll();

  Users findById(Long id);

  void deleteUser(Long id);
}

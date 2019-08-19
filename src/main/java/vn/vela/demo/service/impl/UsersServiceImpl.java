package vn.vela.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vela.demo.entity.Users;
import vn.vela.demo.repository.UsersRepository;
import vn.vela.demo.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

  @Autowired
  private UsersRepository usersRepository;

  @Transactional
  public List<Users> findAll() {
    return usersRepository.findAll();
  }

  @Override
  public Users findById(Long id) {
    return usersRepository.findById(id).get();
  }
}
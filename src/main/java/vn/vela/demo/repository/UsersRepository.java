package vn.vela.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vela.demo.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Long> {

}

package hio.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import hio.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByEmail(String email);

  User findByEmail(String email);

  User findById(Integer email);

  List<User> findAll();

  @Transactional
  void deleteById(Integer id);

}

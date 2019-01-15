package hio.service.implementation;

import javax.servlet.http.HttpServletRequest;

import hio.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hio.exception.CustomException;
import hio.model.User;
import hio.repository.UserRepository;
import hio.security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String email, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      return jwtTokenProvider.createToken(email, userRepository.findByUsername(email).getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(User user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public Boolean update(User user) {

      if (user != null && user.getId() != null) {
          try {
              if(user.getPassword() != null) {
                  user.setPassword(passwordEncoder.encode(user.getPassword()));
              }
              userRepository.save(user);
              return true;
          } catch (Exception e) {
              throw new CustomException("Eroare La salvarea utilizatorului!", HttpStatus.UNPROCESSABLE_ENTITY);
          }

      } else {
          return false;
      }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public void deleteById(Integer id) {
      userRepository.deleteById(id);
  }

  public User findByEmail(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public User findById(Integer id) {
      User user = userRepository.findById(id);
      if (user == null) {
          throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
      }
      return user;
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public List<User> list() {
      List<User> userList = userRepository.findAll();
      if (userList == null && userList.size() > 0) {
          throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
      }
    return userList;
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public ArrayList<Role> getRole(String roles) {
    List<Role> allRoles = new ArrayList<Role>();

    String[] rolesArray = roles.split(",");
    for (String role : rolesArray) {
        switch (role) {
            case "ROLE_ADMIN": allRoles.add(Role.ROLE_ADMIN);
            break;
            case "ROLE_CLIENT": allRoles.add(Role.ROLE_CLIENT);
            break;
            case "ROLE_MANAGER": allRoles.add(Role.ROLE_MANAGER);
            break;
        }
    }
     return new ArrayList<Role>(allRoles);
  }

}

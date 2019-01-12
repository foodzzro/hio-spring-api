package hio.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT, ROLE_MANAGER;

  public String getAuthority() {
    return name();
  }

}

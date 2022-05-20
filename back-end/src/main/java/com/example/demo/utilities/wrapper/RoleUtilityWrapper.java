package com.example.demo.utilities.wrapper;

import com.example.demo.utilities.authentication.RoleUtility;
import org.springframework.stereotype.Component;

@Component
public class RoleUtilityWrapper {
  public String getUserRoleString() {
    return RoleUtility.Role.ROLE_USER.toString();
  }

  public String getAdminRoleString() {
    return RoleUtility.Role.ROLE_ADMIN.toString();
  }
}

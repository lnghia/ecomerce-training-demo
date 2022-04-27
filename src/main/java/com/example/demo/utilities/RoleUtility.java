package com.example.demo.utilities;

public class RoleUtility {
    public static enum Role {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String toString() {
            return this.name();
        }
    }
}

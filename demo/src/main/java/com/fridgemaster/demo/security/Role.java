package com.fridgemaster.demo.security;
import java.util.EnumSet;
import java.util.Set;

public enum Role {
    User("User"),
    Premium_User("Premium_User"),
    Admin("Admin");
    private String role;
    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

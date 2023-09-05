package com.fridgemaster.demo.security;
import java.util.EnumSet;
import java.util.Set;

public enum Role {
    Guest(Set.of(Privilege.VIEW_RECIPES)),
    User(Set.of(Privilege.VIEW_RECIPES, Privilege.VIEW_FRIDGE)),
    Premium_User(Set.of(Privilege.VIEW_RECIPES, Privilege.VIEW_FRIDGE, Privilege.ADD_RECIPES)),
    Admin(EnumSet.allOf(Privilege.class));

    private Set<Privilege> privileges;
    Role(Set<Privilege> privileges){
        this.privileges = privileges;
    }
    public Set<Privilege> getPrivileges(){
        return this.privileges;
    }
}

enum Privilege {
    VIEW_RECIPES,
    VIEW_FRIDGE,
    ADD_RECIPES,
    DELETE_RECIPES
}

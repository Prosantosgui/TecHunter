package com.prosantosgui.techunter.model.enums;

public enum UserRole {
    ADMIN("admin"),
    RECRUITER("recruiter"),
    CANDIDATE("candidate");


    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

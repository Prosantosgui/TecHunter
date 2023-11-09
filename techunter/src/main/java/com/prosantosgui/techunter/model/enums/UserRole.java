package com.prosantosgui.techunter.model.enums;

//TODO definir as ROLES de acordo com a lógica do sistema
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

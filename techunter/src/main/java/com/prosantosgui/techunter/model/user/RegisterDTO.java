package com.prosantosgui.techunter.model.user;

import com.prosantosgui.techunter.model.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}

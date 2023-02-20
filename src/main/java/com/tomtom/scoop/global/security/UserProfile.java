package com.tomtom.scoop.global.security;

import com.tomtom.scoop.domain.user.model.entity.User;
import lombok.Getter;

@Getter
public class UserProfile {
    private final String oauthId;

    private UserProfile(String oauthId) {
        this.oauthId = oauthId;
    }

    public static UserProfile of(String oauthId) {
        return new UserProfile(oauthId);
    }

    public User toUser() {
        return User.of(oauthId);
    }
}

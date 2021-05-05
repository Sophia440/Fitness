package com.epam.web.dto;

public class UserDto {

    private Long id;
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static class Builder {
        private UserDto newUser;

        public Builder() {
            this.newUser = new UserDto();
        }

        public Builder id(Long id) {
            newUser.id = id;
            return this;
        }

        public Builder name(String login) {
            newUser.login = login;
            return this;
        }

        public UserDto build() {
            return newUser;
        }
    }
}

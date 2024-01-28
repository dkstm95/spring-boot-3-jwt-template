package com.seungilahn.springboot3jwttemplate.common;

import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import com.seungilahn.springboot3jwttemplate.user.domain.User;

public class UserTestData {

    public static User defaultUser() {
        return new UserTestDataBuilder()
                .withId(1L)
                .withEmail("example@gmail.com")
                .withName("example")
                .withPhoneNumber("01012345678")
                .withPassword("password")
                .withRole(Role.USER)
                .withEnabled(true)
                .build();
    }

    public static class UserTestDataBuilder {
        private Long id;
        private String email;
        private String name;
        private String phoneNumber;
        private String password;
        private Role role;
        private boolean enabled;

        public UserTestDataBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserTestDataBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserTestDataBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserTestDataBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserTestDataBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserTestDataBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserTestDataBuilder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {
            return User.withId(id, email, name, phoneNumber, password, role, enabled);
        }
    }

}

package builders;

import model.User;

public class UserBuilder {

    private String username = "Pepe";
    private String password = "123456";

    public static UserBuilder user() {
        return new UserBuilder();
    }

    public User build() {
        return new User(username, password);
    }

    public UserBuilder withUsername(String aUsername) {
        username = aUsername;
        return this;
    }

    public UserBuilder withPassword(String aPassword) {
        password = aPassword;
        return this;
    }
}

package Networking.objectProtocol;

import Model.User;

public class LogoutRequest implements Request {
    private User user;

    public LogoutRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

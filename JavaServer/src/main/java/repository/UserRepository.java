package repository;

import domain.User;

public class UserRepository extends AbstactRepository<User>{
    public UserRepository() {
        setClazz(User.class);
    }
}

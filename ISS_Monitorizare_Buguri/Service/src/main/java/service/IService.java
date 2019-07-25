package service;

import model.Bug;
import model.User;

public interface IService {
    User login(String username,String password) throws ServiceException;
    void logout(User user, IAdministratorClient administratorClient) throws ServiceException;
    void logout(User user, IProgrammerClient administratorClient) throws ServiceException;
    void logout(User user, ITesterClient testerClient) throws ServiceException;

    void addObeserver(User user, IAdministratorClient administratorClient) throws ServiceException;
    void addObeserver(User user, IProgrammerClient programmerClient) throws ServiceException;
    void addObeserver(User user, ITesterClient testerClient) throws ServiceException;

    Iterable<User> getUsers();
    void addUser(User user);
    void deleteUser(User user);

    void addBug(Bug bug);
    void updateBug(Bug bug);
    void deleteBug(Bug bug);

    Iterable<Bug> getBugs();


    void updateUser(User user1);
}

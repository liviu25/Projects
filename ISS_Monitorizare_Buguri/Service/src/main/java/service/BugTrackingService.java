package service;

import model.Bug;
import model.User;
import repository.BugRepository;
import repository.UserRepository;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BugTrackingService implements IService {
    private Map<String, IAdministratorClient> loggedAdministrators;
    private Map<String, IProgrammerClient> loggedProgrammers;
    private Map<String, ITesterClient> loggedTesters;
    private UserRepository userRepository;
    private BugRepository bugRepository;

    public BugTrackingService(UserRepository userRepository, BugRepository bugRepository) {
        this.userRepository = userRepository;
        this.bugRepository = bugRepository;
        loggedAdministrators=new ConcurrentHashMap<>();
        loggedProgrammers=new ConcurrentHashMap<>();
        loggedTesters=new ConcurrentHashMap<>();
    }

    @Override
    public User login(String username, String password) throws ServiceException {
        User user = userRepository.findOne(username);
        if(user.getPassword().equals(password))
        {
            return user;
        }
        else throw new ServiceException("Authentication failed.");

    }

    @Override
    public void logout(User user, IAdministratorClient administratorClient) throws ServiceException {
        IAdministratorClient localClient=loggedAdministrators.remove(user.getUsername());
        if (localClient==null)
            throw new ServiceException("User "+user.getUsername()+" is not logged in.");
        System.out.println("User "+user.getUsername()+" logged out.");
    }

    @Override
    public void logout(User user, IProgrammerClient administratorClient) throws ServiceException {
        IProgrammerClient localClient=loggedProgrammers.remove(user.getUsername());
        if (localClient==null)
            throw new ServiceException("User "+user.getUsername()+" is not logged in.");
        System.out.println("User "+user.getUsername()+" logged out.");
    }

    @Override
    public void logout(User user, ITesterClient testerClient) throws ServiceException {
        ITesterClient localClient=loggedTesters.remove(user.getUsername());
        if (localClient==null)
            throw new ServiceException("User "+user.getUsername()+" is not logged in.");
        System.out.println("User "+user.getUsername()+" logged out.");
    }

    @Override
    public void addObeserver(User user, IAdministratorClient administratorClient) throws ServiceException {
        if(user!=null)
        {
            if(loggedAdministrators.get(user.getUsername())!=null)
                throw new ServiceException("User already logged in.");
            loggedAdministrators.put(user.getUsername(), administratorClient);
        }
    }

    @Override
    public void addObeserver(User user, IProgrammerClient programmerClient) throws ServiceException {
        if(user!=null)
        {
            if(loggedProgrammers.get(user.getUsername())!=null)
                throw new ServiceException("User already logged in.");
            loggedProgrammers.put(user.getUsername(), programmerClient);
        }
    }

    @Override
    public void addObeserver(User user, ITesterClient testerClient) throws ServiceException {
        if(user!=null)
        {
            if(loggedTesters.get(user.getUsername())!=null)
                throw new ServiceException("User already logged in.");
            loggedTesters.put(user.getUsername(), testerClient);
        }
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.getAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        notifyAdministrators();
    }



    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
        notifyAdministrators();
    }

    @Override
    public void addBug(Bug bug) {
        bugRepository.save(bug);
        notifyProgrammers();
    }

    @Override
    public void updateBug(Bug bug) {
        bugRepository.update(bug);
        notifyProgrammers();
    }

    @Override
    public void deleteBug(Bug bug) {
        bugRepository.delete(bug);
        notifyProgrammers();
    }


    @Override
    public Iterable<Bug> getBugs() {
        return bugRepository.getAll();
    }

    @Override
    public void updateUser(User user1) {
        userRepository.update(user1);
        notifyAdministrators();
    }

    private void notifyAdministrators() {
        ExecutorService executor= Executors.newFixedThreadPool(4);
        for (IAdministratorClient client : loggedAdministrators.values()) {
            if(client != null)
            {
                executor.execute(() ->{
                    try {
                        client.usersListUpdated();
                    }
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executor.shutdown();
    }

    private void notifyProgrammers() {
        ExecutorService executor= Executors.newFixedThreadPool(4);
        for (IProgrammerClient client : loggedProgrammers.values()) {
            if(client != null)
            {
                executor.execute(() ->{
                    try {
                        client.bugsListUpdated();
                    }
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executor.shutdown();
    }
}

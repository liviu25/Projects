package Networking.utils;

import Networking.objectProtocol.ClientObjectWorker;
import Service.IServer;

import java.net.Socket;

public class ObjectConcurrentServer extends AbstractConcurrentServer {
    private IServer concursServer;
    public ObjectConcurrentServer(int port, IServer server) {
        super(port);
        this.concursServer=server;
    }

    @Override
    protected Thread createWorker(Socket client) {
        ClientObjectWorker worker=new ClientObjectWorker(concursServer,client);
        Thread tw=new Thread(worker);
        return tw;
    }
}

package Networking.utils;


import Networking.rpcProtocol.ClientRpcWorker;
import Service.IServer;

import java.net.Socket;

public class RpcConcurrentServer extends AbstractConcurrentServer{
    private IServer concursServer;
    public RpcConcurrentServer(int port, IServer server) {
        super(port);
        this.concursServer=server;
    }

    @Override
    protected Thread createWorker(Socket client) {
        ClientRpcWorker worker=new ClientRpcWorker(concursServer,client);
        Thread tw=new Thread(worker);
        return tw;
    }
}

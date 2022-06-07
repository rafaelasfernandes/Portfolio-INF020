package br.edu.ifba.ads.rmi;

import br.edu.ifba.ads.produtorconsumidor.Program;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
 
public class RmiServer extends java.rmi.server.UnicastRemoteObject
implements ReceiveMessageInterface {
    
    int thisPort;  
    String thisAddress;
    Registry registry;   
    Program program;
 
    public void receiveMessage(String x) throws RemoteException {
        if(x.contains("2"))
            this.program.runConsumer();
	else
            this.program.runProducer(x);
    }
    
 
    public RmiServer() throws RemoteException {
        
        try {
           
            this.program = Program.getInstance();
            thisAddress= (InetAddress.getLocalHost()).toString();
        }
        catch(Exception e){
            throw new RemoteException("can't get inet address.");
        }
        
        thisPort = 1099;
        System.out.println("this address="+thisAddress+",port="+thisPort);
        try{
        
            registry = LocateRegistry.createRegistry(thisPort);
            registry.rebind("rmiServer", this);
        }
        catch(RemoteException e){
            throw e;
        }
    }
   
    static public void main(String args[]){
        try{
            RmiServer s = new RmiServer();
        }
        catch (Exception e){
           e.printStackTrace();
           System.exit(1);
        }
    }
}
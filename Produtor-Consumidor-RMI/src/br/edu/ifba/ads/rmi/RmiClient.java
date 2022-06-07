package br.edu.ifba.ads.rmi;

import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.Scanner;

public class RmiClient {

    static public void main(String args[]){
        
        ReceiveMessageInterface rmiServer;
        Registry registry;
        String serverAddress = "192.168.100.21";
        String serverPort = "1099";
       
        String input;
        Scanner inputClient = new Scanner(System.in);
        String process = "";     
       
        try{
            String titleProcess = "";
            while (!process.equalsIgnoreCase("1") 
					&& !process.equalsIgnoreCase("2")) {
		
                System.out.println("Informe o número do processo desejado:");
                System.out.println("1. Produtor");
		System.out.println("2. Consumidor");
                System.out.println("3. Sair");
		process = inputClient.nextLine();
            }

            String output = process.substring(0, 1).toUpperCase() + process.substring(1);
            if(output.equals("1"))
                titleProcess = "Produtor";
            else if(output.equals("2"))
                titleProcess = "Consumidor";
            else
                titleProcess = "Sair";
                
	    System.out.println("Você selecionou o seguinte processo: " + titleProcess);
           
            if(process.equalsIgnoreCase("1"))
		System.out.println("\nInforme algo para ser produzido");
            else
	        System.out.println("\nAperte ENTER para consumir");
           
           
           
            registry = LocateRegistry.getRegistry(serverAddress,
                            (new Integer(serverPort)).intValue());
            
            rmiServer = (ReceiveMessageInterface)(registry.lookup("rmiServer"));
           
            while(inputClient.hasNextLine()){
                
                input = inputClient.nextLine();		
                if(input.equals("3")){
                    System.out.println( "Processo '"+ titleProcess + "' finalizado!");
                    break;
                }
		rmiServer.receiveMessage(process + ": " + input);                
            }
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
        inputClient.close();
    }
}

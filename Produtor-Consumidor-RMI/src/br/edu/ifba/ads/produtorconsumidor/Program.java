package br.edu.ifba.ads.produtorconsumidor;

import java.util.ArrayList;


public class Program {

    int itemCount;
    ArrayList<String> buffer;
    Semaphore mutex;
    Semaphore items;
    private static Program instance;
        
    Program () {
        itemCount = 0;
        buffer = new ArrayList();
        mutex = new Semaphore(1);
        items = new Semaphore(0);
    }
    
    public static Program getInstance() {
	if(instance == null)
            instance = new Program();
	return instance;
    }
    
    public void runProducer(String mensagem) {
	Producer producer = new Producer(mensagem, this);
	new Thread(producer).start();
    }

    public void runConsumer() {
	Consumer consumer = new Consumer(this);
	new Thread(consumer).start();
    } 
}

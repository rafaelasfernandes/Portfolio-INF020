package br.edu.ifba.ads.produtorconsumidor;

public class Consumer extends Thread {
    
    Program a;

    public Consumer(Program a){
        this.a = a;
    }

    public void run() {
        
        try {
           
            a.mutex.down();
            a.items.down();
            a.itemCount--;
   
            a.buffer.remove(0);
            a.mutex.up();
                
            String mensagem = this.a.buffer.get(0);
               
            System.out.println("Consumidor consumiu" + mensagem.substring(mensagem.lastIndexOf(":")));
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package br.edu.ifba.ads.produtorconsumidor;

public class Producer extends Thread {
    
    Program a;
    String mensagem;
    int contador;
  
    Producer(String mensagem, Program a) {
        this.mensagem = mensagem;
        this.a = a;
        this.contador = 0;
    }

    public void run() {
        
        try {
            
            a.mutex.down();  
            a.itemCount++;
            a.mutex.up();
            a.items.up();
            
            this.a.buffer.add(this.mensagem);
            System.out.println("Produtor produziu" + this.mensagem.substring(this.mensagem.lastIndexOf(":")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }                  
    }
}
    

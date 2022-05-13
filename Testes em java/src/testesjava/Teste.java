package testesjava;

public class Teste {

	public static void main(String[] args) {
		
		Tarefa tarefa1 = new Tarefa("Escritor 1", 3000, 1);
		Tarefa tarefa2 = new Tarefa("Escritor 2", 1000, 5);
		Tarefa tarefa3 = new Tarefa("Escritor 3", 1000, 3);
		
		try {
			tarefa1.thread.join();
			tarefa2.thread.join();
			tarefa3.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Programa finalizado!");

	}

}

class Tarefa implements Runnable {
	
	String nome;
	int tempo;
	int cartuchos;
	Thread thread;
	static Caneta caneta = new Caneta();
	
	public Tarefa(String nome, int tempo, int cartuchos) {
		this.nome = nome;
		this.tempo = tempo;
		this.cartuchos = cartuchos;
		thread = new Thread(this, nome);
		thread.start();
	}
	
	public void run() {
		for(int i = 0; i < cartuchos; i++) {
			if(cartuchos > 0) {
				caneta.escrever(this.nome, tempo);
				cartuchos--;
			}
			else {
				System.out.println(this.nome + " nao tem cartuchos suficientes!");
			}
		}
	}
}

class Caneta {
	
	boolean ocupada;
	
	synchronized void escrever(String escritor, int tempo) {
		
		if(!ocupada) {
			ocupada = true;
			
			System.out.println(escritor + " escrevendo...");
			try {
				Thread.sleep(tempo);
				System.out.println(escritor + " terminou!");
				notify();
				ocupada = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
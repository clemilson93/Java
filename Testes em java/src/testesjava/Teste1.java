package testesjava;

public class Teste1 {

	public static void main(String[] args) {
		Canhao canhao = new Canhao();
		Atirador atirador1 = new Atirador("Atirador 1", 2000, canhao);
		Atirador atirador2 = new Atirador("Atirador 2", 2000, canhao);
		Atirador atirador3 = new Atirador("Atirador 3", 2000, canhao);
		

	}

}

class Atirador implements Runnable {
	
	String nome;
	int tempo;
	Thread thread;
	Canhao canhao;
	
	public Atirador(String nome, int tempo, Canhao canhao) {
		this.nome = nome;
		this.tempo = tempo;
		this.canhao = canhao;
		thread = new Thread(this, nome);
		thread.start();
	}
	
	public void run() {
		while(canhao.municao > 0) {
			canhao.atirar(nome, tempo);
		}
	}
}

class Canhao {
	
	int municao = 6;
	
	synchronized void atirar(String atirador, int tempo) {
		
		System.out.println(municao + " municoes disponiveis!");
		System.out.println(atirador + " atirando...");
		try {
			Thread.sleep(tempo);
			municao--;
			notify();
			if(municao > 0) {
				System.out.println(atirador + " aguardando para atirar novamente!");
				wait();
			}
			else {
				System.out.println("Municoes acabaram!");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
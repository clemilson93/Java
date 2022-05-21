package testesjava;

public class Teste4 {

	public static void main(String[] args) {
		long tempo = System.currentTimeMillis();
		long segundos = 0;
		long minutos = 0;
		long horas = 0;
		boolean parar = false;
		
		do {
			horas = (System.currentTimeMillis() - tempo) / 3600000;
			minutos = (System.currentTimeMillis() - tempo) / 60000;
			segundos = (System.currentTimeMillis() - tempo) / 1000;
			System.out.println("Relogio: " + horas + ":" + minutos + ":" + segundos);
			
			if(minutos >= 2) {
				parar = true;
			}
		}while(!parar);

	}

}

//Programa Comandos
package comandosparaumprogramaemjava;
class Comandos {
	public static void main(String[] args) {
		//Comandos de cria��o
		float numero;
		int[] numeros;
		Pessoa pessoa;

		//Comandos de atribui��o
		numero = 10;
		numeros = new int[4];
		pessoa = new Pessoa();

		//Blocos de comandos (n�o repetitivos)

		//C�digo IF / ELSE IF / ELSE
		int outroNumero = 32;
		if (outroNumero == 32) {
			System.out.println("Passou no teste");
		}
		else if (outroNumero > 32){
			System.out.println("N�o passou no teste, est� alto");
		}
		else {
			System.out.println("N�o passou no teste, est� baixo");
		}
		
		//C�digo SWITCH
		int comando = 3;
		switch (comando) {
			case 1:
				System.out.println("Tocar");
			break;
		case 2:
				System.out.println("Pausar");
			break;
		case 3:
				System.out.println("Sair");
			break;
		default:
				System.out.println("Comando inv�lido");
			break;

		}
		
		//Blocos de comandos (repetitivos)
		
		//C�digo WHILE
		int contador = 4;
		while (contador < 5) {
			System.out.println("WHILE Contador " + contador);
			contador++;
		}
		//C�digo DO � WHILE
		int outroContador = 4;
		do {
			System.out.println("DO-WHILE Contador " + outroContador);
			outroContador++;
		} while (outroContador < 5);
		
		//C�digo 1 FOR
		for (int i = 0; i < 5; i++) {
			System.out.println("Contador " + i);
		}
		//C�digo 2 FOR
		int[] outrosNumeros = {1, 2, 3, 4};
		for (int item : outrosNumeros) {
			System.out.println("Item " + item);
		}
		

	}
}


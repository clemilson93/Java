//Programa Comandos
package comandosparaumprogramaemjava;
class Comandos {
	public static void main(String[] args) {
		//Comandos de criação
		float numero;
		int[] numeros;
		Pessoa pessoa;

		//Comandos de atribuição
		numero = 10;
		numeros = new int[4];
		pessoa = new Pessoa();

		//Blocos de comandos (não repetitivos)

		//Código IF / ELSE IF / ELSE
		int outroNumero = 32;
		if (outroNumero == 32) {
			System.out.println("Passou no teste");
		}
		else if (outroNumero > 32){
			System.out.println("Não passou no teste, está alto");
		}
		else {
			System.out.println("Não passou no teste, está baixo");
		}
		
		//Código SWITCH
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
				System.out.println("Comando inválido");
			break;

		}
		
		//Blocos de comandos (repetitivos)
		
		//Código WHILE
		int contador = 4;
		while (contador < 5) {
			System.out.println("WHILE Contador " + contador);
			contador++;
		}
		//Código DO – WHILE
		int outroContador = 4;
		do {
			System.out.println("DO-WHILE Contador " + outroContador);
			outroContador++;
		} while (outroContador < 5);
		
		//Código 1 FOR
		for (int i = 0; i < 5; i++) {
			System.out.println("Contador " + i);
		}
		//Código 2 FOR
		int[] outrosNumeros = {1, 2, 3, 4};
		for (int item : outrosNumeros) {
			System.out.println("Item " + item);
		}
		

	}
}


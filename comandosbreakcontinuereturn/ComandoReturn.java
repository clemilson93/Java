package comandosbreakcontinuereturn;

public class ComandoReturn {

	public static void main(String[] args) {
		//return sem retorno de valores
		imprimirMensagem();
		//return com retorno de valores
		System.out.println(somarNumerosQualquer());
		int soma = somarNumeros(6, 9);
		System.out.println("Soma igual a " + soma);
	}

	static void imprimirMensagem() {
		System.out.println("Olá mundo!");
		return;
	}
	static int somarNumerosQualquer() {
		int resultado = 25 + 36;
		return resultado;
	}
	static int somarNumeros(int numero1, int numero2) {
		return numero1 + numero2;
	}

}

package clemilson;
import java.util.Scanner;
class ColetaDeDados {
	public static void main (String[]args) {
		Scanner dado = new Scanner(System.in);
		System.out.println("Digite um n�mero");
		int numero = dado.nextInt();
		System.out.println("Seu n�mero �: " + numero);
	}

}

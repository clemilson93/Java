package clemilson;
class Programa{
	public static void main (String[]args) {
		Programa programa = new Programa();
		programa.imprimirTexto();
		programa.somar1(6, 2);
		System.out.println(programa.somar2(4, 7));
	}
	//Métodos sem entradas e sem saídas
	void imprimirTexto () {
		System.out.println("Olá mundo");
	}
	//Métodos com entradas e sem saídas
	void somar1 (int numero1, int numero2) {
		int resultado = numero1 + numero2;
		System.out.println("Soma igual a: " + resultado);
	}
	//Método com entradas e com saídas
	int somar2 (int numero1, int numero2) {
		int resultado = numero1 + numero2;
		return resultado;
	}
}

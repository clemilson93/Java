package clemilson;
class Programa{
	public static void main (String[]args) {
		Programa programa = new Programa();
		programa.imprimirTexto();
		programa.somar1(6, 2);
		System.out.println(programa.somar2(4, 7));
	}
	//M�todos sem entradas e sem sa�das
	void imprimirTexto () {
		System.out.println("Ol� mundo");
	}
	//M�todos com entradas e sem sa�das
	void somar1 (int numero1, int numero2) {
		int resultado = numero1 + numero2;
		System.out.println("Soma igual a: " + resultado);
	}
	//M�todo com entradas e com sa�das
	int somar2 (int numero1, int numero2) {
		int resultado = numero1 + numero2;
		return resultado;
	}
}

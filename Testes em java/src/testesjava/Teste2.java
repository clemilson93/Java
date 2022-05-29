package testesjava;

public class Teste2 {

	public static void main(String[] args) {
		System.out.println("Contador principal: " + Contador.contador);
		Contador contador1 = new Contador();
		System.out.println("Contador 1: " + contador1.contador);
		Contador contador2 = new Contador();
		System.out.println("Contador 2: " + contador2.contador);
		Contador contador3 = new Contador();
		System.out.println("Contador 3: " + contador3.contador);
		System.out.println("Contador principal: " + Contador.contador);
		Contador.contador = 0;
		System.out.println("Contador 1: " + contador1.contador);
		System.out.println("Contador 2: " + contador2.contador);
		System.out.println("Contador 3: " + contador3.contador);
		System.out.println("Contador principal: " + Contador.contador);
		
		try {
			System.out.println(Teste2.class.forName("testesjava.Teste"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Contador{
	
	static int contador;
	
	Contador(){
		contador++;
	}
}

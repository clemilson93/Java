package clemilson;

import clemilson.programa.contatos.Pessoa;
class CriadorDePessoas {
	public static void main (String[]args) {
		Pessoa joana = new Pessoa("Joana");
		System.out.println(joana.nome);
		joana.andar();
	}
}

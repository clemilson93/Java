package clemilson;
import clemilson.programa.ferramentas.Calculadora;
import clemilson.programa.pessoas.Gari;
class ClassePrincipal {
	int variavelDaClasse;
	ClassePrincipal() {
		variavelDaClasse = 10;
	}
	ClassePrincipal(int variavelDaClasse) {
		this.variavelDaClasse = variavelDaClasse;
	}
	void imprimirMensagem() {
		System.out.println("Olá mundo!");
	}
	void imprimirMensagem(String mensagem) {
		System.out.println(mensagem);
	}
	public static void main(String[] args) {
		ClassePrincipal classePrincipal = new ClassePrincipal(2);
		classePrincipal.imprimirMensagem();
		String mensagem = "";
		Gari gari = new Gari();
		String nomeDoGari = gari.nome;
		int idadeDoGari = gari.idade;
		mensagem += nomeDoGari+", "+idadeDoGari+"\n";
		Calculadora calculadora = new Calculadora();
		mensagem += calculadora.somar(idadeDoGari, 5)+"\n";
		mensagem += calculadora.subtrair(idadeDoGari, 5)+"\n";
		mensagem += calculadora.multiplicar(idadeDoGari, 5)+"\n";
		mensagem += calculadora.dividir(idadeDoGari, 5)+"\n";
		classePrincipal.imprimirMensagem(mensagem);
	}
}
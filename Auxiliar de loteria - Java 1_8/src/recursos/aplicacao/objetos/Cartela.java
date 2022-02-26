package recursos.aplicacao.objetos;

import java.io.Serializable;

public class Cartela implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nome;
	private int[]numeros;

	public Cartela() {
		
	}
	
	public Cartela(String nome, int[]numeros) {
		this.nome = nome;
		this.numeros = numeros;
	}
	
	public int[] getNumeros() {
		return numeros;
	}

	public void setNumeros(int[] numeros) {
		this.numeros = numeros;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		String mensagem = "Nome: "+this.nome+"/ Numeros: ";
		mensagem += "( "+this.numeros[0];
		for(int i = 1; i < this.numeros.length; i++) {
			mensagem += ", "+this.numeros[i];
		}
		mensagem += " )";
		return mensagem;
	}

}

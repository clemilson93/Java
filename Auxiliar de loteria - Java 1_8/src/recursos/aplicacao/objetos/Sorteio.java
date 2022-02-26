package recursos.aplicacao.objetos;

import java.io.Serializable;

public class Sorteio implements Serializable{
	private static final long serialVersionUID = 1L;
	private String numeroDoSorteio;
	private int[]numerosSorteados;
	
	public Sorteio() {
	}
	
	public Sorteio(String numeroDoSorteio, int[]numerosSorteados) {
		this.numeroDoSorteio = numeroDoSorteio;
		this.numerosSorteados = numerosSorteados;
	}
	
	public String getNumeroDoSorteio() {
		return numeroDoSorteio;
	}

	public void setNumeroDoSorteio(String numeroDoSorteio) {
		this.numeroDoSorteio = numeroDoSorteio;
	}

	public int[] getNumerosSorteados() {
		return numerosSorteados;
	}

	public void setNumerosSorteados(int[] numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}
	
	public String toString() {
		String mensagem = "";
		mensagem += "Numero do sorteio: "+this.numeroDoSorteio+"\nNumeros sorteados: ";
		mensagem += this.numerosSorteados[0]+", ";
		for(int i = 1; i < this.numerosSorteados.length; i++) {
			mensagem += ", "+this.numerosSorteados[i];
		}
		mensagem += ".";
		return mensagem;
	}

}

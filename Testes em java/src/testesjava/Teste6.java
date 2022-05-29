package testesjava;

import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;

public class Teste6 {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa("Clemilson");
		Pessoa p2 = new Pessoa("Paula");
		Pessoa p3 = new Pessoa("Maria");
		Pessoa p4 = new Pessoa("Celio");
		
		
		System.out.println(p1.toString());
		for(Equipamento e : p1.equipamentos) {
			System.out.println(e.toString());
		}
		System.out.println(p2.toString());
		for(Equipamento e : p2.equipamentos) {
			System.out.println(e.toString());
		}
		System.out.println(p3.toString());
		for(Equipamento e : p3.equipamentos) {
			System.out.println(e.toString());
		}
		System.out.println(p4.toString());
		for(Equipamento e : p4.equipamentos) {
			System.out.println(e.toString());
		}

	}

}

class Pessoa{
	String nome = "Pessoa";
	ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
	
	Pessoa(String nome) {
		this.nome = nome;
		adicionarEquipamento(new Roupa("Camisa"));
		adicionarEquipamento(new Roupa("Short"));
		adicionarEquipamento(new Roupa("Sapato"));
		adicionarEquipamento(new Arma("Escudo"));
		adicionarEquipamento(new Arma("Espada"));
	}
	
	<T extends Equipamento> void adicionarEquipamento(T equipamento) {
		T equip = equipamento;
		equip.dono = this;
		equipamentos.add(equip);
	}
	
	public String toString() {
		String mensagem = 
				"|-----Pessoa-----|\nNome: " + this.nome + "\nEquipamentos| ";
		for(Equipamento e : equipamentos) {
			mensagem += e.nome + " | ";
		}
		return mensagem;
		
	}
}

class Roupa extends Equipamento{
	Roupa(){
		this.nome = "Roupa";
	}
	
	Roupa(String nome){
		this.nome = nome;
	}
	
	public String toString() {
		String mensagem = 
				"|-----Roupa-----|\nNome: " + this.nome + "\n" + 
				"Dono: " + this.dono.nome + "\n";
		return mensagem;
	}
}

class Arma extends Equipamento{
	Arma(){
		this.nome = "Arma";
	}
	Arma(String nome){
		this.nome = nome;
	}
	public String toString() {
		String mensagem = 
				"|-----Arma-----|\nNome: " + this.nome + "\n" + 
				"Dono: " + this.dono.nome + "\n";
		return mensagem;
	}
}

class Equipamento{
	String nome;
	Pessoa dono;
	
}
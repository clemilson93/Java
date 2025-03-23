package clemilsonfaustinobatista.jogodaadivinhacao;

import java.util.Random;

public class JogoDaAdivinhacao {

	private static String palpite = "";
	private static String mensagem = "";
	private static int numero = -1;
	public static boolean fimDeJogo = false;
	private static Random rdm = new Random();
	
	public static void main(String[] args) {
		Tela tela = new Tela();
		numero = rdm.nextInt(4);
		boolean controle = true;
		while(true) {
			while(!fimDeJogo) {
				tela.biContext.clearRect(0, 0, 800, 600);
				tela.biContext.drawString("Adivinhe o número!", 10, 50);
				tela.biContext.drawString("Digita um número de 0 a 3", 10, 80);
				tela.biContext.drawString("Tecle ENTER para confirmar ou BACKSPACE para apagar palpite:", 10, 110);
				if(controle) {
					tela.biContext.drawString(">", 10, 140);
					controle = false;
				}
				else {
					controle = true;
				}
				tela.biContext.drawString(""+palpite, 30, 140);
				tela.biContext.drawString(mensagem, 10, 170);
				tela.jfContext.drawImage(tela.bi, 0, 0, null);
				
			}
			tela.biContext.clearRect(0, 0, 800, 600);
			tela.biContext.drawString(mensagem, 10, 50);
			tela.biContext.drawString("Tecle ENTER para uma nova rodada ou ESC pra encerrar o jogo.", 10, 80);
			tela.jfContext.drawImage(tela.bi, 0, 0, null);
		}

	}
	
	public static void atribuirPalpite(char valor) {
		if(palpite.length() < 1) {
			palpite += valor;
		}
	}
	
	public static void reiniciarPalpite() {
		palpite = "";
	}
	
	public static void fazerPalpite() {
		try {
			int numeroDoPalpite = Integer.parseInt(palpite);
			if(numeroDoPalpite > numero) {
				mensagem = "Seu palpite foi maior!";
			}
			else if(numeroDoPalpite < numero){
				mensagem = "Seu palpite foi menor!";
			}
			else {
				mensagem = "Parabéns, você acertou o número!";
				fimDeJogo = true;
			}
		}
		catch(NumberFormatException e) {
			mensagem = "Palpite inválido!";
		}
		reiniciarPalpite();
	}
	
	public static void atribuirNovoNumero() {
		numero = rdm.nextInt(4);
	}
	
	public static void reiniciarJogo() {
		reiniciarPalpite();
		mensagem = "";
		atribuirNovoNumero();
		fimDeJogo = false;
	}
	
	public static void encerrarJogo() {
		System.exit(0);
	}

}

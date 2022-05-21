package testesjava;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Teste5 {

	public InputStream pegarSom(String nomeDoArquivo) {
		InputStream in = getClass().getResourceAsStream("/sons/" + nomeDoArquivo);
		return in;
	}
	
	public static void main(String[] args) {
		Teste5 teste = new Teste5();
		AudioInputStream soundFile;
		Clip sound = null;
		try {
			soundFile = AudioSystem.getAudioInputStream(teste.pegarSom("som2.wav"));
			sound = AudioSystem.getClip();
			sound.open(soundFile);
			System.out.println("Tamanho do audio: " + sound.getMicrosecondLength());
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		Scanner entrada = new Scanner(System.in);
		boolean parar = false;
		do {
			System.out.println("Digite a opcao:\n0 - sair\n1 - Tocar audio\n2 - Parar audio\n3 - Reiniciar audio\n4 - Ativar repeticao\n5 - Verificar tempo de reproducao");
			System.out.print("Digite a opcao:");
			int opcao = entrada.nextInt();
			switch(opcao) {
			case 1:
				sound.start();
				break;
			case 2:
				sound.stop();
				System.out.println("Frame da pausa: " + sound.getFramePosition());
				break;
			case 3:
				sound.setFramePosition(0);
				break;
			case 4:
				sound.setLoopPoints(1169312, 2016914);
				sound.loop(Clip.LOOP_CONTINUOUSLY);
				break;
			case 5:
				System.out.println("Tempo reproduzido: " + (sound.getMicrosecondPosition() / 1000000));
				break;
			case 0:
				parar = true;
				break;
			}
		}while(!parar);
	}

}

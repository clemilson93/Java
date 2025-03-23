package clemilsonfaustinobatista.jogodaadivinhacao;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Tela extends JFrame{
	public Graphics2D jfContext = null;
	public BufferedImage bi = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
	public Graphics2D biContext = null;
	public Tela() {
		setTitle("Jogo da adivinhação");
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(JogoDaAdivinhacao.fimDeJogo == false) {
					JogoDaAdivinhacao.atribuirPalpite(e.getKeyChar());
					if(e.getKeyChar() == '\b') {
						JogoDaAdivinhacao.reiniciarPalpite();
					}
					else if(e.getKeyChar() == '\n') {
						JogoDaAdivinhacao.fazerPalpite();
					}
				}
				else if(JogoDaAdivinhacao.fimDeJogo == true) {
					if(e.getKeyChar() == '\n') {
						JogoDaAdivinhacao.reiniciarJogo();
					}
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(JogoDaAdivinhacao.fimDeJogo == true) {
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						JogoDaAdivinhacao.encerrarJogo();
					}
				}
				
			}
		});
		setVisible(true);
		jfContext = (Graphics2D) getGraphics();
		biContext = (Graphics2D) bi.getGraphics();
		Font font = new Font("Arial", Font.PLAIN, 25);
		biContext.setFont(font);
	}

}

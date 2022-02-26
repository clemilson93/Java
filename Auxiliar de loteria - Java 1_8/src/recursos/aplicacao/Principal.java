package recursos.aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import recursos.aplicacao.telas.AdicionarCartela;
import recursos.aplicacao.telas.AjudaSobreOAuxiliarDeLoteria;
import recursos.aplicacao.telas.ApagarCartela;
import recursos.aplicacao.telas.ConsultarCartela;
import recursos.aplicacao.telas.ListarCartelas;
import recursos.aplicacao.telas.RegistrarSorteio;

public class Principal extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel jPanel;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JMenu menu;

	public Principal() {
		this.setTitle("Auxiliar de loteria");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		menuBar = new JMenuBar();
		
		menu = new JMenu("Sorteio");
		menuItem = new JMenuItem("Novo", new ImageIcon(getClass().getResource("/recursos/aplicacao/arquivos/imagens/Adicionar.png")));
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuBar.add(menu);
		
		menu = new JMenu("Cartela");
		menuItem = new JMenuItem("Adicionar", new ImageIcon(getClass().getResource("/recursos/aplicacao/arquivos/imagens/Adicionar.png")));
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Consultar", new ImageIcon(getClass().getResource("/recursos/aplicacao/arquivos/imagens/Consultar.png")));
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Listar", new ImageIcon(getClass().getResource("/recursos/aplicacao/arquivos/imagens/Listar.png")));
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Apagar", new ImageIcon(getClass().getResource("/recursos/aplicacao/arquivos/imagens/Apagar.png")));
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuBar.add(menu);
		
		menu = new JMenu("Ajuda");
		menuItem = new JMenuItem("Sobre o Auxiliar de Loteria");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuBar.add(menu);
		
		this.setJMenuBar(menuBar);
		this.setIconImage(new ImageIcon(getClass().getResource("/recursos/aplicacao/arquivos/imagens/AuxiliarDeLoteria.png")).getImage());
		jPanel = new RegistrarSorteio();
		this.add(jPanel);
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Adicionar":
			this.remove(jPanel);
			jPanel = new AdicionarCartela();
			this.add(jPanel);
			this.pack();
			break;
		case "Consultar":
			this.remove(jPanel);
			jPanel = new ConsultarCartela();
			this.add(jPanel);
			this.pack();
			break;
		case "Novo":
			this.remove(jPanel);
			jPanel = new RegistrarSorteio();
			this.add(jPanel);
			this.pack();
			break;
		case "Listar":
			this.remove(jPanel);
			jPanel = new ListarCartelas();
			this.add(jPanel);
			this.pack();
			break;
		case "Apagar":
			this.remove(jPanel);
			jPanel = new ApagarCartela();
			this.add(jPanel);
			this.pack();
			break;
		case "Sobre o Auxiliar de Loteria":
			this.remove(jPanel);
			jPanel = new AjudaSobreOAuxiliarDeLoteria();
			this.add(jPanel);
			this.pack();
			break;

		default:
			break;
		}
		
	}

	public static void main(String[] args) {
		if(!new File(".\\registros\\cartelas").exists()) {
			File file = new File(".\\registros\\cartelas\\");
			file.mkdirs();
		}
		new Principal();

	}

}

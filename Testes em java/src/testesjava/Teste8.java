package testesjava;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Jogo {
	CampoDeBatalha campoDeBatalha;
	Jogador[] jogadores;
	Jogador jogadorAtual;
	int naviosInimigos = 5;
	Tela tela;
	
	public Jogo(Tela tela, int quantidadeDeJogadores) {
		this.tela = tela;
		tela.logDeJogo.limpar();
		jogadores = new Jogador[quantidadeDeJogadores];
		for(int i = 0; i < jogadores.length; i++) {
			jogadores[i] =  new Jogador(i);
		}
		jogadorAtual = jogadores[0];
		campoDeBatalha = new CampoDeBatalha(tela);
		campoDeBatalha.distribuirObjetos(new Objeto("navioInimigo", new Recurso().pegarRecursoImagem("navioInimigo.png"), 1, 10), naviosInimigos);
		campoDeBatalha.distribuirObjetos(new Objeto("bauDeTesouro", new Recurso().pegarRecursoImagem("bauDeTesouros.png"), 0, 30), 2);
		campoDeBatalha.desenhar();
		tela.logDeJogo.adicionarTexto("|----------" + jogadorAtual.nome.toUpperCase() + "----------|\n" + 
		"Pontuacao: " + jogadorAtual.pontuacao + 
		"\nMunicao: " + jogadorAtual.municao + 
		"\nNavios destruidos:" + jogadorAtual.naviosDestruidos + 
		"\nSelecione as coordenadas de tiro.");
	}
	
	void iniciar() {
		
	}
	
	void bubbleSort(Jogador _vetor[], int _tamanho){
	    int troca, i;
	    Jogador temp;
	    do{
	        troca = 0;
	        for(i = 0; i < (_tamanho - 1); i++){
	        	if(_vetor[i].pontuacao < _vetor[i + 1].pontuacao){
                    temp = _vetor[i];
                    _vetor[i] = _vetor[i + 1];
                    _vetor[i + 1] = temp;
                    troca++;
                }
	        }
	    }while(troca > 0);
	}
	
	void atirar(int linha, int coluna) {
		if(campoDeBatalha.espacos[linha][coluna].tiroAcertado == false) {
			jogadorAtual.municao -= 1;
			Objeto objeto = campoDeBatalha.espacos[linha][coluna].revelar();
			jogadorAtual.municao += objeto.municaoExtra;
			jogadorAtual.pontuacao += objeto.pontosExtra;
			if(objeto.nome.compareTo("navioInimigo") == 0) {
				jogadorAtual.naviosDestruidos += 1;
				naviosInimigos -= 1;
				Clip som = new Recurso().pegarRecursoAudio("som.wav");
				som.start();
			}
			campoDeBatalha.desenhar();
			tela.telaDeJogo.desenhar();
			
			//conferindo condicoes de fim de jogo ou fim de rodada
			int municaoDosJogadores = 0;
			for(int i = 0; i < jogadores.length; i++) {
				municaoDosJogadores += jogadores[i].municao;
			}
			//a quantidade de navios inimigos destruidos para fim da rodada
			if(naviosInimigos == 0) {
				for(int i = 0; i < jogadores.length; i++) {
					jogadores[i].municao = 5;
					jogadores[i].pontuacao += 5 * jogadores[i].naviosDestruidos;
					jogadores[i].naviosDestruidos = 0;
				}
				jogadorAtual = jogadores[0];
				campoDeBatalha = new CampoDeBatalha(tela);
				campoDeBatalha.distribuirObjetos(new Objeto("navioInimigo", new Recurso().pegarRecursoImagem("navioInimigo.png"), 1, 10), 5);
				campoDeBatalha.distribuirObjetos(new Objeto("bauDeTesouro", new Recurso().pegarRecursoImagem("bauDeTesouros.png"), 0, 30), 2);
				campoDeBatalha.desenhar();
				tela.logDeJogo.adicionarTexto("|----------" + jogadorAtual.nome.toUpperCase() + "----------|\n" + 
						"Pontuacao: " + jogadorAtual.pontuacao + 
						"\nMunicao: " + jogadorAtual.municao + 
						"\nNavios destruidos:" + jogadorAtual.naviosDestruidos + 
						"\nSelecione as coordenadas de tiro.");
				naviosInimigos = 5;
			}
			//para munição insuficiente dos jogadores
			else if(municaoDosJogadores > 0) {
				do {
					if(jogadorAtual.id < jogadores.length - 1) {
						jogadorAtual = jogadores[jogadorAtual.id + 1];
					}
					else {
						jogadorAtual = jogadores[0];
					}
				}while(jogadorAtual.municao == 0);
				tela.logDeJogo.adicionarTexto("|----------" + jogadorAtual.nome.toUpperCase() + "----------|\n" + 
						"Pontuacao: " + jogadorAtual.pontuacao + 
						"\nMunicao: " + jogadorAtual.municao + 
						"\nNavios destruidos:" + jogadorAtual.naviosDestruidos + 
						"\nSelecione as coordenadas de tiro.");
			}
			else {
				for(int i = 0; i < jogadores.length; i++) {
					jogadores[i].pontuacao += 5 * jogadores[i].naviosDestruidos;
				}
				bubbleSort(jogadores, jogadores.length);
				tela.logDeJogo.adicionarTexto("|----------Fim de jogo----------|");
				for(int i = 0; i < jogadores.length; i++) {
					tela.logDeJogo.adicionarTexto((i + 1) + " ->" + jogadores[i].nome + " - " + jogadores[i].pontuacao);
				}
				tela.trocarControle(new ControleDeFimDeJogo(tela));
			}
		}
		else {
			tela.logDeJogo.adicionarTexto("Coordenada ja utilizada!");
		}
	}
}

class Recurso {
	BufferedImage pegarRecursoImagem(String nomeDaImagem) {
		BufferedImage imagem = null;
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/imagens/" + nomeDaImagem));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagem;
	}
	
	Clip pegarRecursoAudio(String nomeDoAudio) {
		AudioInputStream arquivoDeAudio = null;
		Clip audio = null;
		try {
			arquivoDeAudio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/" + nomeDoAudio));
			audio = AudioSystem.getClip();
			audio.open(arquivoDeAudio);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		return audio;
	}
}

class CampoDeBatalha {
		Espaco[][] espacos = new Espaco[4][4];
		BufferedImage imagem;
		Recurso recurso;
		Tela tela;
	    public CampoDeBatalha(Tela tela){
	    	this.tela = tela;
	    	recurso = new Recurso();
	    	imagem = recurso.pegarRecursoImagem("tabuleiro.png");
	    	BufferedImage imagemDoEspaco = recurso.pegarRecursoImagem("espaco.png");
	        for(int i = 0; i < 4/*Linhas*/; i++){//cria os espaços do campo de batalha
	            for(int j = 0; j < 4/*Colunas*/; j++){
	            	espacos[i][j] = new Espaco(imagemDoEspaco, null, tela);
	            }

	        }
	    }
	    void distribuirObjetos(Objeto objeto, int quantidade){
	        int[] aleatorio = new int[2];
	        int contador = 0;
	        Random random = new Random();
	        while(contador < quantidade){
	            aleatorio[0] = random.nextInt(4);
	            aleatorio[1] = random.nextInt(4);
	            if(espacos[aleatorio[0]][aleatorio[1]].objeto == null){
	                espacos[aleatorio[0]][aleatorio[1]].objeto = objeto;
	                contador++;
	            }
	        }
	    }
	    void reiniciar(){
	        for(int i = 0; i < 4/*Linhas*/; i++){//reinicia o campo de batalha com objetos vazios.
	            for(int j = 0; j < 4/*Colunas*/; j++){
	                espacos[i][j].imagem = recurso.pegarRecursoImagem("espaco.png");;
	                espacos[i][j].objeto = null;
	                espacos[i][j].tiroAcertado = false;
	            }
	        }
	    }
	    void desenhar(){
	        tela.telaDeJogo.contextoDoBufferSecundario.drawImage(imagem, 0, 0, null);
	        for(int i = 0; i < 4; i++){
	            for(int j = 0; j < 4; j++){
	            	tela.telaDeJogo.contextoDoBufferSecundario.drawImage(espacos[i][j].imagem, 64 + j * 100, 64 + i * 100, null);
	            }
	        }
	        tela.telaDeJogo.desenhar();
	    }
	}

class Jogador {
	int id = 0;
	String nome = "Jogador";
	int pontuacao;
	int municao;
	int naviosDestruidos;
    
	public Jogador(int id) {
        this.id = id;
        nome = "Jogador " + (id + 1);
        municao = 5;
        pontuacao = 0;
        naviosDestruidos = 0;
    }
	
	public String toString() {
		String mensagem;
		mensagem = "Nome: " + nome + ", id: " + id + ", pontuacao: " + pontuacao + ", municao: " + municao + ", navios destruidos: " + naviosDestruidos;
		return mensagem;
	}
}

class Objeto {
	String nome = "Objeto";
	BufferedImage imagem = null;
	int municaoExtra = 0;
	int pontosExtra = 0;
	
	public Objeto(String nome, BufferedImage imagem, int municaoExtra, int pontosExtra) {
        this.nome = nome;
        this.imagem = imagem;
        this.municaoExtra = municaoExtra;
        this.pontosExtra = pontosExtra;
    }
}

class Espaco {
	BufferedImage imagem;
	Objeto objeto;
	boolean tiroAcertado;
	Tela tela;
	
    public Espaco(BufferedImage imagem, Objeto objeto, Tela tela) {
    	this.tela = tela;
        this.imagem = imagem;
        this.objeto = objeto;
        tiroAcertado = false;
    }
    
    Objeto revelar() {
    	if(objeto == null) {
    		objeto = new Objeto("erro", new Recurso().pegarRecursoImagem("erro.png"), 0, 0);
    	}
    	imagem = objeto.imagem;
    	tiroAcertado = true;
    	
    	return objeto;
    }
    
    void desenhar() {
    	tela.telaDeJogo.contextoDoBufferSecundario.drawImage(imagem, 0, 0, null);
    }
}

class LogDeJogo extends JPanel{
	
	JTextArea log;
	
	public LogDeJogo() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(480, 100));
		setLayout(new GridLayout(0, 1));
		log = new JTextArea("Bem vindo ao jogo batalha nalval!\nPara comecar o jogo selecione a quantidade de jogadores ao lado.\n");
		log.setEditable(false);
		log.setLineWrap(true);
		JScrollPane jsc = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsc.setPreferredSize(new Dimension(480, 100));
		add(jsc);
	}
	
	void adicionarTexto(String texto) {
		log.append(texto + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}
	
	void limpar() {
		log.setText("");
	}
}
class TelaDeJogo extends JPanel{
	BufferedImage bufferSecundario = new BufferedImage(480, 480, BufferedImage.TYPE_INT_RGB);
	Graphics2D contextoDoBufferSecundario = null;
	
	public TelaDeJogo() {
		setPreferredSize(new Dimension(480, 480));
		contextoDoBufferSecundario = (Graphics2D)bufferSecundario.getGraphics();
	}
	
	public void desenhar() {
		Graphics2D contextoPrincipal = (Graphics2D)getGraphics();
		contextoPrincipal.drawImage(bufferSecundario, 0, 0, null);
	}
}

class ControleDeMenuInicial extends JPanel{
	
	JButton jButton;
	JLabel jLabel;
	JRadioButton jRadioButton;
	ButtonGroup buttonGroup;
	Tela tela;
	
	public ControleDeMenuInicial(Tela tela) {
		this.tela = tela;
		setPreferredSize(new Dimension(220, 560));
		
		buttonGroup = new ButtonGroup();
		
		jLabel = new JLabel("Quantidade de jogadores:");
		add(jLabel);
		
		jRadioButton = new JRadioButton("1 Jogador", true);
		buttonGroup.add(jRadioButton);
		jRadioButton.setMnemonic(1);
		add(jRadioButton);
		
		jRadioButton = new JRadioButton("2 Jogador", false);
		buttonGroup.add(jRadioButton);
		jRadioButton.setMnemonic(2);
		add(jRadioButton);
		
		
		
		jButton = new JButton("Iniciar");
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tela.jogo = new Jogo(tela, buttonGroup.getSelection().getMnemonic());
				tela.jogo.iniciar();
				tela.trocarControle(new ControleDeJogo(tela));
			}
		});
		add(jButton);
	}
}

class ControleDeFimDeJogo extends JPanel{
	
	JButton jButton;
	Tela tela;
	
	public ControleDeFimDeJogo(Tela tela) {
		this.tela = tela;
		setPreferredSize(new Dimension(220, 560));
		
		jButton = new JButton("Novo jogo");
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tela.trocarControle(new ControleDeMenuInicial(tela));
			}
		});
		add(jButton);
	}
}

class ControleDeJogo extends JPanel{
	
	JButton jButton;
	JLabel jLabel;
	JRadioButton jRadioButton;
	ButtonGroup buttonGroupLinhas;
	ButtonGroup buttonGroupColunas;
	JPanel jPanelPrincipal;
	Tela tela;
	
	public ControleDeJogo(Tela tela) {
		this.tela = tela;
		setPreferredSize(new Dimension(220, 560));
		buttonGroupLinhas = new ButtonGroup();
		buttonGroupColunas = new ButtonGroup();
		
		jPanelPrincipal = new JPanel();
		jPanelPrincipal.setLayout(new GridLayout(4, 0));
		
		jLabel = new JLabel("Coordenadas do tiro:");
		jPanelPrincipal.add(jLabel);
		
		//para linhas-----------------------------------------------
		JPanel jPanel = new JPanel();
		
		jLabel = new JLabel("Linha:");
		jPanel.add(jLabel);
		
		jRadioButton = new JRadioButton("A", true);
		buttonGroupLinhas.add(jRadioButton);
		jRadioButton.setMnemonic(0);
		jPanel.add(jRadioButton);
		
		jRadioButton = new JRadioButton("B", false);
		buttonGroupLinhas.add(jRadioButton);
		jRadioButton.setMnemonic(1);
		jPanel.add(jRadioButton);
		
		jRadioButton = new JRadioButton("C", false);
		buttonGroupLinhas.add(jRadioButton);
		jRadioButton.setMnemonic(2);
		jPanel.add(jRadioButton);
		
		jRadioButton = new JRadioButton("D", false);
		buttonGroupLinhas.add(jRadioButton);
		jRadioButton.setMnemonic(3);
		jPanel.add(jRadioButton);
		
		jPanelPrincipal.add(jPanel);
		//------------------------------------------------------
		
		//para colunas-----------------------------------------------
		jPanel = new JPanel();
		
		jLabel = new JLabel("Coluna:");
		jPanel.add(jLabel);
		
		jRadioButton = new JRadioButton("1", true);
		buttonGroupColunas.add(jRadioButton);
		jRadioButton.setMnemonic(0);
		jPanel.add(jRadioButton);
		
		jRadioButton = new JRadioButton("2", false);
		buttonGroupColunas.add(jRadioButton);
		jRadioButton.setMnemonic(1);
		jPanel.add(jRadioButton);
		
		jRadioButton = new JRadioButton("3", false);
		buttonGroupColunas.add(jRadioButton);
		jRadioButton.setMnemonic(2);
		jPanel.add(jRadioButton);
		
		jRadioButton = new JRadioButton("4", false);
		buttonGroupColunas.add(jRadioButton);
		jRadioButton.setMnemonic(3);
		jPanel.add(jRadioButton);
		
		jPanelPrincipal.add(jPanel);
		//------------------------------------------------------
		
		
		
		jButton = new JButton("Atirar");
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tela.jogo.atirar(buttonGroupLinhas.getSelection().getMnemonic(), buttonGroupColunas.getSelection().getMnemonic());
			}
		});
		jPanelPrincipal.add(jButton);
		
		add(jPanelPrincipal);
	}
}

class Tela extends JFrame {
	
	LogDeJogo logDeJogo;
	TelaDeJogo telaDeJogo;
	JPanel controle;
	Jogo jogo;
	
	
	public Tela() {
		setTitle("Batalha naval");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints restricoes = new GridBagConstraints();
		logDeJogo = new LogDeJogo();
		telaDeJogo = new TelaDeJogo();
		controle = new JPanel();
		
		restricoes.gridx = 0;
		restricoes.gridy = 0;
		jPanel.add(logDeJogo, restricoes);
		
		restricoes.gridx = 0;
		restricoes.gridy = 1;
		jPanel.add(telaDeJogo, restricoes);
		
		restricoes.gridx = 1;
		restricoes.gridy = 0;
		restricoes.gridheight = 2;
		controle.add(new ControleDeMenuInicial(this));
		jPanel.add(controle, restricoes);
		
		add(jPanel);
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	void trocarControle(JPanel controle) {
		this.controle.removeAll();
		this.controle.add(controle);
		pack();
		
	}
}

public class Teste8 {

	/*
	
	

	const jogo = {
	    jogadores : [],
		naviosDoObjetivo : 5,
	    jogadorAtual : null,
	    inicializar(_numeroDeJogadores){
	        
	        this.atualizar();
	        this.inicializarOnda();
	        this.jogadorAtual = this.jogadores[0];
	        campoDeBatalha.atualizarTelaDoCampo();
	        document.getElementById("log").innerHTML = this.jogadorAtual.nome + ' escolha as coordenadas para o tiro.';
	        
	    },
	    atualizar(){
	        var infoJogadores = "";
	        this.jogadores.forEach(atualizarInfoDosJogadores);
	        function atualizarInfoDosJogadores(_jogadores){
	            infoJogadores += "<div>" + _jogadores.nome + "<br>Pontos: " + _jogadores.pontuacao + "<br>Municao: " + _jogadores.municao + "</div>";
	        }
	        document.getElementById("infoDosJogadores").innerHTML = infoJogadores;
	        campoDeBatalha.atualizarTelaDoCampo();
	    }
	    executarTiro(_linha, _coluna){
	        for(var i = 0; i < document.getElementsByName("linha").length; i++){//verificar quais dos radio buttons esta marcado para linha
	            if(document.getElementsByName("linha")[i].checked){
	                _linha = document.getElementsByName("linha")[i].value;
	            }
	        }
	        for(var i = 0; i < document.getElementsByName("coluna").length; i++){//verificar quais dos radio buttons esta marcado para coluna
	            if(document.getElementsByName("coluna")[i].checked){
	                _coluna = document.getElementsByName("coluna")[i].value;
	            }
	        }
	        if(!campoDeBatalha.espacos[_linha][_coluna].tiroAcertado){
	            this.jogadorAtual.municao -= 1;
	            var objetoAcertado = campoDeBatalha.espacos[_linha][_coluna];
	            if(objetoAcertado.objeto.nome == "NAVIO INIMIGO"){
	                this.jogadorAtual.naviosDestruidos++;
	            }
	            objetoAcertado.imagem = objetoAcertado.objeto.imagem;
	            this.jogadorAtual.municao += objetoAcertado.objeto.municaoExtra;
	            this.jogadorAtual.pontuacao += objetoAcertado.objeto.pontosExtra;
	            var mensagemDeLog = "";
	            mensagemDeLog += this.jogadorAtual.nome +" acertou " + objetoAcertado.objeto.nome + "!<br>";
	            for(var i = 0, j = 1; i < this.jogadores.length; i++, j++){
	                if(this.jogadorAtual.id + j < this.jogadores.length){
	                    this.jogadorAtual = this.jogadores[this.jogadorAtual.id + j];
	                    if(this.jogadorAtual.municao > 0){
	                        i = this.jogadores.length;
	                    }
	                    else{
	                        if(this.jogadorAtual.id + 1 < this.jogadores.length){
	                            this.jogadorAtual = this.jogadores[this.jogadorAtual.id + 1];
	                        }
	                        else{
	                            this.jogadorAtual = this.jogadores[0];
	                        }
	                    }
	                }
	                else{
	                    this.jogadorAtual = this.jogadores[0];
	                    j = -1;
	                }
	            }
	            if(this.jogadorAtual.municao == 0){
	                mensagemDeLog += "FIM DE JOGO!";
	                document.getElementById("controle").innerHTML = '<input type="button" value="Recomecar jogo" onclick="location.reload()"></input>';
	            }
	            else{
	                mensagemDeLog += this.jogadorAtual.nome + ' escolha as coordenadas para o tiro.';
	            }
	            document.getElementById("log").innerHTML = mensagemDeLog;
	            campoDeBatalha.espacos[_linha][_coluna].tiroAcertado = true;
	            var naviosDestruidos = 0;
	            this.jogadores.forEach(conferirObjetivo);
	            function conferirObjetivo(_jogadores){
	                naviosDestruidos += _jogadores.naviosDestruidos;
	            }
	            if(naviosDestruidos == this.naviosDoObjetivo){
	                campoDeBatalha.reiniciar();
	                this.inicializarOnda();
	                this.jogadores.forEach(prepararJogadoresNovaOnda);
	                function prepararJogadoresNovaOnda(_jogadores){
	                    if(_jogadores.municao > 0){
	                        _jogadores.pontuacao += 20 * _jogadores.naviosDestruidos;
	                        _jogadores.municao = 5;
	                        _jogadores.naviosDestruidos = 0;
	                    }
	                }
	            }
	        }
	        else{
	            mensagemDeLog = this.jogadorAtual.nome + ", um tiro nessas coordenadas já foi feito! Escolha outras coordenadas.";
	            document.getElementById("log").innerHTML = mensagemDeLog;
	        }
	        
	        console.log(naviosDestruidos);
	        this.atualizar();
	    }
	};
	*/
	
	public static void main(String[] args) {
		new Tela();

	}

}

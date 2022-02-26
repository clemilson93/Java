package recursos.aplicacao.telas;

import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import recursos.aplicacao.Principal;

public class AjudaSobreOAuxiliarDeLoteria extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;
	private String mensagem = "";
	
	public AjudaSobreOAuxiliarDeLoteria() {
		try {
			InputStream is = getClass().getResourceAsStream("/recursos/aplicacao/arquivos/texto/AjudaSobreOAuxiliarDeLoteria.txt");
			Scanner myReader = new Scanner(is);
			do {
				mensagem += myReader.nextLine()+"\n";
			}
			while(myReader.hasNextLine());
			myReader.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		jTextArea = new JTextArea(mensagem);
		jTextArea.setEditable(false);
		jScrollPane = new JScrollPane(jTextArea);
		this.add(jScrollPane, BorderLayout.CENTER);
	}

}

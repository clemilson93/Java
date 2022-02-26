package recursos.aplicacao.telas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import recursos.aplicacao.controleDeDados.CartelaDAO;

public class ApagarCartela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private CartelaDAO cartelaDAO;
	private JLabel jLabel;
	private JButton jButton;
	private JTextField jTextField;
	
	public ApagarCartela() {
		cartelaDAO = new CartelaDAO();
		this.setLayout(new GridLayout(3, 1));
		jLabel = new JLabel("Digite nome da cartela:");
		this.add(jLabel);
		jTextField = new JTextField();
		this.add(jTextField);
		jButton = new JButton("Apagar cartela");
		jButton.addActionListener(this);
		this.add(jButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Apagar cartela") {
			try {
				boolean registroApagado = cartelaDAO.apagar(jTextField.getText());
				if(registroApagado) {
					JOptionPane.showMessageDialog(null, "Registro apagado: "+jTextField.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "Registro nao encontrado!");
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}

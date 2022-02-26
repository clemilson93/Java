package recursos.aplicacao.telas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import recursos.aplicacao.controleDeDados.SorteioDAO;
import recursos.aplicacao.objetos.Sorteio;

public class RegistrarSorteio extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel jLabel;
	private JCheckBox jCheckBox;
	private ArrayList<JCheckBox> jCheckBoxList;
	private JButton jButton;
	private JTextField jTextField;
	private JPanel jPanel;
	private GridBagConstraints gridBagConstraints;
	private GridBagLayout gridBagLayout;
	private Sorteio sorteio;
	private SorteioDAO sorteioDAO;
	
	public RegistrarSorteio() {
		jCheckBoxList = new ArrayList<JCheckBox>();
		gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(2, 1));
		jLabel = new JLabel("Digite o numero do sorteio:");
		jPanel.add(jLabel);
		jTextField = new JTextField(6);
		jPanel.add(jTextField);
		jLabel = new JLabel("Selecione os numeros sortiados:");
		jPanel.add(jLabel);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.add(jPanel, gridBagConstraints);
		
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(10, 10));
		for(int i = 0; i < 100; i++) {
			jCheckBox = new JCheckBox(""+i);
			jCheckBoxList.add(jCheckBox);
			jPanel.add(jCheckBox);
		}
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		this.add(jPanel, gridBagConstraints);
		
		jPanel = new JPanel();
		jButton = new JButton("Registrar");
		jButton.addActionListener(this);
		jPanel.add(jButton);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		this.add(jPanel, gridBagConstraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Registrar") {
			int contador = 0;
			int numerosNecessarios = 20;
			int[]numeros = new int[numerosNecessarios];
			for(int i = 0, j = 0; i < jCheckBoxList.size(); i++) {
				if(jCheckBoxList.get(i).isSelected()) {
					contador++;
					if(j < numerosNecessarios) {
						numeros[j] = Integer.valueOf(jCheckBoxList.get(i).getText());
						j++;
					}
				}
			}
			
			if(contador == numerosNecessarios) {
				sorteio = new Sorteio(jTextField.getText(), numeros);
				sorteioDAO = new SorteioDAO();
				try {
					sorteioDAO.adicionar(sorteio);
					JOptionPane.showMessageDialog(null, "Sorteio registrado com sucesso!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Numero do sorteio invalido!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Quantidade de numeros selecionados invalida!\nSelecione: "+numerosNecessarios+" numeros.\nSelecionados: "+contador+" numeros.");
			}
		}
		
	}

}

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
import recursos.aplicacao.controleDeDados.CartelaDAO;
import recursos.aplicacao.objetos.Cartela;

public class AdicionarCartela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gridBagConstraints;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton jButton;
	private JTextField jTextField;
	private JCheckBox jCheckBox;
	private ArrayList<JCheckBox> jCheckBoxList;
	private CartelaDAO cartelaDAO;
	private Cartela cartela;
	
	public AdicionarCartela() {
		cartelaDAO = new CartelaDAO();
		jCheckBoxList = new ArrayList<JCheckBox>();
		gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();
		
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(1, 2));
		jLabel = new JLabel("Digite nome da cartela:");
		jPanel.add(jLabel);
		jTextField = new JTextField();
		jPanel.add(jTextField);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.add(jPanel, gridBagConstraints);
		
		jPanel = new JPanel();
		jLabel = new JLabel("Selecione os numeros desejados:");
		jPanel.add(jLabel);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		this.add(jPanel, gridBagConstraints);
		
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(10, 10));
		for(int i = 0; i < 100; i++) {
			jCheckBox = new JCheckBox(""+i);
			jPanel.add(jCheckBox);
			jCheckBoxList.add(jCheckBox);
		}
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		this.add(jPanel, gridBagConstraints);
		
		jPanel = new JPanel();
		jButton = new JButton("Salvar");
		jPanel.add(jButton);
		jButton.addActionListener(this);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		this.add(jPanel, gridBagConstraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Salvar") {
			int contador = 0;
			int numerosNecessarios = 50;
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
				try {
					cartela = new Cartela(jTextField.getText(), numeros);
					cartelaDAO.adicionar(cartela);
					JOptionPane.showMessageDialog(null, "Cartela salva com sucesso!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Cartela com nome invalido!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Quantidade de numeros selecionados invalida!\nSelecione: "+numerosNecessarios+" numeros.\nSelecionados: "+contador+" numeros.");
			}
		}
		
	}

}

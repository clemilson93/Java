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

public class ConsultarCartela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gridBagConstraints;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton jButton;
	private JButton botaoSalvarAlteracoes;
	private JTextField jTextField;
	private JCheckBox jCheckBox;
	private ArrayList<JCheckBox> jCheckBoxList;
	private CartelaDAO cartelaDAO;
	private Cartela cartela;
	
	public ConsultarCartela() {
		cartelaDAO = new CartelaDAO();
		jCheckBoxList = new ArrayList<JCheckBox>();
		gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();
		
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(1, 3));
		jLabel = new JLabel("Digite o nome da cartela:");
		jPanel.add(jLabel);
		jTextField = new JTextField();
		jPanel.add(jTextField);
		jButton = new JButton("Buscar");
		jPanel.add(jButton);
		jButton.addActionListener(this);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.add(jPanel, gridBagConstraints);
		
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(11, 10));
		for(int i = 0; i < 100; i++) {
			jCheckBox = new JCheckBox(""+i);
			jPanel.add(jCheckBox);
			jCheckBoxList.add(jCheckBox);
		}
		botaoSalvarAlteracoes = new JButton("Salvar alteracoes");
		botaoSalvarAlteracoes.setEnabled(false);
		jPanel.add(botaoSalvarAlteracoes);
		botaoSalvarAlteracoes.addActionListener(this);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		this.add(jPanel, gridBagConstraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand() == "Buscar") {
			try {
				for(int i = 0; i < jCheckBoxList.size(); i++) {
					jCheckBoxList.get(i).setSelected(false);
				}
				cartela = (Cartela)cartelaDAO.consultar(jTextField.getText());
				for(int i = 0; i < cartela.getNumeros().length; i++) {
					jCheckBoxList.get(cartela.getNumeros()[i]).setSelected(true);
				}
				botaoSalvarAlteracoes.setEnabled(true);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Cartela nao encontrada!");
			}
		}
		
		if(e.getActionCommand() == "Salvar alteracoes") {
			int contador = 0;
			for(int i = 0, j = 0; i < jCheckBoxList.size(); i++) {
				if(jCheckBoxList.get(i).isSelected()) {
					contador++;
					if(j < cartela.getNumeros().length) {
						cartela.getNumeros()[j] = Integer.valueOf(jCheckBoxList.get(i).getText());
						j++;
					}
				}
			}
			
			if(contador == cartela.getNumeros().length) {
				try {
					cartelaDAO.adicionar(cartela);
					JOptionPane.showMessageDialog(null, "Alteracoes salvas com sucesso!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				botaoSalvarAlteracoes.setEnabled(false);
				jTextField.setText("");
				for(int i = 0; i < jCheckBoxList.size(); i++) {
					jCheckBoxList.get(i).setSelected(false);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Quantidade de numeros selecionados invalida!\nSelecione: "+cartela.getNumeros().length+" numeros.\nSelecionados: "+contador+" numeros.");
			}
		}
		
	}

}

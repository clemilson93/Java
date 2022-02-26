package recursos.aplicacao.telas;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import recursos.aplicacao.controleDeDados.CartelaDAO;
import recursos.aplicacao.controleDeDados.SorteioDAO;
import recursos.aplicacao.objetos.Cartela;
import recursos.aplicacao.objetos.Sorteio;

public class ListarCartelas extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private ArrayList<Cartela> cartelaLista;
	private CartelaDAO cartelaDAO;
	private JList jList;
	private JButton jButton;
	private JPanel jPanel;
	private JScrollPane jScrollPane;
	private DefaultListModel defaultListModel;
	private Sorteio sorteio;
	private Cartela cartela;
	
	public ListarCartelas() {
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(1, 2));
		defaultListModel = new DefaultListModel();
		cartelaDAO =  new CartelaDAO();
		SorteioDAO sorteioDAO = new SorteioDAO();
		try {
			cartelaLista = cartelaDAO.listar();
			sorteio = sorteioDAO.consultar();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < cartelaLista.size(); i++) {
			defaultListModel.addElement(cartelaLista.get(i).getNome());
		}
		jList = new JList(defaultListModel);
		jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		jList.setLayoutOrientation(JList.VERTICAL);
		jScrollPane = new JScrollPane(jList);
		jScrollPane.setPreferredSize(new Dimension(250, 100));
		jPanel.add(jScrollPane);
		jButton = new JButton("Calcular resultado");
		jButton.addActionListener(this);
		jPanel.add(jButton);
		this.add(jPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(jList.getSelectedIndex() >= 0) {
			cartela = new Cartela();
			try {
				cartela = cartelaDAO.consultar(defaultListModel.get(jList.getSelectedIndex()).toString());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			int acertos = 0;
		    for (int numAp = 0, numSo = 0; numSo < 20; numAp++)
		    {
		        if(numAp >= 50)
		        {
		            numSo++;
		            numAp = 0;
		        }
		        else if(sorteio.getNumerosSorteados()[numSo] == cartela.getNumeros()[numAp])
		        {
		            acertos++;
		            numSo++;
		            numAp = 0;
		        }
		    };
		    JOptionPane.showMessageDialog(null, "Sorteio: "+sorteio.getNumeroDoSorteio()+"\nAcertos: "+acertos);
		}
		else {
			JOptionPane.showMessageDialog(null, "Selecione um item da lista!");
		}
		
	}

}

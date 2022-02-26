package recursos.aplicacao.controleDeDados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import recursos.aplicacao.objetos.Cartela;

public class CartelaDAO {
	private FileOutputStream fos;
	private FileInputStream fis;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public void adicionar(Cartela cartela) throws IOException {
		fos = new FileOutputStream(".\\registros\\cartelas\\"+cartela.getNome());
		oos = new ObjectOutputStream(fos);
		oos.writeObject(cartela);
		oos.close();
	}
	public Cartela consultar(String nome) throws IOException, ClassNotFoundException {
		fis = new FileInputStream(".\\registros\\cartelas\\"+nome);
		ois = new ObjectInputStream(fis);
		Cartela cartela = (Cartela)ois.readObject();
		ois.close();
		return cartela;
	}
	public ArrayList<Cartela> listar() throws ClassNotFoundException, IOException {
		String[] cartelaNomes = new File(".\\registros\\cartelas\\").list();
		ArrayList<Cartela> cartelaObjetos = new ArrayList<Cartela>();
		for(int i = 0; i < cartelaNomes.length; i++) {
			fis = new FileInputStream(".\\registros\\cartelas\\"+cartelaNomes[i]);
			ois = new ObjectInputStream(fis);
			cartelaObjetos.add((Cartela)ois.readObject());
			ois.close();
		}
		return cartelaObjetos;
	}
	public boolean apagar(String nome) throws IOException, ClassNotFoundException {
		File cartela = new File(".\\registros\\cartelas\\"+nome);
		return cartela.delete();
	}
}

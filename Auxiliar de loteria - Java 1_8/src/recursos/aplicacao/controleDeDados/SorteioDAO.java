package recursos.aplicacao.controleDeDados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import recursos.aplicacao.objetos.Sorteio;

public class SorteioDAO {
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private FileInputStream fis;
	private ObjectInputStream ois;
	
	public void adicionar(Sorteio sorteio) throws IOException {
		fos = new FileOutputStream(".\\registros\\ultimoSorteio");
		oos = new ObjectOutputStream(fos);
		if(sorteio.getNumeroDoSorteio().trim().length() == 0) {
			sorteio.setNumeroDoSorteio("desconhecido");
		}
		oos.writeObject(sorteio);
		oos.close();
	}
	
	public Sorteio consultar() throws IOException, ClassNotFoundException {
		fis = new FileInputStream(".\\registros\\ultimoSorteio");
		ois = new ObjectInputStream(fis);
		Sorteio sorteio = (Sorteio)ois.readObject();
		ois.close();
		return sorteio;
	}

}

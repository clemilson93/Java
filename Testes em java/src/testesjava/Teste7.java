package testesjava;

import java.io.File;
import java.io.IOException;

public class Teste7 {

	public static void main(String[] args) {
		File arquivoOuDiretorio;
		boolean criado;
		arquivoOuDiretorio = new File("Recursos/Imagens/Tipo1");
		criado = arquivoOuDiretorio.mkdirs();
		System.out.println("Diretorio: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
		arquivoOuDiretorio = new File("Recursos/Sons/Tipo1");
		criado = arquivoOuDiretorio.mkdirs();
		System.out.println("Diretorio: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
		arquivoOuDiretorio = new File("Recursos/Textos/Tipo1");
		criado = arquivoOuDiretorio.mkdirs();
		System.out.println("Diretorio: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
		arquivoOuDiretorio = new File("Recursos/Imagens/Tipo2");
		criado = arquivoOuDiretorio.mkdirs();
		System.out.println("Diretorio: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
		arquivoOuDiretorio = new File("Recursos/Sons/Tipo2");
		criado = arquivoOuDiretorio.mkdirs();
		System.out.println("Diretorio: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
		arquivoOuDiretorio = new File("Recursos/Textos/Tipo2");
		criado = arquivoOuDiretorio.mkdirs();
		System.out.println("Diretorio: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
		try {
			arquivoOuDiretorio = new File("Recursos/Imagens/Tipo1/Imagem1.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Imagens/Tipo1/Imagem2.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Sons/Tipo1/Som1.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Sons/Tipo1/Som2.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Textos/Tipo1/Texto1.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Textos/Tipo1/Texto2.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Imagens/Tipo2/Imagem3.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Imagens/Tipo2/Imagem4.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Sons/Tipo2/Som3.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Sons/Tipo2/Som4.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Textos/Tipo2/Texto3.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
			
			arquivoOuDiretorio = new File("Recursos/Textos/Tipo2/Texto4.txt");
			criado = arquivoOuDiretorio.createNewFile();
			System.out.println("Arquivo: "+ arquivoOuDiretorio.getName()+", " + criado + ".");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		arquivoOuDiretorio = new File("Recursos");
		String[] diretorios1 = arquivoOuDiretorio.list();
		for(String s : diretorios1) {
			System.out.println(s);
		}
		File[] diretorios2 = arquivoOuDiretorio.listFiles();
		for(File f : diretorios2) {
			System.out.println(f);
		}
	}

}

package comandosbreakcontinuereturn;

public class ComandoContinue {

	public static void main(String[] args) {
		//continue sem rótulo
		int contador = 0;
		while(true) {
			contador++;
			System.out.println("Contador " + contador);
			if(contador < 10) {
				continue;
			}
			System.out.println("Finalizado");
			break;
		}
		//continue com rótulo
		String texto = "Estou programando em Java hoje";
		String subTexto = "mand";
		boolean encontrado = false;

		int max = texto.length() - 
				subTexto.length();

		buscar:
			for (int i = 0; i <= max; i++) {
				int n = subTexto.length();
				int j = i;
				int k = 0;
				while (n-- != 0) {
					if (texto.charAt(j++) != subTexto.charAt(k++)) {
						continue buscar;
					}
				}
				encontrado = true;
				break buscar;
			}
		System.out.println(encontrado ? "Encontrado" : "Não encontrado");
	}

}

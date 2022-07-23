package comandosbreakcontinuereturn;

public class ComandoBreak {

	public static void main(String[] args) {
		//break sem rótulo
		int[] numeros = {13, 90, 50, 100, 20, 300, 43};
		int numeroAlvo = 1;
		int i;
		boolean encontrado = false;

		for(i = 0; i < numeros.length; i++) {
			if(numeros[i] == numeroAlvo) {
				encontrado = true;
				break;
			}
		}
		if (encontrado == true) {
			System.out.println("Econtrado " + numeroAlvo + " no indice " + i);
		} else {
			System.out.println(numeroAlvo + " não está no array.");
		}
		//break com rótulo
		int[][] outroNumeros = {
				{13, 90, 50, 100},
				{20, 300, 43, 87},
				{54, 68, 12, 74},
				{66, 72, 98, 93}
				};
		int outroNumeroAlvo = 12;
		int outroI;
		int j = 0;
		boolean outroEncontrado = false;

		buscar:
	        for (outroI = 0; outroI < outroNumeros.length; outroI++) {
	            for (j = 0; j < outroNumeros[outroI].length; j++) {
	                if (outroNumeros[outroI][j] == outroNumeroAlvo) {
	                	outroEncontrado = true;
	                    break buscar;
	                }
	            }
	        }

	        if (outroEncontrado == true) {
	            System.out.println("Econtrado " + outroNumeroAlvo + " em " + outroI + ", " + j);
	        } else {
	            System.out.println(outroNumeroAlvo + " não está no array");
	        }
	}

}

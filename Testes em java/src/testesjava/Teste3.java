package testesjava;

public class Teste3 {
	public static void main(String[]args) {
		int largura_16_9 = 1920, altura_16_9 = 1080;
		int largura_4_3 = 1440, altura_4_3 = 1080;
		float proporcao_16_9 = 16f/9f, proporcao_4_3 = 4f/3f;
		System.out.println("Proporcao 16:9 => "+proporcao_16_9);
		System.out.println("Proporcao 4:3 => "+proporcao_4_3);
		
		for(int i = 1; i < 4; i++) {
			//largura / altura = proporcao
			//altura = largura * proporcao_16_9;
			//largura = altura / proporcao_16_9;
			System.out.println("Resolucao " + i + "\nWide on: " + (int)(((float)altura_16_9 / i) * proporcao_16_9) + "x" + (int)(((float)largura_16_9 / i) / proporcao_16_9));
			System.out.println("Wide off: " + (int)(((float)altura_4_3 / i) * proporcao_4_3) + "x" + (int)(((float)largura_4_3 / i) / proporcao_4_3));
		}
		
		int num1 = -384, num2 = -408;
		if(num1 > num2) {
			System.out.println("Num1 maior que num2");
		}
		if(num2 > num1) {
			System.out.println("Num2 maior que num1");
		}
		if(num2 == num1) {
			System.out.println("Num2 igual num1");
		}
	}

}

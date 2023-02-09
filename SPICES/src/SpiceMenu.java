import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase per mostrar menu per pantalla
 */
public class SpiceMenu {
	private int option;

	public SpiceMenu() {
		super();
	}
	/***
	 * Mostra per pantalla el menu principal
	 */
	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {

			System.out.println(" \nMENU PRINCIPAL \n");

			System.out.println("1. Mostra tota la informació");
			System.out.println("2. Mostra especia per país");
			System.out.println("3. Mostra especia per format");
			System.out.println("4. Mostra especia per cuina per països");
			System.out.println("5. Mostra especia per paraula/text clau");
			System.out.println("6. Afegir nova especia a la DDBB");
			System.out.println("7. Modifica el nom d'una especia");
			System.out.println("8. Borrar totes les taules de la DDBB");
			System.out.println("9. Borrar una especia per Id");
			System.out.println("10. Carregar totes les dades del csv especies");
			System.out.println("11. Carregar les taules de la DDBB");
			System.out.println("12. Carregar totes les dades del csv especies");
			System.out.println("0. Sortir");
			System.out.println("Esculli opció: ");
			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no vàlid");
				e.printStackTrace();

			}

		} while (option != 0 && option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
				&& option != 8 && option != 9 && option != 10 && option != 11 && option != 12);

		return option;
	}

}

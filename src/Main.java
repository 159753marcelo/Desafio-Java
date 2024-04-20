import Models.Conversor;
import Models.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Conversor conversor = new Conversor();
        String userChoice = "";

        Menu menu = new Menu();

        menu.printMenuPrincipal();
        System.out.println("Digite a opção desejada");

        while (menu.isSeguir()) {

            userChoice = sc.nextLine();
            menu.opcaoMenuPrincipal(userChoice);

            Menu.firstMenu(userChoice, menu, sc, conversor);
        }
    }

}

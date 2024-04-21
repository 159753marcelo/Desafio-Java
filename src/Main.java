import Models.Conversor;
import Models.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Conversor conversor = new Conversor();
        Menu menu = new Menu();

        do {
            menu.firstMenu(sc, conversor);
        } while (menu.isSeguir());
    }

}

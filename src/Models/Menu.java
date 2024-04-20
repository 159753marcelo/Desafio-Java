package Models;

// ═ ║ ╒ ╓ ╔ ╕ ╖ ╗ ╘ ╙ ╚ ╛ ╜ ╝ ╞ ╟
// ╠ ╡ ╢ ╣ ╤ ╥ ╦ ╧ ╨ ╩ ╪ ╫ ╬

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Menu {
    private boolean seguir = true;
    private String moedaBase;
    private String moedaAlvo;
    private String principal = """
            ╔═══════════════════════════════════════════════════════╗
            ║=-=-=-=-=-=-=-=- Conversor de Moedas -=-=-=-=-=-=-=-=-=║
            ║                 $ $ $ $ $ $ $ $ $ $                   ║
            ╠═══════════════════════════════════════════════════════╣
            ║ 1) Dólar =>> Peso argentino                           ║
            ║ 2) Peso Argentino =>> Dólar                           ║
            ║ 3) Dólar Americano =>> Real brasileiro                ║
            ║ 4) Real brasileiro =>> Dólar                          ║
            ║ 5) Dólar =>> Peso Colombiano                          ║
            ║ 6) Peso Colombiano => >Dólar                          ║
            ║ 7) Conversão customizada                              ║
            ║ 8) Sair                                               ║
            ╚═══════════════════════════════════════════════════════╝
            """;

    private String custom = """
            ╔═══════════════════════════════════════════════════════╗
            ║=-=-=-=-=-=-=-=- Conversor de Moedas -=-=-=-=-=-=-=-=-=║
            ║                 $ $ $ $ $ $ $ $ $ $                   ║
            ╠═══════════════════════════════════════════════════════╣
            ║ 1) Real                                               ║
            ║ 2) Peso Argentino                                     ║
            ║ 3) Peso Uruguaio                                      ║
            ║ 4) Peso Colombiano                                    ║
            ║ 5) Peso Mexicano                                      ║
            ║ 6) Sol Peruano                                        ║
            ║ 7) Dólar Americano                                    ║
            ║ 8) Euro                                               ║
            ║ 9) Voltar                                             ║
            ║ 10) Sair                                              ║
            ╚═══════════════════════════════════════════════════════╝
            """;

    private String askChoice = """
            ╔═══════════════════════════════════════════════════════╗
            ║ Digite a opção desejada:                              ║
            ╚═══════════════════════════════════════════════════════╝
            """;


    private int userChoice;

    public static void firstMenu(String userChoice, Menu menu, Scanner sc, Conversor conversor) throws IOException, InterruptedException {
        if (userChoice.equals("8")) {
            System.out.println("Saindo do Conversor de Moedas. Até logo!");
            menu.setSeguir(false);
        } else if (userChoice.equals("7")) {
            menu.printMenuCustom();
            System.out.println("Digite a opção da moeda que deseja converter");
            String userCustom = sc.nextLine();
            String moedaBase = menu.opcaoMenuCustom(userCustom);
            firstCustomMenu(userCustom, menu, sc, conversor, moedaBase);
        } else if (Stream.of("1", "2", "3", "4", "5", "6").anyMatch(userChoice::equals)) {
            System.out.println("Digite o valor a ser convertido:");
            try {
                double valor = sc.nextDouble();
                double taxa = conversor.pegaTaxaConversao(menu.getMoedaBase(), menu.getMoedaAlvo());
                System.out.println("O valor de " + menu.getMoedaBase() + " " + valor + " em " + menu.getMoedaAlvo() +
                        " é de: " + conversor.converteValor(valor, taxa));
                userChoice = "";
                menu.printMenuPrincipal();
                System.out.println("Digite a opção desejada");
            } catch (InputMismatchException e) {
                userChoice = "";
                System.out.println("Valor inválido, tente novamente");
            }
        }
    }

    private static void firstCustomMenu(String userCustom, Menu menu, Scanner sc, Conversor conversor, String moedaBase) throws IOException, InterruptedException {
        String userChoice;
        if (userCustom.equals("10")) {
            System.out.println("Saindo do Conversor de Moedas. Até logo!");
            menu.setSeguir(false);
        } else if (userCustom.equals("9")) {
            System.out.println("Retornando ao menu principal");
            menu.printMenuPrincipal();
            userChoice = "";
            userCustom = "";
            System.out.println("Digite a opção desejada");
        } else if (Stream.of("1", "2", "3", "4", "5", "6", "7", "8").anyMatch(userCustom::equals)) {
            System.out.println("Digite a opção da moeda para qual converter");
            userCustom = sc.nextLine();
            String moedaAlvo = menu.opcaoMenuCustom(userCustom);
            secondCustomMenu(userCustom, menu, sc, conversor, moedaBase, moedaAlvo);
        } else {
            userCustom = "";
            System.out.println("Opção inválida, tente novamente.");
            menu.printMenuPrincipal();
            System.out.println("Digite a opção desejada");
        }
    }

    private static void secondCustomMenu(String userCustom, Menu menu, Scanner sc, Conversor conversor, String moedaBase, String moedaAlvo) throws IOException, InterruptedException {
        String userChoice;
        if (userCustom.equals("10")) {
            System.out.println("Saindo do Conversor de Moedas. Até logo!");
            menu.setSeguir(false);
        } else if (userCustom.equals("9")) {
            System.out.println("Retornando ao menu principal");
            menu.printMenuPrincipal();
            userChoice = "";
            userCustom = "";
            System.out.println("Digite a opção desejada");
        } else {
            System.out.println("Digite o valor a ser convertido:");
            try {
                double valor = sc.nextDouble();
                double taxa = conversor.pegaTaxaConversao(moedaBase, moedaAlvo);
                System.out.println("O valor de " + moedaBase + " " + valor + " em " + moedaBase +
                        " é de: " + conversor.converteValor(valor, taxa));
                menu.printMenuPrincipal();
                System.out.println("Digite a opção desejada");
            } catch (InputMismatchException e) {
                userCustom = "";
                System.out.println("Valor inválido, tente novamente");
                menu.printMenuPrincipal();
            }
        }
    }

    public void opcaoMenuPrincipal(String opcao) {
        String base = "";
        String alvo = "";
        switch (opcao) {
            case "1":
                base = "USD";
                alvo = "ARS";
                break;
            case "2":
                base = "ARS";
                alvo = "USD";
                break;
            case "3":
                base = "USD";
                alvo = "BRL";
                break;
            case "4":
                base = "BRL";
                alvo = "USD";
                break;
            case "5":
                base = "USD";
                alvo = "COP";
                break;
            case "6":
                base = "COP";
                alvo = "USD";
                break;
            case "7":
                base = "custom";
                break;
            case "8":
                base = "sair";
                break;
        }
        this.setMoedaAlvo(alvo);
        this.setMoedaBase(base);
    }

    public String opcaoMenuCustom(String opcao) {
        String choice = "";
        switch (opcao) {
            case "1":
                choice = "BRL";
                break;
            case "2":
                choice = "ARS";
                break;
            case "3":
                choice = "UYU";
                break;
            case "4":
                choice = "COP";
                break;
            case "5":
                choice = "MXN";
                break;
            case "6":
                choice = "PEN";
                break;
            case "7":
                choice = "USD";
                break;
            case "8":
                choice = "EUR";
                break;
            case "9":
                choice = "back";
                break;
            case "10":
                choice = "exit";
                this.seguir = false;
        }
        return choice;
    }

    public void printMenuPrincipal() {
        System.out.println(this.getPrincipal());
    }

    public void printMenuCustom() {
        System.out.println(this.getCustom());
    }

    public void printMenuAsk() {
        System.out.println(this.getAskChoice());
    }

    public String getPrincipal() {
        return principal;
    }

    public String getCustom() {
        return custom;
    }

    public String getAskChoice() {
        return askChoice;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public boolean isSeguir() {
        return seguir;
    }

    public void setSeguir(boolean seguir) {
        this.seguir = seguir;
    }

    public String getMoedaBase() {
        return moedaBase;
    }

    public void setMoedaBase(String moedaBase) {
        this.moedaBase = moedaBase;
    }

    public String getMoedaAlvo() {
        return moedaAlvo;
    }

    public void setMoedaAlvo(String moedaAlvo) {
        this.moedaAlvo = moedaAlvo;
    }

}
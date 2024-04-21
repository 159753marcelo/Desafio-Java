package Models;

// ═ ║ ╒ ╓ ╔ ╕ ╖ ╗ ╘ ╙ ╚ ╛ ╜ ╝ ╞ ╟
// ╠ ╡ ╢ ╣ ╤ ╥ ╦ ╧ ╨ ╩ ╪ ╫ ╬

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private boolean seguir = true;
    private String moedaBase;
    private String moedaAlvo;
    private String principal = """
            ╔═══════════════════════════════════════════════════════╗
            ║                 $ $ $ $ $ $ $ $ $ $                   ║
            ║=-=-=-=-=-=-=-=- Conversor de Moedas -=-=-=-=-=-=-=-=-=║
            ║                 $ $ $ $ $ $ $ $ $ $                   ║
            ╠═══════════════════════════════════════════════════════╣
            ║ 1) Dólar =>> Peso argentino                           ║
            ║ 2) Peso Argentino =>> Dólar                           ║
            ║ 3) Dólar =>> Real brasileiro                          ║
            ║ 4) Real brasileiro =>> Dólar                          ║
            ║ 5) Dólar =>> Peso Colombiano                          ║
            ║ 6) Peso Colombiano =>> Dólar                          ║
            ║ 7) Conversão customizada                              ║
            ║ 8) Sair                                               ║
            ╚═══════════════════════════════════════════════════════╝
            """;

    private String custom = """
            ╔═══════════════════════════════════════════════════════╗
            ║                 $ $ $ $ $ $ $ $ $ $                   ║
            ║=-=-=-=-=-=-=-=- Conversor de Moedas -=-=-=-=-=-=-=-=-=║
            ║                 $ $ $ $ $ $ $ $ $ $                   ║
            ╠═══════════════════════════════════════════════════════╣
            ║ 1) Real                                               ║
            ║ 2) Peso Argentino                                     ║
            ║ 3) Peso Uruguaio                                      ║
            ║ 4) Peso Colombiano                                    ║
            ║ 5) Peso Mexicano                                      ║
            ║ 6) Sol Peruano                                        ║
            ║ 7) Dólar                                              ║
            ║ 8) Euro                                               ║
            ║ 9) Voltar                                             ║
            ║ 10) Sair                                              ║
            ╚═══════════════════════════════════════════════════════╝
            """;

//    private String askChoice = """
//            ╔═══════════════════════════════════════════════════════╗
//            ║ Digite a opção desejada:                              ║
//            ╚═══════════════════════════════════════════════════════╝
//            """;

    public void firstMenu(Scanner sc, Conversor conversor) throws IOException, InterruptedException {
        this.printMenuPrincipal();
        System.out.println("Digite a opção desejada");
        String userChoice = sc.nextLine();
        this.opcaoMenuPrincipal(userChoice);
        if (userChoice.equals("8")) {
            System.out.println("Saindo do Conversor de Moedas. Até logo!");
            this.setSeguir(false);
        } else if (userChoice.equals("7")) {
            this.printMenuCustom();
            System.out.println("Digite a opção da moeda que deseja converter");
            String userCustom = sc.nextLine();
            String moedaBase = this.opcaoMenuCustom(userCustom);
            firstCustomMenu(userCustom, sc, conversor, moedaBase);
        } else if (List.of("1", "2", "3", "4", "5", "6").contains(userChoice)) {
            System.out.println("Digite o valor a ser convertido:");
            String valorString = sc.nextLine();
            try {
                double valor = Double.parseDouble(valorString);
                double taxa = conversor.pegaTaxaConversao(this.getMoedaBase(), this.getMoedaAlvo());
                System.out.println("O valor de " + this.getMoedaBase() + " " + valor + " em " + this.getMoedaAlvo() +
                        " é de: " + conversor.converteValor(valor, taxa));
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Valor inválido, tente novamente");
            }
        } else {
            System.out.println("Opção inválida, tente novamente.");
        }
    }

    private void firstCustomMenu(String userCustom, Scanner sc, Conversor conversor, String moedaBase) throws IOException, InterruptedException {
        if (userCustom.equals("10")) {
            System.out.println("Saindo do Conversor de Moedas. Até logo!");
            this.setSeguir(false);
        } else if (userCustom.equals("9")) {
            System.out.println("Retornando ao menu principal");
        } else if (List.of("1", "2", "3", "4", "5", "6", "7", "8").contains(userCustom)) {
            System.out.println("Digite a opção da moeda para qual converter");
            userCustom = sc.nextLine();
            String moedaAlvo = this.opcaoMenuCustom(userCustom);
            secondCustomMenu(userCustom, sc, conversor, moedaBase, moedaAlvo);
        } else {
            System.out.println("Opção inválida, tente novamente.");
        }
    }

    private void secondCustomMenu(String userCustom, Scanner sc, Conversor conversor, String moedaBase, String moedaAlvo) throws IOException, InterruptedException {
        if (userCustom.equals("10")) {
            System.out.println("Saindo do Conversor de Moedas. Até logo!");
            this.setSeguir(false);
        } else if (userCustom.equals("9")) {
            System.out.println("Retornando ao menu principal");
        } else if (List.of("1", "2", "3", "4", "5", "6", "7", "8").contains(userCustom)) {
            System.out.println("Digite o valor a ser convertido:");
            String valorString = sc.nextLine();
            try {
                double valor = Double.parseDouble(valorString);
                double taxa = conversor.pegaTaxaConversao(moedaBase, moedaAlvo);
                System.out.println("O valor de " + moedaBase + " " + valor + " em " + moedaAlvo +
                        " é de: " + conversor.converteValor(valor, taxa));
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Valor inválido, tente novamente");
            }
        } else {
            System.out.println("Opção inválida, tente novamente.");
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

    public String getPrincipal() {
        return principal;
    }

    public String getCustom() {
        return custom;
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
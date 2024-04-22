package Models;

import java.util.ArrayList;

public class ConversionData {
    private String moedaBase;
    private String moedaAlvo;
    private double taxa;
    private double valor;




    public ConversionData(String moedaBase, String moedaAlvo, double taxa, double valor) {
        this.moedaBase = moedaBase;
        this.moedaAlvo = moedaAlvo;
        this.taxa = taxa;
        this.valor = valor;
    }

    public String getMoedaBase() {
        return moedaBase;
    }

    public String getMoedaAlvo() {
        return moedaAlvo;
    }

    public double getTaxa() {
        return taxa;
    }

    public double getValor() {
        return valor;
    }
}

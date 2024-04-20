package Models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class Conversor {
    private String urlInicial = "";
    private String APIKEY = "";
    private String moedaBase = "";
    private String moedaAlvo = "";
    private double valor = 0;
    private double valorConvertido = 0;
    private double taxaConversao = 0;


    public double pegaTaxaConversao(String moedaBase, String moedaAlvo) throws IOException, InterruptedException {
        urlInicial = "https://v6.exchangerate-api.com/v6/" + APIKEY + "/latest/" + moedaBase;
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(urlInicial)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Gson gson = new Gson();

        // define tipo esperado no Json como um mapa com String e object
        TypeToken<Map<String, Object>> outerTypeToken = new TypeToken<>() {};
        // cria um mapa pro Gson, especificando o tipo definido antes e joga tudo nele
        Map<String, Object> dataMap = gson.fromJson(json, outerTypeToken.getType());
        // cria outro mapa para jogar somente o que interessa, as taxas de conversão
        HashMap<String, Double> conversionRatesMap = new HashMap<>();
        // itera pelo dataMap completo e identifica a chave "conversion_rates". Passa cada entrada no mapa
        // conversion_rates para o mapa criado anteriormente (conversionRatesMap), pegando a String de entrada e o
        // valor dela.
        conversionRatesMap.putAll(((Map<String, Double>) dataMap.get("conversion_rates")));

        // pega a taxa escolhida e retorna seu valor
        double taxa = conversionRatesMap.get(moedaAlvo);


        return taxa;
    }

    public double converteValor(double valor, double taxa){
        return valor * taxa;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorConvertido() {
        this.valorConvertido = this.valor * this.taxaConversao;
        return valorConvertido;
    }

    public double getTaxaConversao() {
        return taxaConversao;
    }

    public void setTaxaConversao(double taxaConversao) {
        this.taxaConversao = taxaConversao;
    }


}
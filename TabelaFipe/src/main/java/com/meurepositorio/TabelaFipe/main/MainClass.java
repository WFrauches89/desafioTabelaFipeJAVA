package com.meurepositorio.TabelaFipe.main;

import com.meurepositorio.TabelaFipe.enums.TypeOf;
import com.meurepositorio.TabelaFipe.model.Details;
import com.meurepositorio.TabelaFipe.model.Vehicle;
import com.meurepositorio.TabelaFipe.model.VehicleModel;
import com.meurepositorio.TabelaFipe.service.ConsumerAPI;
import com.meurepositorio.TabelaFipe.service.DataConvert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainClass {

    private Scanner getInsert = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumerAPI consumer = new ConsumerAPI();
    private DataConvert dataConvert = new DataConvert();

    public void menu() {

        StringBuilder menu = new StringBuilder("\n*** Tipos de veículos que você pode consultar: ***\n\n");

        for (TypeOf t : TypeOf.values()) {
            menu.append(t.getDescription()).append("\n");
        }
        menu.append("\nDigite uma das opções para consulta: ");

        System.out.println(menu.toString());

        var choice = getInsert.nextLine();

        String address;

        switch (choice.toLowerCase()) {
            case "car", "1" -> address = URL_BASE + "carros/marcas";
            case "mot", "2" -> address = URL_BASE + "motos/marcas";
            case "cam", "3" -> address = URL_BASE + "caminhoes/marcas";
            default -> {
                System.out.println("Digite um código válido");
                return;
            }
        }


        var json = consumer.getData(address);

        var brands = dataConvert.getList(json, Details.class);
        brands.stream()

                .sorted(Comparator.comparing(Details::code))

                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");

        var brandCode = getInsert.nextLine();

        address = address + "/" + brandCode + "/modelos";
        json = consumer.getData(address);
        System.out.println("Log json: "+json);
        var modelList = dataConvert.getDatas(json, VehicleModel.class);
        System.out.println("Log modellist: "+modelList);

        modelList.modelos().stream()
                .peek(System.out::println)
                .sorted(Comparator.comparing(Details::code))
                .forEach(System.out::println);

        System.out.println("\nDigite o nome do carro ou parte do nome a ser buscado");
        var nameVehicle = getInsert.nextLine();

        List<Details> modelsFilter = modelList.modelos().stream()
                .filter(m -> m.model().toLowerCase().contains(nameVehicle.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelsFilter.forEach(System.out::println);

        System.out.println("Digite o código: ");
        var code = getInsert.nextLine();

        address = address + "/" + code + "/anos";
        json = consumer.getData(address);
        List<Details> years = dataConvert.getList(json, Details.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var yearCode = address + "/" + years.get(i).code();
            json = consumer.getData(yearCode);
            Vehicle vehicle = dataConvert.getDatas(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        vehicles.forEach(System.out::println);

    }

}

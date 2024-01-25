package com.meurepositorio.TabelaFipe.enums;

public enum TypeOf {

    CARRO("01 - Carro"),
    MOTO("02 - Moto"),
    CAMINHAO("03 - Caminhão");

    private String description;

    TypeOf(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

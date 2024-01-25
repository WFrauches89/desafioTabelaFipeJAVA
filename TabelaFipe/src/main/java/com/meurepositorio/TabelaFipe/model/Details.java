package com.meurepositorio.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Details(@JsonAlias("codigo") String code,
                      @JsonAlias("nome") String model) {

}

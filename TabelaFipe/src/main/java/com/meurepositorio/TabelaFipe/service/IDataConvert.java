package com.meurepositorio.TabelaFipe.service;

import java.util.List;

public interface IDataConvert {

    <T> T getDatas(String json, Class<T> classNew);

    <T> List<T> getList(String json, Class<T> classNew);

}

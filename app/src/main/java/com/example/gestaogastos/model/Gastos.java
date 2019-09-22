package com.example.gestaogastos.model;

import java.io.Serializable;
import java.util.Date;

public class Gastos implements Serializable {

    private Long id;
    private Double valor;
    private String data;
    private String tipo;
    private String local;

    @Override
    public String toString() {
        return valor.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}

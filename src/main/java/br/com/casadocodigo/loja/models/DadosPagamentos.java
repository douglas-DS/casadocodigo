package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class DadosPagamentos {

    private BigDecimal value;

    public DadosPagamentos() {
    }

    public DadosPagamentos(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}

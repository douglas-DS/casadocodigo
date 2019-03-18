package br.com.casadocodigo.loja.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class DadosPagamentos {

    private BigDecimal value;

    public DadosPagamentos() {
    }

    public DadosPagamentos(BigDecimal value) {
        this.value = value;
    }
}

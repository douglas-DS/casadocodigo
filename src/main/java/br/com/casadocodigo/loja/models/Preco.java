package br.com.casadocodigo.loja.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter @Setter @ToString
public class Preco {

    private BigDecimal valor;
    private TipoPreco tipo;

}

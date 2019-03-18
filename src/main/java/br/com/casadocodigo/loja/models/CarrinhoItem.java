package br.com.casadocodigo.loja.models;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
public class CarrinhoItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Produto produto;
    private TipoPreco tipoPreco;

    public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
        this.produto = produto;
        this.tipoPreco = tipoPreco;
    }

    public BigDecimal getTotal(int quantidade) {
        return this.getPreco().multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getPreco() {
        return produto.precoPara(tipoPreco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoItem that = (CarrinhoItem) o;
        return Objects.equals(produto, that.produto) &&
                tipoPreco == that.tipoPreco;
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, tipoPreco);
    }

}

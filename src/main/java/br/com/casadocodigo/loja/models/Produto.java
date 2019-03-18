package br.com.casadocodigo.loja.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, message = "Campo 'título' deve ser preenchido corretamente! (Mínimo: 2 caracteres)")
    private String titulo;

    @Lob
    @NotNull
    @Size(min = 3, message = "Campo 'descrição' deve ser preenchido corretamente! (Mínimo: 3 caracteres)")
    private String descricao;

    @NotNull(message = "Campo 'páginas' está vazio! Preencha corretamente")
    @Min(1)
    private Integer paginas;

    @DateTimeFormat
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Campo não pode estar vazio!")
    private Calendar dataLancamento;

    @ElementCollection
    private List<Preco> precos;

    private String sumarioPath;

    public BigDecimal precoPara(TipoPreco tipoPreco) {
        return precos.stream()
                .filter(preco -> preco.getTipo().equals(tipoPreco))
                .findFirst()
                .get()
                .getValor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "titulo='" + titulo + '\'' +
                ", precos=" + precos +
                '}';
    }
}

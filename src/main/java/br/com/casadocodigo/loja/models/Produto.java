package br.com.casadocodigo.loja.models;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Calendar getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Calendar dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public List<Preco> getPrecos() {
        return precos;
    }

    public void setPrecos(List<Preco> precos) {
        this.precos = precos;
    }

    public String getSumarioPath() {
        return sumarioPath;
    }

    public void setSumarioPath(String sumarioPath) {
        this.sumarioPath = sumarioPath;
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

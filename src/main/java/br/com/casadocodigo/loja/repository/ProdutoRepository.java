package br.com.casadocodigo.loja.repository;

import br.com.casadocodigo.loja.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findProdutoById(Integer id);

}

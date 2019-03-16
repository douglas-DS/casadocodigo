package br.com.casadocodigo.loja.controllers;


import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("carrinho")
@RequestScope
public class CarrinhoComprasController {

    private ProdutoRepository repository;
    private CarrinhoCompras carrinho;

    @Autowired
    public CarrinhoComprasController(ProdutoRepository repository, CarrinhoCompras carrinho) {
        this.repository = repository;
        this.carrinho = carrinho;
    }

    @RequestMapping("add")
    public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
        ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");

        Produto produto = repository.findProdutoById(produtoId);

        CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
        System.out.println(carrinhoItem.toString());

        carrinho.add(carrinhoItem);

        return modelAndView;
    }

    @GetMapping
    public String itens() {
        return "carrinho/itens";
    }

    @PostMapping("remover/{produtoId}/{tipoPreco}")
    public ModelAndView remover(@PathVariable Integer produtoId, @PathVariable TipoPreco tipoPreco) {
        carrinho.remover(produtoId, tipoPreco);

        return new ModelAndView("redirect:/carrinho");
    }

    @PostMapping("atualizar/{produtoId}/{tipoPreco}")
        public ModelAndView atualizar(@PathVariable Integer produtoId, @PathVariable TipoPreco tipoPreco, Integer quantidade) {
            Produto produto = repository.findProdutoById(produtoId)     ;
            CarrinhoItem item = new CarrinhoItem(produto, tipoPreco);

            carrinho.atualizar(item, quantidade);

            return new ModelAndView("redirect:/carrinho");
        }


}

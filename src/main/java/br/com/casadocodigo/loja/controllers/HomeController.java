package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private ProdutoRepository repository;

    @Autowired
    public HomeController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");

        List<Produto> produtos = repository.findAll();
        if(produtos != null) modelAndView.addObject("produtos", produtos);

        return modelAndView;
    }

}

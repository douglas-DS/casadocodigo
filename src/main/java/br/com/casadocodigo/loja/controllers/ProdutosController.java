package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("produtos")
public class ProdutosController {

    private ProdutoRepository repository;

    private FileSaver fileSaver;

    @Autowired
    public ProdutosController(ProdutoRepository repository, FileSaver fileSaver) {
        this.repository = repository;
        this.fileSaver = fileSaver;
    }

    @RequestMapping("form")
    public ModelAndView form(Produto produto) {
        ModelAndView modelAndView = new ModelAndView("produtos/form");

        modelAndView.addObject(produto);
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("produtos/lista");

        List<Produto> produtos = repository.findAll();
        if(produtos != null) modelAndView.addObject("lista", produtos);

        return modelAndView;
    }

    @PostMapping
    public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) return form(produto);

        String path = fileSaver.write("arquivos-sumario", sumario);

        System.out.println(sumario.getOriginalFilename());

        produto.setSumarioPath(path);
        repository.save(produto);

        attributes.addFlashAttribute("sucesso", "Produto Salvo!");

        return new ModelAndView("redirect:/produtos");
    }

    @RequestMapping("detalhe/{id}")
    public ModelAndView detalhe(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
        Produto produto = repository.findProdutoById(id);
        modelAndView.addObject("produto", produto);

        return modelAndView;
    }

   @RequestMapping("sumario/{id}")
   public ModelAndView sumario(@PathVariable Integer id) {
       ModelAndView modelAndView = new ModelAndView("sumario/sumario");

       Produto produto = repository.findProdutoById(id);
       String sumario = produto.getSumarioPath();

       modelAndView.addObject("sumarioPath", sumario);

       return modelAndView;
   }

}

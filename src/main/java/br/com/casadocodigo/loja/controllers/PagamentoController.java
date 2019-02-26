package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.Callable;

@Controller
@RequestMapping("pagamento")
public class PagamentoController {

    private CarrinhoCompras carrinho;

    private RestTemplate restTemplate;

    @Autowired
    public PagamentoController(CarrinhoCompras carrinho, RestTemplate restTemplate) {
        this.carrinho = carrinho;
        this.restTemplate = restTemplate;
    }

    @PostMapping("finalizar")
    public Callable<ModelAndView> finalizar(RedirectAttributes model) {
        return() -> {
            String uri = "http://book-payment.herokuapp.com/payment";

            try {
                String response = restTemplate.postForObject(uri, new DadosPagamentos(carrinho.getTotal()), String.class);
                model.addFlashAttribute("sucesso", response);
                carrinho.limparCarrinho();
                return new ModelAndView("redirect:/produtos");

            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                model.addFlashAttribute("falha", "Valor maior que o permitido");
                return new ModelAndView("redirect:/produtos");
            }
        };
    }

}

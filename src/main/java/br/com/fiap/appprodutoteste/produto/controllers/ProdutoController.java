package br.com.fiap.appprodutoteste.produto.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.appprodutoteste.produto.dto.ProdutoDTO;
import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.repositories.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/produtos")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("produtos/index");
		List<Produto> produtoDaRepository = produtoRepository.findAll();
		modelView.addObject("listarProdutos", produtoDaRepository);

		return modelView;
	}

	@GetMapping("/produtos/criar")
	public ModelAndView criar(ProdutoDTO produtoDTO) {
		ModelAndView modelAndView = new ModelAndView("produtos/criar");
		return modelAndView;
	}

	@PostMapping("/produtos")
	public ModelAndView salvar(@Valid ProdutoDTO produtoDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("Temos erros");
			return new ModelAndView("produtos/criar");
		}
		Produto produtoEntity = modelMapper.map(produtoDTO, Produto.class);
		produtoRepository.save(produtoEntity);
		return new ModelAndView("redirect:/produtos");
	}

	@GetMapping("produtos/{id}")
	public ModelAndView mostrar(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isPresent()) {
			Produto produtoGet = produto.get();
			ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
			modelAndView.addObject("produto", produtoGet);
			return modelAndView;
		}

		return new ModelAndView("redirect:/produtos");
	}

}

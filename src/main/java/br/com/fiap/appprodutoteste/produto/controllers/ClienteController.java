package br.com.fiap.appprodutoteste.produto.controllers;

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

import br.com.fiap.appprodutoteste.produto.dto.ClienteDTO;
import br.com.fiap.appprodutoteste.produto.model.Cliente;
import br.com.fiap.appprodutoteste.produto.model.Produto;
import br.com.fiap.appprodutoteste.produto.repositories.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/clientes")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("clientes/index");
		List<Cliente> clientes = clienteRepository.findAll();
		model.addObject("clientes", clientes);
		return model;
	}

	@GetMapping("/clientes/criar")
	public ModelAndView criar(ClienteDTO clienteDTO) {
		ModelAndView modelAndView = new ModelAndView("clientes/criar");
		return modelAndView;
	}

	@PostMapping("/clientes")
	public ModelAndView salvar(@Valid ClienteDTO clienteDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("clientes/criar");
		}

		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		clienteRepository.save(cliente);
		return new ModelAndView("redirect:/clientes");
	}

	@GetMapping("clients/{id}")
	public ModelAndView mostrar(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			Cliente clienteGet = cliente.get();
			ModelAndView modelAndView = new ModelAndView("clientes/detalhe");
			modelAndView.addObject("cliente", clienteGet);
			return modelAndView;
		}

		return new ModelAndView("redirect:/clientes");
	}
}

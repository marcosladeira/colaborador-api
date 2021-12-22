package com.marcosladeira.colaborador.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.marcosladeira.colaborador.model.Colaborador;
import com.marcosladeira.colaborador.repository.ColaboradorRepository;



@Controller
public class ColaboradorController {
	
	@Autowired
	private ColaboradorRepository colaboradorRepo;

    public ColaboradorController(ColaboradorRepository colaboradorRepo) {
		this.colaboradorRepo = colaboradorRepo;
	}
	
	@GetMapping("/crud/colaboradores")
	public String colaboradores(Model model) {
		model.addAttribute("listaColaboradores", colaboradorRepo.findAll());
		return "crud/colaboradores/index";
	}
	
	@GetMapping("/crud/colaboradores/novo")
	public String novoColaborador(@ModelAttribute("colaborador") Colaborador colaborador) {
		return "crud/colaboradores/form";
	}

	@PostMapping("/crud/colaboradores/salvar")
	public String salvarColaborador(@Valid @ModelAttribute("colaborador")Colaborador colaborador, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "crud/colaboradores/form";
		}
		colaboradorRepo.save(colaborador);
		return "redirect:/crud/colaboradores";
	}
	
	@GetMapping("/crud/colaboradores/{id}")
	public String alterarColaborador(@PathVariable("id") long id, Model model) {
		Optional<Colaborador> colaboradorOpt = colaboradorRepo.findById(id);
		if (!colaboradorOpt.isPresent()) {
			throw new IllegalArgumentException("Colaborador inválido.");
		}
		
		model.addAttribute("colaborador", colaboradorOpt.get());
		
		return "crud/colaboradores/form";
	}
	
	@GetMapping("/crud/colaboradores/excluir/{id}")
	public String excluirColaborador(@PathVariable("id") long id) {
		Optional<Colaborador> colaboradorOpt = colaboradorRepo.findById(id);
		if (colaboradorOpt.isEmpty()) {
			throw new IllegalArgumentException("Colaborador inválido.");
		}
		colaboradorRepo.delete(colaboradorOpt.get());
		return "redirect:/crud/colaboradores";
	}
}
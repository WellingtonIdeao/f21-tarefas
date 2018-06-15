package br.com.wellington.tarefas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.wellington.tarefas.model.Tarefa;
import br.com.wellington.tarefas.model.TarefaDAO;

@Controller
public class TarefasController {
	private TarefaDAO dao;
	
	@Autowired
	public TarefasController(TarefaDAO dao) {
		this.dao = dao;
	}
	@RequestMapping("/adicionaTarefa")
	public String adicionar(@Valid Tarefa tarefa, BindingResult result){
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		dao.adicionar(tarefa);
		return "tarefa/adicionada";
		
	}
	
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
		
	}
	@RequestMapping("listaTarefas")
	public String listar(Model model) {
		model.addAttribute("tarefas", dao.getLista());
		return"tarefa/lista";
		
	}
	
	@ResponseBody
	@RequestMapping("removeTarefa")
	public void remover(Long id) {
		dao.remove(id);
	}
	@RequestMapping("mostraTarefa")
	public String  mostra(Long id, Model model) {
		model.addAttribute("tarefa",dao.buscarPorId(id));
		return "tarefa/mostra";
		
	}
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.alterar(tarefa);
		return "redirect:listaTarefas";
		
	}
	@RequestMapping("finalizaTarefa")
	public String finalizar(Long id, Model model) {
		dao.finaliza(id);
		model.addAttribute("tarefa",dao.buscarPorId(id));
		return "tarefa/finalizada";
	}
}

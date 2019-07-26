package com.eventoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	EventoRepository eventoRepository;
	@Autowired
	ConvidadoRepository convidadoRepository;

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public ModelAndView adiconarEvento(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("evento/formEvento");
        return mv;
    }
    
    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public ModelAndView adicionarEvento(@Validated Evento evento, BindingResult result, RedirectAttributes atributte){
    	ModelAndView mv = new ModelAndView();
    	if (result.hasErrors()) {
			atributte.addFlashAttribute("mensagem","verifique os campos");
			mv.setViewName("redirect:/cadastrarEvento");
	    	return mv;
		}
    	eventoRepository.save(evento);
    	mv.setViewName("redirect:/listarEventos");
        return mv;
    }
    
    @RequestMapping(value = "/listarEventos")
    public ModelAndView listar() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("evento/listaEventos");
    	Iterable<Evento> eventos = eventoRepository.findAll(); 
    	mv.addObject("eventos", eventos);
    	return mv;
    }
    
    @RequestMapping(value = "/evento/detalhes/{id}")
    public ModelAndView detalhesEvento(@PathVariable("id") long id) {
    	ModelAndView mv = new ModelAndView();
    	Evento evento = eventoRepository.findById(id);
    	mv.setViewName("evento/detalhesEvento");
    	mv.addObject("evento", evento);
    	Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
    	mv.addObject("convidados", convidados);
    	return mv;
    }
    @RequestMapping(value = "/evento/deletar/{id}")
    public ModelAndView deletarEvento(@PathVariable("id") long id) {
    	ModelAndView mv = new ModelAndView();
    	eventoRepository.deleteById(id);
    	mv.setViewName("redirect:/listarEventos");
    	return mv;
    }
    @RequestMapping(value = "/convidado/adicionar/{id}", method = RequestMethod.GET)
    public ModelAndView adicionarConvidado(@PathVariable("id") long id) {
    	ModelAndView mv = new ModelAndView();
    	Evento evento = eventoRepository.findById(id);
    	mv.setViewName("evento/AdicionarConvidado");
    	mv.addObject("evento", evento);
    	return mv;
    }
    @RequestMapping(value = "/convidado/adicionar/{id}", method = RequestMethod.POST)
    public ModelAndView adicionarConvidado(@PathVariable("id") long id,@Validated Convidado convidado, BindingResult result, RedirectAttributes atributte) {
    	ModelAndView mv = new ModelAndView();
    	if (result.hasErrors()) {
			atributte.addFlashAttribute("mensagem","verifique os campos");
			mv.setViewName("redirect:/convidado/adicionar/{id}");
	    	return mv;
		}
    	Evento evento = eventoRepository.findById(id);
    	convidado.setEvento(evento);
    	convidadoRepository.save(convidado);
    	atributte.addFlashAttribute("mensagem","Convidado adicionado com sucesso");
    	mv.setViewName("redirect:/evento/detalhes/{id}");
    	return mv;
    }
    
    @RequestMapping(value = "/convidado/deletar/{id}")
    public String deletarConvidado(@PathVariable("id") long id) {
    	Convidado convidado = convidadoRepository.getOne(id);
    	convidadoRepository.deleteById(id);
    	String codigo = ""+convidado.getEvento().getId();
    	return "redirect:/evento/detalhes/" + codigo;
    }
    
}

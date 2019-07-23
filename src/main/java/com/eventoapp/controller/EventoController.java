package com.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	EventoRepository eventoRepository;

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public ModelAndView form(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("evento/formEvento");
        return mv;
    }
    
    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public ModelAndView form(Evento evento){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("redirect:/listarEventos");
    	eventoRepository.save(evento);
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
    	Evento Evento = eventoRepository.findById(id);
    	mv.setViewName("evento/detalhesEvento");
    	mv.addObject("evento", Evento);
    	return mv;
    }
    
}

package com.eventoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventoapp.models.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	Evento findById(long id);
}

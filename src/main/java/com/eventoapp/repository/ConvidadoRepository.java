package com.eventoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventoapp.models.Convidado;



@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Long>{

}

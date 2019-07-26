package com.eventoapp.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "evento")
public class Evento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "evento_id")
	private long id;
	@NotEmpty
	private String nome;
	@NotEmpty
    private String local;
	@NotEmpty
    private String data;
	@NotEmpty
    private String horario;
    @OneToMany(mappedBy = "evento", fetch = FetchType.EAGER)
    private List<Convidado> convidado;
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}

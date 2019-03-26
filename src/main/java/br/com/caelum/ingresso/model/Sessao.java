package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity // Anotação de Persistência da JPA (Hibernate)
public class Sessao {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Filme filme;
	@ManyToOne
	private Sala sala;
	private LocalTime horario;

	// Design Pattern JavaBean 
	//(Método Sem argumentos, Getters e Setters para atributos privados)

	// Anotação para deixar o construtor obsoleto (informar ao programador para não utilizar o método)
	/**
	 * @deprecated Só existe devido o Hibernate
	 */
	public Sessao() {

	}

	public Sessao(Filme filme, Sala sala, LocalTime horario) {
		super();
		this.filme = filme;
		this.sala = sala;
		this.horario = horario;
	}

	public LocalTime getHorarioTermino() {
		return this.horario.plusMinutes(filme.getDuracao().toMinutes());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

}

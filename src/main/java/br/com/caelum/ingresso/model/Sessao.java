package br.com.caelum.ingresso.model;

import java.time.LocalTime;

public class Sessao {
		
	private Long id;
	private Filme filme;
	private Sala sala;
	private LocalTime horario;	
	
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

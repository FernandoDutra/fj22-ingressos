package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private BigDecimal preco = BigDecimal.ZERO;

	@OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
	private Set<Ingresso> ingressos = new HashSet<>();
	
	// Design Pattern JavaBean
	// (Método Sem argumentos, Getters e Setters para atributos privados)

	// Anotação para deixar o construtor obsoleto (informar ao programador para não
	// utilizar o método)
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
		this.preco = sala.getPreco().add(filme.getPreco());
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

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Map<String, List<Lugar>> getMapaDeLugares() {
		return sala.getMapaDeLugares();
	}

	public Set<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(Set<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
	public boolean isDisponivel(Lugar lugarSelecionado) {
		return ingressos.stream().map(Ingresso::getLugar).noneMatch(lugar -> lugar.equals(lugarSelecionado));
	}

}

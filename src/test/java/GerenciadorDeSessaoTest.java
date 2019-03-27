import static org.junit.Assert.assertArrayEquals;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

public class GerenciadorDeSessaoTest {

	private Filme rogueOne;
	private Sala sala3D;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;

	@Before
	public void preparaSessoes() {
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		this.sala3D = new Sala("Sala 3D");

		this.sessaoDasDez = new Sessao(rogueOne, sala3D, LocalTime.parse("10:00:00"));
		this.sessaoDasTreze = new Sessao(rogueOne, sala3D, LocalTime.parse("13:00:00"));
		this.sessaoDasDezoito = new Sessao(rogueOne, sala3D, LocalTime.parse("18:00:00"));
	}

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciadorDeSessao.cabe(sessaoDasDez));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDEntroDoHorarioDeUmaSessaoJaExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(rogueOne, sala3D, sessaoDasDez.getHorario().minusHours(1));
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciadorDeSessao.cabe(sessao));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {
		List<Sessao> sessoesDaSala = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(rogueOne, sala3D, sessaoDasDez.getHorario().plusHours(1));
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoesDaSala);
		Assert.assertFalse(gerenciadorDeSessao.cabe(sessao));
	}

	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		Assert.assertTrue(gerenciadorDeSessao.cabe(sessaoDasTreze));
	}

}

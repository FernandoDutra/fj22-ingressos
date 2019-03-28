import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.descontos.DescontoParaEstudantes;
import br.com.caelum.ingresso.descontos.SemDesconto;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {

	private Sala sala;
	private Filme filme;
	private Sessao sessao;
	private Ingresso ingresso;
	private Lugar lugar;

	@Before
	public void preparaTest() {
		sala = new Sala("Eldorado - IMAX", new BigDecimal("20.50"));
		filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		sessao = new Sessao(filme, sala, LocalTime.parse("10:00:00"));
		lugar = new Lugar("A", 1);
	}

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);

		BigDecimal precoEsperado = new BigDecimal("32.50");

		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesdeBancos() {
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);

		BigDecimal precoEsperado = new BigDecimal("22.750");

		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontode50porcentoParaIngressosDeEstudante() {
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);

		BigDecimal precoEsperado = new BigDecimal("16.25");

		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

}

package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.descontos.Desconto;

public class Ingresso {

	private Sessao sessao;
	private BigDecimal preco = BigDecimal.ZERO;

	/**
	 * @deprecated hibernate only
	 */
	public Ingresso() {

	}

	public Ingresso(Sessao sessao, Desconto tipoDesconto) {
		this.setSessao(sessao);
		this.setPreco(tipoDesconto.aplicarDescontosSobre(sessao.getPreco()));
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}

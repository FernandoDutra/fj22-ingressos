package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.descontos.Desconto;
import br.com.caelum.ingresso.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.descontos.DescontoParaEstudantes;
import br.com.caelum.ingresso.descontos.SemDesconto;

public enum TipoDeIngresso {
	
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoParaEstudantes()),
	BANCO(new DescontoParaBancos());
	
	private final Desconto desconto;
	
	TipoDeIngresso(Desconto desconto){
		this.desconto = desconto;
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor) {
		return desconto.aplicarDescontosSobre(valor);
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
	}

}

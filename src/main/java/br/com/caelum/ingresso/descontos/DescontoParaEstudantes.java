package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class DescontoParaEstudantes implements Desconto {

	@Override
	public BigDecimal aplicarDescontosSobre(BigDecimal precoOriginal) {
		// TODO Auto-generated method stub
		return precoOriginal.divide(new BigDecimal("2.0"));
	}

}

package br.com.caelum.ingresso.descontos;
import java.math.BigDecimal;

import br.com.caelum.ingresso.descontos.Desconto;

public class SemDesconto implements Desconto {

	@Override
	public BigDecimal aplicarDescontosSobre(BigDecimal precoOriginal) {
		// TODO Auto-generated method stub
		return precoOriginal;
	}

}

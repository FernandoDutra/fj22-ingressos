package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {

	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SalaDao salaDao;
	@Autowired
	private SessaoDao sessaoDao;

	@GetMapping("admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm sessaoForm) {
		sessaoForm.setSalaId(salaId);

		ModelAndView mav = new ModelAndView("sessao/sessao");

		List<Filme> filmes = filmeDao.findAll();
		Sala sala = salaDao.findOne(salaId);

		mav.addObject("sala", sala);
		mav.addObject("filmes", filmes);
		mav.addObject("form", sessaoForm);

		return mav;
	}

	@PostMapping("admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm sessaoForm, BindingResult result) {
		if (result.hasErrors()) {
			return form(sessaoForm.getSalaId(), sessaoForm);
		}

		Sessao sessao = sessaoForm.toSessao(salaDao, filmeDao);

		List<Sessao> sessaoDaSala = sessaoDao.findAllBySala(sessao.getSala());

		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessaoDaSala);

		if (gerenciadorDeSessao.cabe(sessao)) {
			sessaoDao.save(sessao);
			return new ModelAndView("redirect:/admin/sala/" + sessao.getSala().getId() + "/sessoes");
		}

		return form(sessaoForm.getSalaId(), sessaoForm);
	}

}

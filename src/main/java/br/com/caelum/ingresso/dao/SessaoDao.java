package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

// Ele serve para indicar ao Spring que ele instanciará o Objeto Dao
@Repository
public class SessaoDao {

	@PersistenceContext
	EntityManager manager;

	public void save(Sessao sessao) {
		manager.persist(sessao);
	}

	public Sessao findOne(Long id) {
		return manager.find(Sessao.class, id);
	}

	public List<Sessao> findAllBySala(Sala sala) {
		return manager.createQuery("select s from Sessao s where s.sala = :sala", Sessao.class)
				.setParameter("sala", sala).getResultList();
	}

	public List<Sessao> buscaSessoesDoFilme(Filme filme) {
		return manager.createQuery("select s from Sessao s where s.filme = :filme", Sessao.class)
				.setParameter("filme", filme).getResultList();
	}

}

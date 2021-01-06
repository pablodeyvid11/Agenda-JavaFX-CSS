package entities.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entities.Contato;

public class ContatoDAOHibernate {
	private EntityManager em;

	public ContatoDAOHibernate() {
		this.em = Persistence.createEntityManagerFactory("maindb").createEntityManager();
	}

	public Boolean update(Contato c) {
		Contato banco = findById(c.getId());
		banco.setNome(c.getNome());
		banco.setEmail(c.getEmail());
		banco.setGrupo(c.getGrupo());
		banco.setNumero(c.getNumero());
		banco.setOperadora(c.getOperadora());
		try {
			em.getTransaction().begin();
			em.merge(banco);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Contato> getAll() {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Contato> cq = cb.createQuery(Contato.class);
		Root<Contato> root = cq.from(Contato.class);
		CriteriaQuery<Contato> all = cq.select(root);

		TypedQuery<Contato> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	public Contato findById(Integer id) {
		try {
			return em.getReference(Contato.class, id);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Contato deleteById(Integer id) {
		Contato c = findById(id);
		try {
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return c;
	}

	public Boolean addContato(Contato contato) {
		try {
			em.getTransaction().begin();
			em.persist(contato);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

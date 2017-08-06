package org.wildflyjpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.wildflyjpa.model.Mytable;

/**
 * DAO for Mytable
 */
@Stateless
public class MytableDao {
	@PersistenceContext(unitName = "wildflyjpa-persistence-unit")
	private EntityManager em;

	public void create(Mytable entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Mytable entity = em.find(Mytable.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Mytable findById(int id) {
		return em.find(Mytable.class, id);
	}

	public Mytable update(Mytable entity) {
		return em.merge(entity);
	}

	public List<Mytable> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Mytable> findAllQuery = em
				.createQuery("SELECT DISTINCT m FROM Mytable m ORDER BY m.id",
						Mytable.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}

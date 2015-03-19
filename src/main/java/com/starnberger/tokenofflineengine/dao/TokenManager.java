/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.starnberger.tokenofflineengine.model.Token;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenManager {
	private static final TokenManager _INSTANCE = new TokenManager();

	/**
	 * @return
	 */
	public static final TokenManager getInstance() {
		return _INSTANCE;
	}

	/**
	 * Private default constructor
	 */
	private TokenManager() {
	}

	/**
	 * Mark the token config upgrade flag as completed.
	 * 
	 * @param token
	 */
	public void markTokenConfigUpgradeDone(Token token) {
		token.setNeedsConfigUpdate(false);
		merge(token);
	}

	/**
	 * @param token
	 * @return
	 */
	public Token merge(Token token) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		token = em.merge(token);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return token;
	}

	/**
	 * @param id
	 * @return
	 */
	public Token findById(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		Token token = em.find(Token.class, id);
		em.close();
		return token;
	}

	/**
	 * @param id
	 * @return
	 */
	public Token findByRemoteId(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<Token> query = em.createNamedQuery("Token.findMyWebKey", Token.class);
		query.setParameter("webKey", id);
		List<Token> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	/**
	 * @return
	 */
	public Token findByMac(String mac) {
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<Token> query = em.createNamedQuery("Token.findByMac", Token.class);
		query.setParameter("mac", mac);
		Token token = null;
		List<Token> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			token = resultList.get(0);
		}
		em.close();
		return token;
	}

}

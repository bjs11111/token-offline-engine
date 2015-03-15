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

	private final EntityManager em;

	/**
	 * Private default constructor
	 */
	private TokenManager() {
		this.em = EMF.get().createEntityManager();
	}

	/**
	 * @return
	 */
	public Token findByMac(String mac) {
		TypedQuery<Token> query = em.createNamedQuery("Token.findByMac", Token.class);
		query.setParameter("mac", mac);
		List<Token> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0)
			return resultList.get(0);
		return null;
	}

}

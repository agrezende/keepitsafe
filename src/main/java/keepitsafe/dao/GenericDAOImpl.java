/*
 * Copyright 2012 Felipe C. do R. P.
 *
 * This file is part of Keep It Safe.
 * 
 * Keep It Safe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Keep It Safe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Keep It Safe.  If not, see <http://www.gnu.org/licenses/>.
 */

package keepitsafe.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import keepitsafe.model.ModelObject;

public abstract class GenericDAOImpl<OBJ extends ModelObject> extends
		HibernateDaoSupport implements GenericDAO<OBJ> {

	private Class<OBJ> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.persistentClass = (Class<OBJ>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// Classe que será persistida.

	public void save(OBJ object) {
		getHibernateTemplate().save(object);
	}

	public void update(OBJ object) {
		getHibernateTemplate().update(object);
	}

	public void delete(OBJ object) {
		getHibernateTemplate().delete(object);
	}

	@SuppressWarnings("unchecked")
	public OBJ find(long id) {
		return (OBJ) getHibernateTemplate().get(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<OBJ> findAll() {
		return getHibernateTemplate().loadAll(persistentClass);
	}

}

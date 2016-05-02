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

package info.fcrp.keepitsafe.dao;

import java.util.List;

import info.fcrp.keepitsafe.model.Keep;

public class KeepDAOImpl extends GenericDAOImpl<Keep>
		implements KeepDAO {

	public Keep findByName(String name) {
		@SuppressWarnings("unchecked")
		List<Keep> list = getHibernateTemplate().find(
				"select ps from PasswordStore ps where ps.name = ?", name);

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}

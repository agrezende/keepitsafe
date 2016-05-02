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

package info.fcrp.keepitsafe.bean;

import info.fcrp.keepitsafe.dao.KeepDAO;
import info.fcrp.keepitsafe.model.Keep;

import org.springframework.beans.factory.annotation.Autowired;

public class KeepBeanImpl implements KeepBean {

	@Autowired
	KeepDAO passwordStoreDAO;

	public Keep create(String name) {
		
		Keep ps = new Keep(name);
		passwordStoreDAO.save(ps);
		return ps;
	}

	public Keep get(String name) {
		Keep ps = passwordStoreDAO.findByName(name);
		return ps;
	}
}

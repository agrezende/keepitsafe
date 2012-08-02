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

package info.fcrp.keepitsafe.service;

import java.util.List;

import info.fcrp.keepitsafe.dao.KeepDAO;
import info.fcrp.keepitsafe.dao.SecretDAO;
import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.Secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
@RequestMapping(value = "/keep")
public class KeepService {
	@Autowired
	private KeepDAO keepDAO;

	@Autowired
	private SecretDAO secretDAO;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Keep findById(@PathVariable long id) {
		Keep keep = keepDAO.find(id);
		return keep;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Keep> findAll() {
		List<Keep> keeps = keepDAO.findAll();
		return keeps;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Keep create(@RequestBody Keep keep) {
		for (Secret sc : keep.getSecrets()) {
			sc.setKeep(keep);
		}
		keepDAO.save(keep);
		return keep;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Keep create(@PathVariable long id, @RequestBody Keep keep) {
	    Keep curKeep = keepDAO.find(id);
	    if(curKeep != null) {
	        curKeep.setName(keep.getName());
	        curKeep.setDescription(keep.getDescription());
	    }
		keepDAO.save(curKeep);
		return curKeep;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		Keep keep = keepDAO.find(id);
		keepDAO.delete(keep);
	}
	
}

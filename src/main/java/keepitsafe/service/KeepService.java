/*
 * Copyright 2016
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

package keepitsafe.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import keepitsafe.dao.KeepDAO;
import keepitsafe.dao.SecretDAO;
import keepitsafe.model.Keep;
import keepitsafe.model.Secret;

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
		Keep keep = keepDAO.findOne(id);
		return keep;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Keep> findAll() {
		List<Keep> keeps = new ArrayList<>();
		keepDAO.findAll().forEach(keeps::add);
		return keeps;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Keep create(@RequestBody Keep keep, Principal principal) {
		for (Secret sc : keep.getSecrets()) {
			sc.setKeep(keep);
		}
		//keep.getRoleMap().setKing("user:" + principal.getName());
		
		keepDAO.save(keep);
		return keep;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Keep create(@PathVariable long id, @RequestBody Keep keep) {
	    Keep curKeep = keepDAO.findOne(id);
	    if(curKeep != null) {
	        curKeep.setName(keep.getName());
	        curKeep.setDescription(keep.getDescription());
	    }
		keepDAO.save(curKeep);
		return curKeep;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		Keep keep = keepDAO.findOne(id);
		keepDAO.delete(keep);
	}
	
}
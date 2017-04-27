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
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import keepitsafe.model.Keep;
import keepitsafe.model.KeepDAO;
import keepitsafe.service.mapper.KeepMapper;

@RestController
@Transactional
@RequestMapping(value = "/api/1/keep")
public class KeepService {

    private KeepDAO keepDAO;

    @Autowired
    public KeepService(KeepDAO keepDAO) {
        super();
        this.keepDAO = keepDAO;
    }

    /**
     * Return a keep by id
     * 
     * Return: FORBIDDEN when not found or don´t have permission
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ObjectNode> findById(@PathVariable long id) {
        Keep keep = keepDAO.findOne(id);

        if (keep == null) {
            return new ResponseEntity<ObjectNode>(HttpStatus.FORBIDDEN);
        }

        ObjectNode keepJ = KeepMapper.toJsonWithSecrets(keep);
        return new ResponseEntity<ObjectNode>(keepJ, HttpStatus.OK);
    }

    /**
     * Return the keeps the user is allowed to access
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ArrayNode> findAll() {
        List<Keep> keeps = new ArrayList<Keep>();
        Iterator<Keep> keepsIt = keepDAO.findAll().iterator();
        keepsIt.forEachRemaining(keeps::add);
        ArrayNode keepsJ = KeepMapper.toJson(keeps);
        return new ResponseEntity<ArrayNode>(keepsJ, HttpStatus.OK);
    }

    /**
     * Create an empty keep
     * 
     * The creator becomes the keep owner
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody ObjectNode keepJ, Principal principal) {
        if (keepJ == null || !keepJ.has("name") || !keepJ.has("description")) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        
        String name = keepJ.get("name").textValue();
        String description = keepJ.get("description").textValue();

        Keep keep = new Keep(name, description);
        keepDAO.save(keep);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}

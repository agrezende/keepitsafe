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

package keepitsafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import keepitsafe.model.Keep;
import keepitsafe.model.KeepDAO;
import keepitsafe.model.Secret;
import keepitsafe.model.SecretDAO;

@Controller
@Transactional
public class SecretService {
    @Autowired
    private SecretDAO secretDAO;

    @Autowired
    private KeepDAO keepDAO;

    @RequestMapping(value = "/keep/{keepId}/secret", method = RequestMethod.GET)
    public @ResponseBody
    List<Secret> findByKeepId(@PathVariable long keepId) {
        List<Secret> secrets = secretDAO.findByKeepId(keepId);
        return secrets;
    }

    @RequestMapping(value = "/secret/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasPermission(#id,'info.fcrp.keepitsafe.model.Secret','king')")
    public @ResponseBody
    Secret findById(@PathVariable long id) {
        Secret secret = secretDAO.findOne(id);
        return secret;
    }

    @RequestMapping(value = "/keep/{keepId}/secret", method = RequestMethod.POST)
    public @ResponseBody
    Secret create(@PathVariable long keepId, @RequestBody Secret secret) {
        Keep keep = keepDAO.findOne(keepId);
//        secret.setKeep(keep);
        secretDAO.save(secret);
        return secret;
    }

    @RequestMapping(value = "/secret/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Secret update(@PathVariable long id, @RequestBody Secret secret) {
        Secret curSecret = secretDAO.findOne(id);
        if (curSecret != null) {
//            curSecret.setName(secret.getName());
//            curSecret.setLogin(secret.getLogin());
//            curSecret.setDescription(secret.getDescription());
//            curSecret.setPassword(secret.getPassword());
            secretDAO.save(curSecret);
        }
        return curSecret;
    }

    @RequestMapping(value = "/secret/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        Secret secret = secretDAO.findOne(id);
        secretDAO.delete(secret);
    }

}

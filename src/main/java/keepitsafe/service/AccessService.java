/*
 * Copyright (c) 2016 Felipe do Rego Pinto
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

import java.util.Base64;
import java.util.Base64.Decoder;

import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import keepitsafe.model.User;
import keepitsafe.model.UserDAO;

@RestController
@Transactional
@RequestMapping(value = "/api/1/access")
public class AccessService {

    private UserDAO userDAO;
    
    
    public AccessService(UserDAO userDAO) {
        super();
        this.userDAO = userDAO;
    }

    /**
     * Register a new user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody ObjectNode userJ) {
        if (userJ == null ||
                !userJ.has("email") || !userJ.has("publickey")) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        
        String email = userJ.get("email").textValue();
        String publickeyB64 = userJ.get("publickey").textValue();
 
        if (userDAO.findByEmail(email) != null) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        
        
        //TODO check public key
        byte[] publicKeyB = Base64.getDecoder().decode(publickeyB64.getBytes());
//        PublicKeyPacket publicKeyP = new P
//        PGPPublicKey publicKey = new PGPPublicKey(publicKeyB, new JcaKeyFingerprintCalculator())
        
        
        User user = new User(email, publickeyB64);
        userDAO.save(user);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
}

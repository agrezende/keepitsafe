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

package info.fcrp.keepitsafe.security;

import info.fcrp.keepitsafe.dao.KeepDAO;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class KeepRoleEvaluator implements PermissionEvaluator {
    @Autowired
    private KeepDAO keepDAO;

    public boolean hasPermission(Authentication authentication,
            Object targetDomainObject, Object permission) {

        if (targetDomainObject == null) {
            return false;
        } 
        
      
        return true;
    }

    public boolean hasPermission(Authentication authentication,
            Serializable targetId, String targetType, Object permission) {
        // TODO Auto-generated method stub
        return false;
    }

}

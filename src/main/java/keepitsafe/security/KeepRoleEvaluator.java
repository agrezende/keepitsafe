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

package keepitsafe.security;

import keepitsafe.dao.KeepDAO;
import keepitsafe.dao.RoleMapDAO;
import keepitsafe.model.Keep;
import keepitsafe.model.RoleMap;
import keepitsafe.model.Secret;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class KeepRoleEvaluator implements PermissionEvaluator {
    @Autowired
    private RoleMapDAO roleMapDAO;

    public boolean hasPermission(Authentication authentication,
            Object targetDomainObject, Object permission) {

        if (targetDomainObject == null) {
            return false;
        }

        return true;
    }

    public boolean hasPermission(Authentication authentication,
            Serializable targetId, String targetType, Object permission) {

        RoleMap roleMap = null;
        if (targetId != null) {
            if ("info.fcrp.keepitsafe.model.Secret".equals(targetType)) {
                roleMap = roleMapDAO.findBySecretId((Long) targetId);
            } else if (Keep.class.getName().equals(targetType)) {
                roleMap = roleMapDAO.findByKeepId((Long) targetId);
            }
        }

        if (roleMap != null) {
            return checkRole(roleMap, authentication, permission);
        }

        return false;

    }

    private boolean checkRole(RoleMap roleMap, Authentication authentication,
            Object permission) {

        String[] roles = null;
        if ("king".equals(permission)) {
            if (roleMap.getKing() != null) {
                roles = roleMap.getKing().split(";");
            }
        } else if ("commoner".equals(permission)) {
            if (roleMap.getCommoner() != null) {
                roles = roleMap.getCommoner().split(";");
            }
        }

        for (String role : roles) {
            if (role.startsWith("user:")) {
                User user = (User) authentication.getPrincipal();
                if(role.replaceFirst("user:", "").equals(user.getUsername())) {
                    return true;
                }
            } else if (authentication.getAuthorities().contains(role)) {
                return true;
            }
        }

        return false;
    }

}

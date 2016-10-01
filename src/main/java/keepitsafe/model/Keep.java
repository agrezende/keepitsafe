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

package keepitsafe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * A set of passwords
 * 
 * @author felipecrp
 * 
 */
@Entity
public class Keep extends ModelObject {
    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "keep", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private List<Secret> secrets;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn
    private RoleMap roleMap;

    public Keep() {
        super();
        this.secrets = new ArrayList<Secret>();
        this.roleMap = new RoleMap();
        roleMap.setKing("user:rod;teller");
    }

    public Keep(String name) {
        this();
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the secrets
     */
    public List<Secret> getSecrets() {
        return secrets;
    }

    /**
     * @param secrets
     *            the secrets to set
     */
    public void setSecrets(List<Secret> secrets) {
        this.secrets = secrets;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the roleMap
     */
    public RoleMap getRoleMap() {
        return roleMap;
    }

    /**
     * @param roleMap
     *            the roleMap to set
     */
    public void setRoleMap(RoleMap roleMap) {
        this.roleMap = roleMap;
    }

}

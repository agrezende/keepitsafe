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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

/**
 * A password. Nothing else ;)
 * 
 * @author felipecrp
 * 
 */
@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@NamedQueries({ @NamedQuery(name = "secret.find.keepId", query = "select sc from Secret sc where sc.keep.id = :keepId") })
public class Secret extends ModelObject {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String login;

    @Column
    @Type(type = "info.fcrp.keepitsafe.model.PasswordType")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Keep keep;

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
     * @return the value
     */

    /**
     * @return the keep
     */
    public Keep getKeep() {
        return keep;
    }

    /**
     * @param keep
     *            the keep to set
     */
    public void setKeep(Keep keep) {
        this.keep = keep;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

}

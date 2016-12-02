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
package keepitsafe.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * An ordinary user with access to the system
 * 
 * The system access is controlled by pgp, so to login, user must be able to sign a server message
 */
@Entity
@Table(name = "USER")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String email;
    
    @Column
    private String publicKey;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_GRP", 
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<Group> groups;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<AccessLog> logs;

    
    protected User() {
        super();
    }
    
    public User(String email) {
        super();
        this.email = email;
    }

    
    public User(String email, String publicKey) {
        super();
        this.email = email;
        this.publicKey = publicKey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public List<Group> getGroups() {
        return groups;
    } 
}

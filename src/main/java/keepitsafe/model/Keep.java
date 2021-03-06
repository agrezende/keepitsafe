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

package keepitsafe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A keep is place where secrets can be shared, but only with users in the allowed groups
 */
@Entity
@Table(name = "KEEP")
public class Keep {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column(length=10000)
	private String encryptedKey;
	
	@OneToMany(mappedBy = "keep", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Secret> secrets;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "keep")
	private List<AccessRight> rights;
	
	protected Keep() {
	    super();
	}

	public Keep(String name) {
		super();
		this.name = name;
	}

	public Keep(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Keep other = (Keep) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public List<Secret> getSecrets() {
        return secrets;
    }

    public List<AccessRight> getRights() {
        return rights;
    }
}

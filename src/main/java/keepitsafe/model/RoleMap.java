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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ 
@NamedQuery(name = "roleMap.find.keepId", query = 
    "select rm from RoleMap rm where rm.keep.id = :keepId"),
@NamedQuery(name = "roleMap.find.secretId", query = 
    "select rm from RoleMap rm inner join rm.keep.secrets sc where sc.id = :secretId") })
public class RoleMap extends ModelObject {
    @Column
    private String king;

    @Column
    private String commoner;

    @OneToOne(mappedBy="roleMap", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Keep keep;

    /**
     * @return the king
     */
    public String getKing() {
        return king;
    }

    /**
     * @param king
     *            the king to set
     */
    public void setKing(String king) {
        this.king = king;
    }

    /**
     * @return the commoner
     */
    public String getCommoner() {
        return commoner;
    }

    /**
     * @param commoner
     *            the commoner to set
     */
    public void setCommoner(String commoner) {
        this.commoner = commoner;
    }

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

}

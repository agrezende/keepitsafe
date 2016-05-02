package info.fcrp.keepitsafe.model;

import info.fcrp.keepitsafe.util.Crypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.type.StringType;

public class PasswordType extends StringType {
    
    @Override
    public void set(PreparedStatement st, Object value, int index)
            throws SQLException {
        super.set(st, Crypt.crypt(value.toString()), index);
    }
    
    @Override
    public Object get(ResultSet rs, String name) throws SQLException {
        return Crypt.decrypt(super.get(rs, name).toString());
    }
}

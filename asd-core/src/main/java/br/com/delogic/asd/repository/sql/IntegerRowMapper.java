package br.com.delogic.asd.repository.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.delogic.jfunk.Has;

/**
 * Row mapper created to return Lists of Integer values.
 *
 * @author celio@delogic.com.br
 *
 * @param <E>
 */
public class IntegerRowMapper<E> implements RowMapper<E> {

    @SuppressWarnings("unchecked")
    public E mapRow(ResultSet rs, int rowNum) throws SQLException {
        String value = rs.getString(1);
        if (Has.content(value)) {
            return (E) Integer.valueOf(value);
        } else {
            return null;
        }
    }

}

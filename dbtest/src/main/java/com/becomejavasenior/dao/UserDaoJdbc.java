package com.becomejavasenior.dao;

import com.becomejavasenior.model.User;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.util.List;

public class UserDaoJdbc extends JdbcDaoSupport implements UserDao {
    public static final String SQL_INSERT = "INSERT INTO Users (Name, PersonID) VALUES (?,?)";
    public static final String SQL_DELETE = "DELETE FROM Users WHERE ID=?";
    public static final String SQL_UPDATE = "UPDATE Users SET Name=? WHERE ID=?";
    public static final String SQL_FIND_BY_ID = "SELECT * FROM Users WHERE ID=?";

    @Override
    public User insert(User user) {
        User insUser = new User();
        insUser.setName(user.getName());
        insUser.setPersonId(user.getPersonId());
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{FIELD_ID});
            ps.setString(1, insUser.getName());
            ps.setInt(2, insUser.getPersonId());
            return ps;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(preparedStatementCreator, keyHolder);
        insUser.setId(keyHolder.getKey().intValue());
        return insUser;
    }

    @Override
    public User insertBatch(List<User> users) {
        return null;
    }

    @Override
    public void update(User keyword) {
        this.getJdbcTemplate().update(SQL_UPDATE, keyword.getName(), keyword.getId());
    }

    @Override
    public void delete(User keyword) {
        this.getJdbcTemplate().update(SQL_DELETE, keyword.getId());
    }


    private List<User> select(String sql) {
        return getJdbcTemplate().query(sql, getRowMapper());
    }

    private RowMapper<User> getRowMapper() {
        return (rs, arg) -> {
            User keyword = new User();
            keyword.setId(rs.getInt(FIELD_ID));
            keyword.setName(rs.getString(FIELD_NAME));
            keyword.setPersonId(rs.getInt(FIELD_PERSON_ID));
            return keyword;
        };
    }
}
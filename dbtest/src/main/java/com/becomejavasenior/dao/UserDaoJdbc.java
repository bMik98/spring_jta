package com.becomejavasenior.dao;

import com.becomejavasenior.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc extends JdbcDaoSupport implements UserDao {
    public static final String ID_FIELD = "id";
    public static final String SQL_INSERT = "INSERT INTO user"
            + "(ftiny, fsmall, fbig, fdouble, fdate, fyear, fchar, fvchar, fdec) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE = "UPDATE user "
            + "SET ftiny=?, fsmall=?, fbig=?, fdouble=?, fdate=?, fyear=?, fchar=?, fvchar=?, fdec=? "
            + "WHERE ID=?";
    public static final String SQL_DELETE = "DELETE FROM user WHERE id=?";
    public static final String SQL_GET = "SELECT * FROM user WHERE id=?";
    public static final String SQL_SELECT_1000 = "SELECT * FROM user LIMIT 0, 1000";

    @Override
    public User insert(User user) {
        User insUser = new User();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{ID_FIELD});
            ps.setByte(1, user.getFtiny());
            ps.setShort(2, user.getFsmall());
            ps.setLong(3, user.getFbig());
            ps.setDouble(4, user.getFdouble());
            ps.setTimestamp(5, user.getFdate());
            ps.setInt(6, user.getFyear());
            ps.setString(7, user.getFchar());
            ps.setString(8, user.getFvchar());
            ps.setBigDecimal(9, user.getFdec());
            return ps;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(preparedStatementCreator, keyHolder);
        insUser.setId(keyHolder.getKey().intValue());
        return insUser;
    }

    @Override
    public void insertBatch(final List<User> users) {
        getJdbcTemplate().batchUpdate(SQL_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = users.get(i);
                ps.setByte(1, user.getFtiny());
                ps.setShort(2, user.getFsmall());
                ps.setLong(3, user.getFbig());
                ps.setDouble(4, user.getFdouble());
                ps.setTimestamp(5, user.getFdate());
                ps.setInt(6, user.getFyear());
                ps.setString(7, user.getFchar());
                ps.setString(8, user.getFvchar());
                ps.setBigDecimal(9, user.getFdec());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }

    @Override
    public void update(User user) {
        this.getJdbcTemplate().update(SQL_UPDATE,
                user.getFtiny(),
                user.getFsmall(),
                user.getFbig(),
                user.getFdouble(),
                user.getFdate(),
                user.getFyear(),
                user.getFchar(),
                user.getFvchar(),
                user.getFdec(),
                user.getId()
        );
    }

    @Override
    public void delete(User user) {
        this.getJdbcTemplate().update(SQL_DELETE, user.getId());
    }

    @Override
    public User getById(Integer id) {
        User foundUser;
        try {
            foundUser = getJdbcTemplate().queryForObject(SQL_GET, new Object[]{id}, getRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return foundUser;
    }

    @Override
    public List<User> select() {
        return query(SQL_SELECT_1000);
    }

    private List<User> query(String sql) {
        return getJdbcTemplate().query(sql, getRowMapper());
    }

    private RowMapper<User> getRowMapper() {
        return (rs, arg) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFtiny(rs.getByte("ftiny"));
            user.setFsmall(rs.getShort("fsmall"));
            user.setFbig(rs.getLong("fbig"));
            user.setFdouble(rs.getDouble("fdouble"));
            user.setFdate(rs.getTimestamp("fdate"));
            user.setFyear(rs.getInt("fyear"));
            user.setFchar(rs.getString("fchar"));
            user.setFvchar(rs.getString("fvchar"));
            user.setFdec(rs.getBigDecimal("fdec"));
            return user;
        };
    }
}
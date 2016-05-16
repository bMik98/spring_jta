package com.becomejavasenior.dao;

import com.becomejavasenior.model.Order;
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

public class OrderDaoJdbc extends JdbcDaoSupport implements OrderDao {
    public static final String ID_FIELD = "id";
    public static final String SQL_INSERT = "INSERT INTO `order`"
            + "(ftiny, fsmall, fbig, fdouble, fdate, fyear, fchar, fvchar, fdec) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE = "UPDATE `order` "
            + "SET ftiny=?, fsmall=?, fbig=?, fdouble=?, fdate=?, fyear=?, fchar=?, fvchar=?, fdec=? "
            + "WHERE ID=?";
    public static final String SQL_DELETE = "DELETE FROM `order` WHERE id=?";
    public static final String SQL_GET = "SELECT * FROM `order` WHERE id=?";
    public static final String SQL_SELECT_1000 = "SELECT * FROM `order` LIMIT 0, 1000";

    @Override
    public Order insert(Order order) {
        Order insOrder = new Order();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{ID_FIELD});
            ps.setByte(1, order.getFtiny());
            ps.setShort(2, order.getFsmall());
            ps.setLong(3, order.getFbig());
            ps.setDouble(4, order.getFdouble());
            ps.setTimestamp(5, order.getFdate());
            ps.setInt(6, order.getFyear());
            ps.setString(7, order.getFchar());
            ps.setString(8, order.getFvchar());
            ps.setBigDecimal(9, order.getFdec());
            return ps;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(preparedStatementCreator, keyHolder);
        insOrder.setId(keyHolder.getKey().intValue());
        return insOrder;
    }

    @Override
    public void insertBatch(final List<Order> orders) {
        getJdbcTemplate().batchUpdate(SQL_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Order order = orders.get(i);
                ps.setByte(1, order.getFtiny());
                ps.setShort(2, order.getFsmall());
                ps.setLong(3, order.getFbig());
                ps.setDouble(4, order.getFdouble());
                ps.setTimestamp(5, order.getFdate());
                ps.setInt(6, order.getFyear());
                ps.setString(7, order.getFchar());
                ps.setString(8, order.getFvchar());
                ps.setBigDecimal(9, order.getFdec());
            }

            @Override
            public int getBatchSize() {
                return orders.size();
            }
        });
    }

    @Override
    public void update(Order order) {
        this.getJdbcTemplate().update(SQL_UPDATE,
                order.getFtiny(),
                order.getFsmall(),
                order.getFbig(),
                order.getFdouble(),
                order.getFdate(),
                order.getFyear(),
                order.getFchar(),
                order.getFvchar(),
                order.getFdec(),
                order.getId()
        );
    }

    @Override
    public void delete(Order order) {
        this.getJdbcTemplate().update(SQL_DELETE, order.getId());
    }

    @Override
    public Order getById(Order order) {
        Order foundOrder;
        try {
            foundOrder = getJdbcTemplate().queryForObject(SQL_GET, new Object[]{order.getId()}, getRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return foundOrder;
    }

    @Override
    public List<Order> select() {
        return query(SQL_SELECT_1000);
    }

    private List<Order> query(String sql) {
        return getJdbcTemplate().query(sql, getRowMapper());
    }

    private RowMapper<Order> getRowMapper() {
        return (rs, arg) -> {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setFtiny(rs.getByte("ftiny"));
            order.setFsmall(rs.getShort("fsmall"));
            order.setFbig(rs.getLong("fbig"));
            order.setFdouble(rs.getDouble("fdouble"));
            order.setFdate(rs.getTimestamp("fdate"));
            order.setFyear(rs.getInt("fyear"));
            order.setFchar(rs.getString("fchar"));
            order.setFvchar(rs.getString("fvchar"));
            order.setFdec(rs.getBigDecimal("fdec"));
            return order;
        };
    }
}
package com.becomejavasenior.dao;

import com.becomejavasenior.model.Product;
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

public class ProductDaoJdbc extends JdbcDaoSupport implements ProductDao {
    public static final String ID_FIELD = "id";
    public static final String SQL_INSERT = "INSERT INTO product"
            + "(ftiny, fsmall, fbig, fdouble, fdate, fyear, fchar, fvchar, fdec) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE = "UPDATE product "
            + "SET ftiny=?, fsmall=?, fbig=?, fdouble=?, fdate=?, fyear=?, fchar=?, fvchar=?, fdec=? "
            + "WHERE ID=?";
    public static final String SQL_DELETE = "DELETE FROM product WHERE id=?";
    public static final String SQL_GET = "SELECT * FROM product WHERE id=?";
    public static final String SQL_SELECT_1000 = "SELECT * FROM product LIMIT 0, 1000";

    @Override
    public Product insert(Product product) {
        Product insProduct = new Product();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{ID_FIELD});
            ps.setByte(1, product.getFtiny());
            ps.setShort(2, product.getFsmall());
            ps.setLong(3, product.getFbig());
            ps.setDouble(4, product.getFdouble());
            ps.setTimestamp(5, product.getFdate());
            ps.setInt(6, product.getFyear());
            ps.setString(7, product.getFchar());
            ps.setString(8, product.getFvchar());
            ps.setBigDecimal(9, product.getFdec());
            return ps;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(preparedStatementCreator, keyHolder);
        insProduct.setId(keyHolder.getKey().intValue());
        return insProduct;
    }

    @Override
    public void insertBatch(final List<Product> products) {
        getJdbcTemplate().batchUpdate(SQL_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Product product = products.get(i);
                ps.setByte(1, product.getFtiny());
                ps.setShort(2, product.getFsmall());
                ps.setLong(3, product.getFbig());
                ps.setDouble(4, product.getFdouble());
                ps.setTimestamp(5, product.getFdate());
                ps.setInt(6, product.getFyear());
                ps.setString(7, product.getFchar());
                ps.setString(8, product.getFvchar());
                ps.setBigDecimal(9, product.getFdec());
            }

            @Override
            public int getBatchSize() {
                return products.size();
            }
        });
    }

    @Override
    public void update(Product product) {
        this.getJdbcTemplate().update(SQL_UPDATE,
                product.getFtiny(),
                product.getFsmall(),
                product.getFbig(),
                product.getFdouble(),
                product.getFdate(),
                product.getFyear(),
                product.getFchar(),
                product.getFvchar(),
                product.getFdec(),
                product.getId()
        );
    }

    @Override
    public void delete(Product product) {
        this.getJdbcTemplate().update(SQL_DELETE, product.getId());
    }

    @Override
    public Product getById(Integer id) {
        Product foundProduct;
        try {
            foundProduct = getJdbcTemplate().queryForObject(SQL_GET, new Object[]{id}, getRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return foundProduct;
    }

    @Override
    public List<Product> select() {
        return query(SQL_SELECT_1000);
    }

    private List<Product> query(String sql) {
        return getJdbcTemplate().query(sql, getRowMapper());
    }

    private RowMapper<Product> getRowMapper() {
        return (rs, arg) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setFtiny(rs.getByte("ftiny"));
            product.setFsmall(rs.getShort("fsmall"));
            product.setFbig(rs.getLong("fbig"));
            product.setFdouble(rs.getDouble("fdouble"));
            product.setFdate(rs.getTimestamp("fdate"));
            product.setFyear(rs.getInt("fyear"));
            product.setFchar(rs.getString("fchar"));
            product.setFvchar(rs.getString("fvchar"));
            product.setFdec(rs.getBigDecimal("fdec"));
            return product;
        };
    }
}
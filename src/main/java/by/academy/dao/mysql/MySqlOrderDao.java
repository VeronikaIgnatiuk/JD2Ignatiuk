package by.academy.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import by.academy.dao.AbstractJDBCDao;
import by.academy.dao.domain.Order;

public class MySqlOrderDao extends AbstractJDBCDao<Order, Integer> {

	  private class PersistOrder extends Order {
	        /*public void setId(int id) {
	            super.setId(id);
	        }*/
	    }
    @Override
    public String getSelectQuery() {
        return "SELECT id_user, id_car, id_bill FROM autoservice.order ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO autoservice.order (id_user, id_car, id_bill) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE autoservice.order \n" +
                "SET id_user = ?, id_car  = ? , id_bill  = ? \n" ;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM autoservice.order WHERE id_bill = ?;";
    }

    @Override
    public Order add() throws Exception {
        Order s = new Order();
        return add(s);
    }

    public MySqlOrderDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Order> result = new LinkedList<Order>();
        try {
            while (rs.next()) {
                PersistOrder order = new PersistOrder();
                order.setId_User(rs.getInt("id_user"));
                order.setId_Car(rs.getInt("id_car"));
                order.setId_Bill(rs.getInt("id_bill"));
                result.add(order);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws Exception {
        try {
            statement.setInt(1, object.getId_User());
            statement.setInt(2, object.getId_Car());
            statement.setInt(3, object.getId_Bill());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws Exception {
        try {
            statement.setInt(1, object.getId_User());
            statement.setInt(2, object.getId_Car());
            statement.setInt(3, object.getId_Bill());
           
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

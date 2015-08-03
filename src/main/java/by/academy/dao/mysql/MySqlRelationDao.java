package by.academy.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import by.academy.dao.AbstractJDBCDao;
import by.academy.dao.domain.Order_Service;

public class MySqlRelationDao extends AbstractJDBCDao<Order_Service, Integer> {

	   private class PersistRelation extends Order_Service {
	        /*public void setId_Car(int id) {
	            super.setId_Car(id);
	        }*/
	    }
    @Override
    public String getSelectQuery() {
        return "SELECT id_bill, id_service FROM autoservice.relation WHERE id_bill = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO autoservice.relation (id_bill , id_service) \n" +
                "VALUES (? , ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE autoservice.relation \n" +
                "SET  id_service  = ?  WHERE id_bill = ?\n" ;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM autoservice.relation WHERE id_bill = ?;";
    }

    @Override
    public Order_Service add() throws Exception {
    	Order_Service s = new Order_Service();
        return add(s);
    }

    public MySqlRelationDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Order_Service> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Order_Service> result = new LinkedList<Order_Service>();
        try {
            while (rs.next()) {
                PersistRelation order_Service = new PersistRelation();
                order_Service.setId_Bill(rs.getInt("id_bill"));
                order_Service.setId_Service(rs.getInt("id_service"));
                result.add(order_Service);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order_Service object) throws Exception {
        try {
            statement.setInt(1, object.getId_Bill());
            statement.setInt(2, object.getId_Service());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order_Service object) throws Exception {
        try {
        	statement.setInt(1, object.getId_Bill());
            statement.setInt(2, object.getId_Service());
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
	public Order_Service create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

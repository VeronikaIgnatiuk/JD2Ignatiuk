package by.academy.dao.mysql;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import by.academy.dao.AbstractJDBCDao;
import by.academy.dao.domain.Car;
import by.academy.dao.domain.Order;
import by.academy.dao.domain.Service;

public class MySqlServiceDao extends AbstractJDBCDao<Service, Integer> {

	   private class PersistService extends Service {
	        public void setId_Service(int id) {
	            super.setId_Service(id);
	        }
	    }
    @Override
    public String getSelectQuery() {
        return "SELECT id_service, title, cost, date, amount FROM autoservice.service ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO autoservice.service ( title, cost, date, amount) \n" +
                "VALUES ( ?, ?, ? , ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE autoservice.service \n" +
                "SET id_service = ?, title = ?, cost = ?, date = ?, amount = ? WHERE id_service = ?\n" ;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM autoservice.service WHERE id_service = ?;";
    }

    @Override
    public Service add() throws Exception {
    	Service s = new Service();
        return add(s);
    }

    public MySqlServiceDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Service> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Service> result = new LinkedList<Service>();
        try {
            while (rs.next()) {
                PersistService service = new PersistService();
                service.setId_Service(rs.getInt("id_Service"));
                service.setTitle(rs.getString("title"));
                service.setCost(rs.getInt("cost"));
                service.setDate(rs.getDate("date"));
                service.setAmount(rs.getInt("amount"));
                result.add(service);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Service object) throws Exception {
        try {
        	Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getId_Service());
            statement.setString(2, object.getTitle());
            statement.setInt(3, object.getCost());
            statement.setDate(4, sqlDate);
            statement.setInt(5, object.getAmount());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Service object) throws Exception {
        try {
        	Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getId_Service());
            statement.setString(2, object.getTitle());
            statement.setInt(3, object.getCost());
            statement.setDate(4, sqlDate);
            statement.setInt(5, object.getAmount());
           
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
	public Service create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

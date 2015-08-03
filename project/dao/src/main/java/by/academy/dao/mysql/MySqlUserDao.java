package by.academy.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import by.academy.dao.AbstractJDBCDao;
import by.academy.dao.domain.User;

public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {

    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, login, password FROM autoservice.user ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO autoservice.user (login, password) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE autoservice.user \n" +
                "SET login = ?, password  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM autoservice.user WHERE id= ?;";
    }

    @Override
    public User add() throws Exception {
        User s = new User();
        return add(s);
    }

    public MySqlUserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistUser user = new PersistUser();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws Exception {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getLogin());
            statement.setString(3, object.getPassword());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws Exception {
        try {
            int Id = (object.getId() == null) ? 0 : object.getId();
 
            statement.setInt(1, Id);
            statement.setString(2, object.getLogin());
            statement.setString(3, object.getPassword());
           
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
	public User create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

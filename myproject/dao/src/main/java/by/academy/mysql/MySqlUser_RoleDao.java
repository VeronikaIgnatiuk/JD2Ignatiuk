package by.academy.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import by.academy.dao.AbstractJDBCDao;
import by.academy.dao.domain.Car;
import by.academy.dao.domain.Order;
import by.academy.dao.domain.User_Role;

public class MySqlUser_RoleDao extends AbstractJDBCDao<User_Role, Integer> {

	   private class PersistUser_Role extends User_Role{
	        /*public void setId_Car(int id) {
	            super.setId_Car(id);
	        }*/
	    }
    @Override
    public String getSelectQuery() {
        return "SELECT id_user, id_role FROM autoservice.user_role ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO autoservice.user_role (id_user, id_role) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE autoservice.user_role \n" +
                "SET id_user = ?, id_role  = ?  \n" ;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM autoservice.user_role WHERE id= ?;";
    }

    @Override
    public User_Role add() throws Exception {
        User_Role s = new User_Role();
        return add(s);
    }

    public MySqlUser_RoleDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<User_Role> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<User_Role> result = new LinkedList<User_Role>();
        try {
            while (rs.next()) {
                PersistUser_Role user_Role = new PersistUser_Role();
                user_Role.setId_User(rs.getInt("id_user"));
                user_Role.setId_Role(rs.getInt("id_role"));
                result.add(user_Role);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User_Role object) throws Exception {
        try {
            statement.setInt(1, object.getId_User());
            statement.setInt(2, object.getId_Role());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User_Role object) throws Exception {
        try {
            statement.setInt(1, object.getId_User());
            statement.setInt(2, object.getId_Role());
           
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
	public User_Role create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

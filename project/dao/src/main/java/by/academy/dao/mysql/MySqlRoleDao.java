package by.academy.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import by.academy.dao.AbstractJDBCDao;
import by.academy.dao.domain.Role;

public class MySqlRoleDao extends AbstractJDBCDao<Role, Integer> {

    private class PersistRole extends Role {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, role FROM autoservice.role ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO autoservice.role (role) \n" +
                "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE autoservice.role \n" +
                "SET role  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM autoservice.role WHERE id= ?;";
    }

    @Override
    public Role create() throws Exception {
        Role s = new Role();
        return add(s);
    }

    public MySqlRoleDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Role> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Role> result = new LinkedList<Role>();
        try {
            while (rs.next()) {
                PersistRole role = new PersistRole();
                role.setId(rs.getInt("id"));
                role.setRole(rs.getString("role"));
                result.add(role);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role object) throws Exception {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getRole());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role object) throws Exception {
        try {
            int Id = (object.getId() == null) ? 0 : object.getId();
 
            statement.setInt(1, Id);
            statement.setString(2, object.getRole());
           
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role add() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}

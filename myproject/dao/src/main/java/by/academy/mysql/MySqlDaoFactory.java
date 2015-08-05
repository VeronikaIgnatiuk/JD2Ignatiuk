package by.academy.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.management.relation.Relation;

import by.academy.dao.DaoFactory;
import by.academy.dao.GenericDao;
import by.academy.dao.domain.Car;
import by.academy.dao.domain.Order;
import by.academy.dao.domain.Order_Service;
import by.academy.dao.domain.Role;
import by.academy.dao.domain.Service;
import by.academy.dao.domain.User;
import by.academy.dao.domain.User_Role;

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/autoservice";
    private String driver = "com.mysql.jdbc.Driver";
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws Exception {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new Exception("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        creators = new HashMap<Class, DaoCreator>();
        creators.put(Car.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlCarDao(connection);
            }
        });
        creators.put(Order.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlOrderDao(connection);
            }
        });
        creators.put(Relation.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlRelationDao(connection);
            }
        });
        creators.put(Role.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlRoleDao(connection);
            }
        });
        creators.put(Service.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlServiceDao(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUserDao(connection);
            }
        });
        creators.put(User_Role.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUser_RoleDao(connection);
            }
        });
    }
}

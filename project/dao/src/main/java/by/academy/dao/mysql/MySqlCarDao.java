package by.academy.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import by.academy.dao.AbstractJDBCDao;
import by.academy.dao.domain.Car;

public class MySqlCarDao extends AbstractJDBCDao<Car, Integer> {

    private class PersistCar extends Car {
        public void setId_Car(int id) {
            super.setId_Car(id);
        }
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT  id_car, id_user, brand, model FROM autoservice.car WHERE id_car = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO autoservice.car ( id_car, id_user, brand, model) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE autoservice.car \n" +
                "SET  id_user  = ? , brand  = ? , model = ? WHERE id_car = ? \n" ;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM autoservice.brand WHERE id= ?;";
    }

    @Override
    public Car add() throws Exception {
        Car s = new Car();
        return add(s);
    }

    public MySqlCarDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Car> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Car> result = new LinkedList<Car>();
        try {
            while (rs.next()) {
                PersistCar car = new PersistCar();
                car.setId_Car(rs.getInt("id_car"));
                car.setId_User(rs.getInt("id_user"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                result.add(car);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Car object) throws Exception {
        try {
            statement.setInt(1, object.getId_Car());
            statement.setInt(2, object.getId_User());
            statement.setString(3, object.getBrand());
            statement.setString(4, object.getModel());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Car object) throws Exception {
        try {
            statement.setInt(1, object.getId_Car());
            statement.setInt(2, object.getId_User());
            statement.setString(3, object.getBrand());
            statement.setString(4, object.getModel());
           
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
	public Car create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}

package by.academy.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import by.academy.dao.domain.User;

public abstract class AbstractJDBCDao<T , PK extends Serializable> implements GenericDao<T, PK> {

    private Connection connection;

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();
    
    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws Exception ;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws Exception ;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws Exception ;

    @Override
    public T add(T object)  {
        T persistInstance = null;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
			
        }
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new Exception("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
        	 e.printStackTrace();
        }
        return persistInstance;
    }

    @Override
    public T getById(Integer id) throws Exception {
        List<T> list = null;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
				e.printStackTrace();
        }
        if (list == null || list.size() == 0) {
           throw new Exception("Record with PK = " + id + " not found.");
        }
        if (list.size() > 1) {
            throw new Exception("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws Exception {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public void delete(T object) throws Exception {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, ((GenericDao<T, PK>) object).getId());
            } catch (Exception e) {
                throw new Exception(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public List<T> getAll() throws Exception {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

	

}

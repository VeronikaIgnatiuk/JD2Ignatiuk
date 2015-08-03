package by.academy.dao;

	import java.io.Serializable;
import java.util.List;

import by.academy.dao.domain.Role;
import by.academy.dao.domain.User;
	
public interface GenericDao<T , PK extends Serializable> {
	 
		public PK getId();
		
		T create () throws Exception;
	
		T add (T newInstance);

		T getById(Integer id) throws Exception;
		
		 void update(T transientObject) throws Exception;
		 
		 void delete(T persistentObject) throws Exception;

		List<T> getAll() throws Exception;

		T add() throws Exception;
		 
		 
}


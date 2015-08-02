package by.academy.dao.domain;

public class Order  {

    private Integer id_User;
    private Integer id_Car;
    private Integer id_Bill;

    public Integer getId_User() {
        return id_User;
    }

    protected void setId_User(int id) { 
    	this.id_User = id;  
    }
    
    public Integer getId_Car() {
        return id_Car;
    }

    protected void setId_Car(int id) { 
    	this.id_Car = id;  
    }
    
    public Integer getId_Bill() {
        return id_Bill;
    }

    protected void setId_Bill(int id) { 
    	this.id_Bill = id;  
    }
    
}

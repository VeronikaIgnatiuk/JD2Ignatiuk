package by.academy.dao.domain;

public class Order_Service  {

    private Integer id_Bill;
    private Integer id_Service;

    public Integer getId_Bill() {
        return id_Bill;
    }

    protected void setId_Bill(int id) { 
    	this.id_Bill = id;  
    }
    public Integer getId_Service() {
        return id_Service;
    }

    protected void setId_Service(int id) { 
    	this.id_Service = id;  
    }
}

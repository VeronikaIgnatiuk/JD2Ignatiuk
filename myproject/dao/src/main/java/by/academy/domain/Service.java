package by.academy.dao.domain;

import java.util.Date;

public class Service  {

    private Integer id_Service;
    private String title;
    private Integer cost;
    private Date date;
    private Integer amount;

    public Integer getId_Service() {
        return id_Service;
    }

    public void setId_Service(int id) { 
    	this.id_Service = id;  
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { 
    	this.title = title;  
    }
    
    public Integer getCost() {
        return cost;
    }

    public void setCost(int cost) { 
    	this.cost = cost;  
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) { 
    	this.date = date;  
    }
    
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) { 
    	this.amount = amount;  
    }

}

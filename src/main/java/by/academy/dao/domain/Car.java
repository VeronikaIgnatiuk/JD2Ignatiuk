package by.academy.dao.domain;

public class Car  {

    private Integer id_Car;
    private Integer id_User;
    private String brand;
    private String model;

    public Integer getId_Car() {
        return id_Car;
    }

    public void setId_Car(int id) { 
    	this.id_Car = id;  
    }
    
    public Integer getId_User() {
        return id_User;
    }

    public void setId_User(int id) { 
    	this.id_User = id;  
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

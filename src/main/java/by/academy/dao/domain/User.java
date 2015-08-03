package by.academy.dao.domain;

public class User  {

    private Integer id = null;
    private String login;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(int id) { 
    	this.id = id;  
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

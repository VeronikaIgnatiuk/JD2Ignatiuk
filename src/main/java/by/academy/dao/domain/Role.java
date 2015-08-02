package by.academy.dao.domain;


public class Role  {

    private Integer id = null;
    private String role;

    public Integer getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

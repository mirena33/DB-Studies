package softuni.wshop.model.binding;

public class RoleAddBindingModel {

    private String username;
    private String role;

    public RoleAddBindingModel() {
    }

    //to do validations

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

package co.edu.uptc.views.users;

public class User implements Cloneable{
    private String nameUser;
    private String password;
    private String email;
    private int id;

    public User(String nameUser, String password, String email, int id) {
        this.nameUser = nameUser;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public User(String nameUser, String password, String email) {
        this.nameUser = nameUser;
        this.password = password;
        this.email = email;
    }
    public User(User user, int id) {
        this.nameUser = user.getNameUser();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

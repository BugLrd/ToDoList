package Entity;

public class User {
    private String name;
    private String username;
    private String gender;
    private String email;
    private String password;

    public User() {}
    public User (String name, String username, String gender, String email, String password) {
        setName(name);
        setUsername(username);
        setGender(gender);
        setEmail(email);
        setPassword(password);
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }

    public void setGender(String gender) { this.gender = gender; }
    public String getGender() { return gender; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }
}

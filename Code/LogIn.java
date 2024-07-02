class LogIn{

    private String username;
    private String password;

    // constructor
    public LogIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // method to check login condition
    public boolean checkCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
}
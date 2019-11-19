package client.model.modelaccount;

public class VOS implements Account {

    private String username;
    private String password;

    public VOS(String username, String password)
    {
        this.username= username;
        this.password= password;
    }

    @Override
    public void setUsername(String Username) {
        this.username= Username;

    }

    @Override
    public void setPassword(String password) {

        if(password.length() >= 8   ) {
            this.password = password;
        }
        else
            System.out.println("Password not strong enough");

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return password;
    }
}

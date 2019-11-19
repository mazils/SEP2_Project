package client.model.modelaccount;



public class Manager implements Account {
    String username;
    String password;

    public Manager(String username, String password)
    {
        this.password= password;
        this.username= username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }



}

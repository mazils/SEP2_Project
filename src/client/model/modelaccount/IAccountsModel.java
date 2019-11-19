package client.model.modelaccount;

public interface IAccountsModel {
    boolean accountExists(Account acc);
    boolean accountExists(String username,String password);
    boolean checkUsername(String username);

}

package UserAccounts;

import java.util.ArrayList;

public interface AccountInterface {

    public void createAccount();

    public ArrayList login() throws InvalidPasswordException, InactiveAccountException;

    public void deleteAccount();

}

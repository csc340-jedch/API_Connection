package UserAccounts;

import java.util.ArrayList;
import java.util.Map;

public interface AccountInterface {

    public User login() throws InvalidPasswordException, InactiveAccountException;

    public void deleteAccount() throws InactiveAccountException, InvalidPasswordException;

    public User searchAccount() throws InactiveAccountException;

}

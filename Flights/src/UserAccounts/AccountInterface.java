package UserAccounts;

import java.util.ArrayList;
import java.util.Map;

public interface AccountInterface {

    public User login() throws InvalidPasswordException, InactiveAccountException;

    void deleteAccount() throws InactiveAccountException, InvalidPasswordException;

    private User searchAccount() throws InactiveAccountException, InvalidPasswordException {
        return null;
    }

}

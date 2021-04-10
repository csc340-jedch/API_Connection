package UserAccounts;

import java.util.*;

public class Account extends User implements AccountInterface {

    Scanner in = new Scanner(System.in);
    public static int INACTIVE_ACCOUNT = -1;

    public User createAccount(String username,String password,String name,String email,String phonenumber,String birthday, String gender,String location){
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phonenumber);
        newUser.setBirthday(birthday);
        newUser.setGender(gender);
        newUser.setLocation(location);
        newUser.setStatus();
        return newUser;
    }

    public String inputUserName(){
        System.out.println("What is your Username");
        return in.nextLine().toLowerCase();
    }

    public String inputPassword(){
        System.out.println("What is your password");
        return in.nextLine();
    }

    //Next part needs to search through database for usernames.
    // Checks if one is there
    // Get information from the database
    //DOESNT WORK NOW
   public User searchAccount() throws InactiveAccountException {
            String username = inputUserName();
            //finds account
       User account = new User(); //Just used to make this compile. Not useful as of now
       if (account.getStatus() == INACTIVE_ACCOUNT) {
           throw new InactiveAccountException("Account doesn't exist");
       }
       if (account.getUserName().equals(username)) {
           return account;
       }
       throw new InactiveAccountException("Can't find account");
    }


    public User login() throws InvalidPasswordException, InactiveAccountException {
        User account = searchAccount();
        String password = inputPassword();
        if (account.getStatus() == INACTIVE_ACCOUNT) {
            throw new InactiveAccountException("Inactive Account");
        }
        if(account.getPassword().equals(password)) {
            return account;
        } else {
            throw new InvalidPasswordException("Incorrect Password");
        }
    }

    public void deleteAccount() throws InactiveAccountException, InvalidPasswordException {
        User account = login();
        account.setStatus(INACTIVE_ACCOUNT);
        System.out.println("Account Deleted");
    }




}

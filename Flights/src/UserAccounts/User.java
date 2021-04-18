package UserAccounts;

import java.util.*;

public class User implements AccountInterface{

    Scanner in = new Scanner(System.in);

    protected static String name;
    protected static String email;
    protected static String phoneNumber;
    protected static String birthday;
    protected static String gender;
    protected static String location;
    public static final int INACTIVE_ACCOUNT = -1;
    public static final int ACTIVE_ACCOUNT = 1;
    protected static String username;
    protected static String password;
    protected static int status;

    public User(String _name,String _email,String _phoneNumber,String _birthday, String _gender,String _location,String _username,String _password){
        this.name = _name;
        this.email = _email;
        this.phoneNumber = _phoneNumber;
        this.birthday = _birthday;
        this.gender = _gender;
        this.location = _location;
        this.username = _username;
        this.password = _password;
        this.status = ACTIVE_ACCOUNT;
    }

    //=================  GETTERS ===============

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getBirthday(){
        return birthday;
    }

    public String getGender(){
        return gender;
    }

    public String getLocation(){
        return location.toUpperCase();
    }

    public String getUserName() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int getStatus(){
        return status;
    }

    //=================  SETTERS ===============

    public void setName(String _name) {
       name = _name;
    }

    public void setEmail(String _email) {
        email = _email;
    }

    public void setPhoneNumber(String _phoneNumber) {
        phoneNumber = _phoneNumber;
    }

    public void setBirthday(String _birthday) {
        birthday = _birthday;
    }

    public void setGender(String _gender) {
        gender = _gender;
    }

    public void setLocation(String _location) {
        location = _location;
    }

    public void setUserName(String _username) {
        username = _username;
    }

    public void setPassword(String _password) {
        password = _password;
    }

    public void setStatus(){
        status = ACTIVE_ACCOUNT;
    }

    public void resetStatus(int _status){
        status = _status;
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
    private User searchAccount() throws InactiveAccountException, InvalidPasswordException {
        String username = inputUserName();
        //finds account
        User account = login(); //Just used to make this compile. Not useful as of now
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
        if(account.getPassword().equals(password)) {
            throw new InvalidPasswordException("Incorrect Password");
        } else if(account.getStatus() == INACTIVE_ACCOUNT) {
            throw new InactiveAccountException("Account does not exist");
        }
        else{
            return account;
        }
    }

    public void deleteAccount() throws InactiveAccountException, InvalidPasswordException {
        User account = login();
        account.resetStatus(INACTIVE_ACCOUNT);
        System.out.println("Account Deleted");
    }

    @Override
    public String toString() {
        return  username + ":" + " , " +
                name  + ":" + " , " +
                email + ":" + " , " +
                phoneNumber + ":" + " , " +
                birthday + ":" + " , " +
                gender + ":" + " , " +
                location  + ":" + " , " +
                status;
    }

}

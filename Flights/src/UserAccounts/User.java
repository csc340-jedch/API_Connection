package UserAccounts;

import java.util.*;

public class User {

    Scanner in = new Scanner(System.in);

    public static int ACTIVE_ACCOUNT = 1;
    String Username;
    String Password;
    String Name;
    String Email;
    String PhoneNumber;
    String Birthday;
    String Gender;
    String Location;
    int Status;

    public void setUserName(String _username) {
        Username = _username;
    }

    public String getUserName() {
        return Username;
    }

    public void setPassword(String _password) {
        Password = _password;
    }

    public String getPassword(){
        return Password;
    }

    public void setName(String _name) {
       Name = _name;
    }

    public String getName(){
        return Name;
    }

    public void setEmail(String _email) {
        Email = _email;
    }

    public String getEmail(){
        return Email;
    }

    public void setPhoneNumber(String _phonenumber) {
        PhoneNumber = _phonenumber;
    }

    public String getPhoneNumber(){
        return PhoneNumber;
    }

    public void setBirthday(String _birthday) {
        Birthday = _birthday;
    }

    public String getBirthday(){
        return Birthday;
    }

    public void setGender(String _gender) {
        Gender = _gender;
    }

    public String getGender(){
        return Gender;
    }

    public void setLocation(String _location) {
        Location = _location;
    }

    public String getLocation(){
        return Location.toUpperCase();
    }

    public void setStatus(int _status){
        Status = _status;
    }

    public void setStatus(){
        Status = ACTIVE_ACCOUNT;
    }

    public int getStatus(){
        return Status;
    }

    @Override
    public String toString() {
        return Username + ":" + " , " +
                Name  + ":" + " , " +
                Email + ":" + " , " +
                PhoneNumber + ":" + " , " +
                Birthday + ":" + " , " +
                Gender + ":" + " , " +
                Location + ":" + " , " +
                Status;
    }
}

package UserAccounts;

import java.util.*;

public class Account implements AccountInterface {

    Scanner in = new Scanner(System.in);
    Map<String, ArrayList> accountInformation = new HashMap<String, ArrayList>();
    public static String ACTIVE_ACCOUNT = "1";
    public static String INACTIVE_ACCOUNT = "1";
    public static final int ACCOUNT_STATUS = 8;
    public static final int PASSWORD = 0;
    public static final int TEMP_USERNAME = 0;

        public void createAccount(){
        //Input contents in this order: userName, password, Name, Email, Phone, Birthday, Gender, Location
        ArrayList<String> contents = getData();

        String userName = contents.get(TEMP_USERNAME);
        contents.remove(TEMP_USERNAME); //remove username to use as the key

        inputData(userName, contents);

        System.out.println("Account Created");

    }

    public ArrayList<String> getData(){
        ArrayList<String> contents = new ArrayList<String>();

        String userName = in.nextLine().toLowerCase();
        String password = in.nextLine();
        String name = in.nextLine();
        String email = in.nextLine();
        String phone = in.nextLine().toString();
        String birthday = in.nextLine().toString();
        String gender = in.nextLine();
        String location = in.nextLine().toString();

        contents.add(userName);
        contents.add(password);
        contents.add(name);
        contents.add(email);
        contents.add(phone);
        contents.add(birthday);
        contents.add(gender);
        contents.add(location);
        contents.add(ACTIVE_ACCOUNT);

        return contents;
    }


    public void inputData(String userName, ArrayList<String> contents){

        accountInformation.put(userName, contents);
    }

    public ArrayList login() throws InvalidPasswordException, InactiveAccountException {
        String username = in.nextLine().toLowerCase();
        String password = in.nextLine();

       ArrayList getAccountInformation = accountInformation.get(username);

        if (getAccountInformation.get(ACCOUNT_STATUS).equals(ACTIVE_ACCOUNT)) {
            if (getAccountInformation.get(PASSWORD).equals(password)) {
                return getAccountInformation;
            }
            throw new InvalidPasswordException("Incorrect password.");
        }
        throw new InactiveAccountException("This account is Inactive");
       }


    public void deleteAccount() {
        String username = in.nextLine().toLowerCase();
            ArrayList work = accountInformation.get(username);
            work.remove(ACCOUNT_STATUS);
            work.add(INACTIVE_ACCOUNT);
        System.out.println("Account deleted.");
    }




}

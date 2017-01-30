package TestCases.database;

import Database.AuthorizeController;
import Message.Authorization.Authorize;

import java.util.Scanner;

/**
 * Created by Nikolay on 1/25/2017.
 */
public class Logint {
    public static void main(String[] args){
        AuthorizeController authorizeController = new AuthorizeController();
        Scanner sc = new Scanner(System.in);
        while(true){
            String username = sc.next();
            String password = sc.next();
            System.out.println(authorizeController.login(username,password));
        }
    }
}

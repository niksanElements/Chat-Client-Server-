package TestCases;

import Models.MD5Converter;

import java.util.Scanner;

/**
 * Created by Nikolay on 1/25/2017.
 */
public class Password {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print(" Enter pass word fo hash conver: ");
        String passwod = sc.next();
        String newPassword = MD5Converter.convert(passwod);
        String newPassword2 = MD5Converter.convert(passwod);
        System.out.println(passwod + " = "+ newPassword);
        System.out.println(passwod + " = "+ newPassword2);
    }
}

package TestCases.database;

import Database.AuthorizeController;

/**
 * Created by Nikolay on 1/25/2017.
 */
public class Registration {
    public static void main(String[] args){
        AuthorizeController authorizeController = new AuthorizeController();
        System.out.println(authorizeController.registration("niksan","12345","Nikolay","Nikolov"));
        System.out.println(authorizeController.registration("alex","12345","Alex","Alex"));
        System.out.println(authorizeController.registration("pesho","12345","Petrov","Nikra"));
        System.out.println(authorizeController.registration("mako","12345","Mrkov","Markov"));
        System.out.println(authorizeController.registration("mimi","12345","Miroslava","Emilova"));
        System.out.println(authorizeController.registration("mara","12345","Marava","Marova"));
        System.out.println(authorizeController.registration("algel","12345","Angel","Kostadinov"));
    }
}

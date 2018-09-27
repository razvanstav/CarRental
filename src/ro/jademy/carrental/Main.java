package ro.jademy.carrental;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop();
        Scanner scan = new Scanner(System.in);
        String user ;
        String password;
        do {
            user = scan.nextLine();
            password = scan.nextLine();

        } while (!shop.login(user,password));

        shop.showMenu();
    }
}

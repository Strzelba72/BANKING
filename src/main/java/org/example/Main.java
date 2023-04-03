package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {



        Display();


    }
    public static void Display()
    {
        Datasource datasource=new Datasource();
        if(!datasource.open())
        {
            System.out.println("Something went wrong");
            System.out.println("!!!");
            return;
        }

        System.out.println("SELECT :");
        System.out.println("1. CLIENT BANK LOGIN");
        System.out.println("2. ADMIN LOGIN");
        Scanner scanner =new Scanner(System.in);
        Boolean var_ =true;
        switch (scanner.next())
        {
            case "1"://LOGIN PANEL
            {
                Access a=datasource.account(Datasource.inputLogin(),Datasource.inputPassword());
                if(a==null)
                    break;
                System.out.println(a.getNumber_account());
                Client example=datasource.clientAccount(a.getNumber_account());
                example.display();
                Boolean var =true;
                while(var){
                    System.out.println("1. DISPLAY INFO");
                    System.out.println("2. STANDARD TRANSFER");
                    System.out.println("3. EXIT");
                    switch (scanner.next()){
                        case "1":
                        {
                            example.display();
                            break;
                        }
                        case "2":
                        {
                            System.out.println("NUMBER ACCOUNT: ");
                            String numTrans=scanner.next();
                            System.out.println("VALUE: ");
                            float valTrans=scanner.nextFloat();

                            datasource.transfer(example,numTrans,valTrans);
                            break;
                        }
                        case "3":
                        {
                            var=false;
                            break;
                        }
                    }
                }

                Display();
            }
            case "2":
            {
                System.out.println("LOGIN: ");
                String loginAdmin=scanner.next();
                System.out.println("PASSWORD: ");
                String passAdmin=scanner.next();
                if(!loginAdmin.equals("admin") || !passAdmin.equals("123"))
                {
                    Display();
                }


                List<Client> clients=datasource.queryClients();
                for(Client client: clients)
                {
                     client.display();
                }
                 datasource.close();


                Display();
                break;
            }
            default:
            {
                System.out.println("Something went wrong");
                Display();
            }
        }
        datasource.close();
    }
}
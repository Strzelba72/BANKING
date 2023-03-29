package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Datasource datasource=new Datasource();
        if(!datasource.open())
        {
            System.out.println("Something went wrong");
            return;
        }
        //List<Client> clients=datasource.queryClients();
       // for(Client client: clients)
       // {
           // client.display();
       // }
       // datasource.close();
        Access a=datasource.account(Datasource.inputLogin(),Datasource.inputPassword());
        System.out.println(a.getNumber_account());
        Client example=datasource.clientAccount(a.getNumber_account());
        example.display();
        datasource.transfer(example,"1234567890",100);
    }
}
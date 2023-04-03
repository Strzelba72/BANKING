package org.example;

import java.sql.*;
import java.util.*;

public class Datasource {
    public static final String CONNECTION="jdbc:sqlite:/home/mikolaj/Programing/JAVA/BANKING/bank.db";

    public static final String NAME="name";
    public static final String LAST_NAME="last_name";
    public static final String DAY_OF_BIRTH="day_of_birth";
    public static final String TELEPHONE="telephone";
    public static final String NUMBER_ACCOUNT="number_account";
    public static final String BALANCE_ACCOUNT="balance_account";
    public static final String TABLE_CLIENTS="clients";
    public static final String TABLE_ACCESSES="accesses";
    public static final String LOGIN="login";
    public static final String PASSWORD="password";

    private Connection conn;
    //OPEN DB AND CREATE CONNECTION
    public boolean open()
    {
        try {

            conn= DriverManager.getConnection(CONNECTION);
            return true;

        }catch (SQLException e)
        {
            System.out.println("Something went wrong "+e.getMessage());
            System.out.println("!!!");
            return false;
        }
    }
    //CLOSE DB CONNECTION
    public void close()
    {
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e)
        {
            System.out.println("Something went wrong "+e.getMessage());
        }
    }
    //query which add all clients in DB to LIST of clients
    public List<Client> queryClients()
    {

        try( Statement statement=conn.createStatement();
        ResultSet result=statement.executeQuery("SELECT * FROM "+TABLE_CLIENTS);)
        {

            List<Client> clients=new ArrayList<>();

            while(result.next())
            {
                Client client=new Client();
                client.setName(result.getString(NAME));
                client.setLastName(result.getString(LAST_NAME));
                client.setDayOfBirth(result.getString(DAY_OF_BIRTH));
                client.setTelephoneNumber(result.getInt(TELEPHONE));
                client.setNumberAccount(result.getString(NUMBER_ACCOUNT));
                client.setBalanceAccount(result.getFloat(BALANCE_ACCOUNT));
                clients.add(client);
            }
            return clients;

        }catch (SQLException e)
        {
            System.out.println("Something went wrong "+e.getMessage());
        }


        return null;
    }
    //return access to account by login and password
    public Access account(String log,String pass) {
        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM "+TABLE_ACCESSES+" WHERE login='"+log+"'and password='"+pass+"';");) {
            Access access = new Access();
            access.setNumber_account(result.getString(NUMBER_ACCOUNT));
            access.setLogin(result.getString(LOGIN));
            access.setPassword(result.getString(PASSWORD));
            System.out.println(access.getNumber_account());
            return access;


        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
        return null;
    }
    //return Client by obtained access
    public Client clientAccount(String numAccount)
    {
        try(Statement statement= conn.createStatement();
        ResultSet result= statement.executeQuery("SELECT * FROM "+TABLE_CLIENTS+" WHERE "+NUMBER_ACCOUNT+" = '"+numAccount+"';"))
        {
            Client client=new Client();
            client.setName(result.getString(NAME));
            client.setLastName(result.getString(LAST_NAME));
            client.setDayOfBirth(result.getString(DAY_OF_BIRTH));
            client.setTelephoneNumber(result.getInt(TELEPHONE));
            client.setNumberAccount(result.getString(NUMBER_ACCOUNT));
            client.setBalanceAccount(result.getFloat(BALANCE_ACCOUNT));
            return client;

        }catch (SQLException e)
        {
            System.out.println("Something went wrong " + e.getMessage());
        }
        if(numAccount==null)
            System.out.println("Client account by obtained number accounts don't exist.");
            return null;
    }
    //make a transfer
    public void send(String numAccount)
    {

    }
    public void send(int numAccount)
    {

    }
    public void transfer(Client client,String numAccount,float value)
    {
        if((client.getBalanceAccount()-value)>=0)
        {
            if(this.clientAccount(numAccount).equals(null))
            {
                System.out.println("Client with this account numbers doesn't exist.");
            }
            else {
                try(Statement statement= conn.createStatement();
                   )
                {
                    statement.executeUpdate("UPDATE "+TABLE_CLIENTS+" SET "+BALANCE_ACCOUNT+"="+BALANCE_ACCOUNT+"+"+value+" WHERE "+NUMBER_ACCOUNT+" = '"+numAccount+"';");
                    statement.executeUpdate("UPDATE "+TABLE_CLIENTS+" SET "+BALANCE_ACCOUNT+"="+BALANCE_ACCOUNT+"-"+value+" WHERE "+NUMBER_ACCOUNT+" = '"+client.getNumberAccount()+"';");


                }catch (SQLException e)
                {
                    System.out.println("Something went wrong " + e.getMessage());
                }

            }

        }else {
            System.out.println("Balance account can't be on minus");
        }
    }
    public static String inputLogin(){
        System.out.println("LOGIN :");
        Scanner scanner=new Scanner(System.in);
        String logS=scanner.next();
        System.out.println("");
        if(logS.length()!=5)
        {

            System.out.println("Login format incorrect");
            return null;
        }
        for(int i=0;i<logS.length();i++)
        {
            if(logS.charAt(i)<'0' || logS.charAt(i)>'9')
            {
                System.out.println("Login format incorrect");
                return null;
            }
        }
        return logS;

    }
    public static String inputPassword()
    {
        System.out.println("PASSWORD :");
        Scanner scanner=new Scanner(System.in);
        return scanner.next();
    }
}

package org.example;

public class Client {
    private String name;
    private String lastName;
    private String dayOfBirth;
    private int telephoneNumber;
    private String numberAccount;
    private float balanceAccount;

    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public float getBalanceAccount() {
        return balanceAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public void setBalanceAccount(float balanceAccount) {
        this.balanceAccount = balanceAccount;
    }
    //DISPLAY CLIENT
    public void display()
    {
        System.out.println("NAME : "+this.getName()+", LAST NAME : "+this.getLastName()+", DAY OF BIRTH : "+this.getDayOfBirth()+", TELEPHONE NUMBER : "+this.getTelephoneNumber());
        System.out.println("NUMBER ACCOUNT : "+this.numberAccount+", BALANCE ACCOUNT : "+this.balanceAccount);
    }
}

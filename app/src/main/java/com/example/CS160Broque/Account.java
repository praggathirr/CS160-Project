package com.example.CS160Broque;

import android.os.Parcel;
import android.os.Parcelable;

// This class creates an Account Object using data retrieved from the Database
// Newly created accounts will be initialized with 0 values.
public class Account implements Parcelable {
    private String fullName;
    private String userName;
    private String password;
    private String phoneNumber;

    private double currentTotalBudget;
    private double currentBillsBudget;
    private double currentFoodBudget;
    private double currentEntertainmentBudget;
    private double currentOtherBudget;

    private double staticTotalBudget;
    private double staticBillsBudget;
    private double staticFoodBudget;
    private double staticEntertainmentBudget;
    private double staticOtherBudget;

    public Account(){
        this.fullName = "";
        this.userName = "";
        this.password = "";
        this.phoneNumber = "";

        this.currentTotalBudget = 0;
        this.currentBillsBudget = 0;
        this.currentFoodBudget = 0;
        this.currentEntertainmentBudget = 0;
        this.currentOtherBudget = 0;

        this.staticTotalBudget = 0;
        this.staticBillsBudget = 0;
        this.staticFoodBudget = 0;
        this.staticEntertainmentBudget = 0;
        this.staticOtherBudget = 0;
    }// Account Default Constructor

    public Account(String fullName, String userName, String password, String phoneNumber){
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }// Account Constructor

    protected Account(Parcel in) {
        fullName = in.readString();
        userName = in.readString();
        password = in.readString();
        phoneNumber = in.readString();

        currentTotalBudget = in.readDouble();
        currentBillsBudget = in.readDouble();
        currentFoodBudget = in.readDouble();
        currentEntertainmentBudget = in.readDouble();
        currentOtherBudget = in.readDouble();

    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCurrentTotalBudget() {
        return currentTotalBudget;
    }

    public void setCurrentTotalBudget(double currentTotalBudget) {
        this.currentTotalBudget = currentTotalBudget;
    }

    public double getCurrentBillsBudget() {
        return currentBillsBudget;
    }

    public void setCurrentBillsBudget(double currentBillsBudget) {
        this.currentBillsBudget = currentBillsBudget;
    }

    public double getCurrentFoodBudget() {
        return currentFoodBudget;
    }

    public void setCurrentFoodBudget(double currentFoodBudget) {
        this.currentFoodBudget = currentFoodBudget;
    }

    public double getCurrentEntertainmentBudget() {
        return currentEntertainmentBudget;
    }

    public void setCurrentEntertainmentBudget(double currentEntertainmentBudget) {
        this.currentEntertainmentBudget = currentEntertainmentBudget;
    }

    public double getCurrentOtherBudget() {
        return currentOtherBudget;
    }

    public void setCurrentOtherBudget(double currentOtherBudget) {
        this.currentOtherBudget = currentOtherBudget;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(currentTotalBudget);
        parcel.writeDouble(currentBillsBudget);
        parcel.writeDouble(currentFoodBudget);
        parcel.writeDouble(currentEntertainmentBudget);
        parcel.writeDouble(currentOtherBudget);

        parcel.writeString(fullName);
        parcel.writeString(userName);
        parcel.writeString(password);
        parcel.writeString(phoneNumber);
    }

    // Updates budgets
    public void insertBudgets(double bills, double food, double entertainment, double other) {
        currentTotalBudget = bills + food + entertainment + other;
        currentBillsBudget = bills;
        currentFoodBudget = food;
        currentEntertainmentBudget = entertainment;
        currentOtherBudget = other;
    }
}// Account

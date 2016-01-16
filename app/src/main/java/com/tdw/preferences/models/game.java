package com.tdw.preferences.models;

/**
 * Created by siddhaja on 1/10/2016.
 */
public class game {

    public game(float er1,float er2,float er3,float er4,float er5,float er6,int soonerDays, int laterDays, int gameType){
        exchangeRate1 = er1;
        exchangeRate2 = er2;
        exchangeRate3 = er3;
        exchangeRate4 = er4;
        exchangeRate5 = er5;
        exchangeRate6 = er6;
        numberOfDaystoSoonerDate = soonerDays;
        numberOfDaystoLaterDate = laterDays;
        this.gameType = gameType;
    }

    public float getExchangeRate1() {
        return exchangeRate1;
    }

    public void setExchangeRate1(float exchangeRate1) {
        this.exchangeRate1 = exchangeRate1;
    }

    public float getExchangeRate2() {
        return exchangeRate2;
    }

    public void setExchangeRate2(float exchangeRate2) {
        this.exchangeRate2 = exchangeRate2;
    }

    public float getExchangeRate3() {
        return exchangeRate3;
    }

    public void setExchangeRate3(float exchangeRate3) {
        this.exchangeRate3 = exchangeRate3;
    }

    public float getExchangeRate4() {
        return exchangeRate4;
    }

    public void setExchangeRate4(float exchangeRate4) {
        this.exchangeRate4 = exchangeRate4;
    }

    public float getExchangeRate5() {
        return exchangeRate5;
    }

    public void setExchangeRate5(float exchangeRate5) {
        this.exchangeRate5 = exchangeRate5;
    }

    public float getExchangeRate6() {
        return exchangeRate6;
    }

    public void setExchangeRate6(float exchangeRate6) {
        this.exchangeRate6 = exchangeRate6;
    }

    public int getNumberOfDaystoSoonerDate() {
        return numberOfDaystoSoonerDate;
    }

    public void setNumberOfDaystoSoonerDate(int numberOfDaystoSoonerDate) {
        this.numberOfDaystoSoonerDate = numberOfDaystoSoonerDate;
    }

    public int getNumberOfDaystoLaterDate() {
        return numberOfDaystoLaterDate;
    }

    public void setNumberOfDaystoLaterDate(int numberOfDaystoLaterDate) {
        this.numberOfDaystoLaterDate = numberOfDaystoLaterDate;
    }

    public int getNumberOfDaystoCashRewardDate() {
        return numberOfDaystoCashRewardDate;
    }

    public void setNumberOfDaystoCashRewardDate(int numberOfDaystoCashRewardDate) {
        this.numberOfDaystoCashRewardDate = numberOfDaystoCashRewardDate;
    }

    float exchangeRate1;
    float exchangeRate2;
    float exchangeRate3;
    float exchangeRate4;
    float exchangeRate5;
    float exchangeRate6;
    int numberOfDaystoSoonerDate;
    int numberOfDaystoLaterDate;
    int gameType;
    int numberOfDaystoCashRewardDate; //only for section F Games
}

package com.example.mintlux.crowdfunding;

/**
 * Created by mintlux on 6/12/17.
 */

public class Projekt {
    private int projektID;
    private String projektName;
    private String projektType;
    private String description;
    private double minFunding;
    private String endOfFundingPeriod;
    private String startOfPayback;
    private double interestRate;

    public Projekt(int projektID, String projektName, String projektType, String description, double minFunding, String endOfFundingPeriod, String startOfPayback, double interestRate) {
        this.projektID = projektID;
        setProjektName(projektName);
        setProjektTyp(projektType);
        setDescription(description);
        setMinFunding(minFunding);
        setEndOfFundingPeriod(endOfFundingPeriod);
        setStartOfPayback(startOfPayback);
        setInterestRate(interestRate);
    }

    public void setProjektName(String projektName) {
        this.projektName = projektName;
    }

    public void setProjektTyp(String projektType) {
        this.projektType = projektType;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setMinFunding(double minFunding) {
        this.minFunding = minFunding;
    }
    public void setEndOfFundingPeriod(String endOfFundingPeriod) {
        this.endOfFundingPeriod = endOfFundingPeriod;
    }
    public void setStartOfPayback(String startOfPayback) {
        this.startOfPayback = startOfPayback;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public  int getProjektID() {
        return  this.projektID;
    }

    public String getProjektName() {
        return this.projektName;
    }

    public String getProjektType() {
        return this.projektType;
    }

    public String getDescription() {
        return this.description;
    }
    public double getMinFunding() {
        return this.minFunding;
    }
    public String getEndOfFundingPeriod() {
        return this.endOfFundingPeriod;
    }
    public String getStartOfPayback() {
        return this.startOfPayback;
    }
    public double getInterestRate() {
        return this.interestRate;
    }

    public String toString() {
        return this.projektName + " " + this.projektType;
    }
}

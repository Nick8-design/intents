package com.nickdieda.intents;

public class LoanApplication {
private String name,institute,regno;
private int loanAmount;

    public LoanApplication(String name, String institute, String regno, int loanAmount) {
        this.name = name;
        this.institute = institute;
        this.regno = regno;
        this.loanAmount = loanAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }
}

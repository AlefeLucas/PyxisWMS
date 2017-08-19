/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.model.warehouse.process.Loan;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Aluno
 */
public class TableLoanAdapter {

    private Loan loan;
    private String customer;
    private String status;
    private String loanDate;
    private String expDate;
    private String returnDate;
    private String annotations;

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;

        this.customer = loan.getCustomer().getName();
        this.status = loan.getStatus().toString();
        Date loanDate = loan.getLoanDate();
        Date expDate = loan.getExpReturnDate();
        Date returnDate = loan.getReturnDate();
        if (loanDate != null) {
            this.loanDate = new SimpleDateFormat("dd/MM/yyyy").format(loanDate);
        } else {
            this.loanDate = "";
        }
        if (expDate != null) {
            this.expDate = new SimpleDateFormat("dd/MM/yyyy").format(expDate);
        } else {
            this.expDate = "";
        }
        if (returnDate != null) {
            this.returnDate = new SimpleDateFormat("dd/MM/yyyy").format(returnDate);
        } else {
            this.returnDate = "";
        }
        this.annotations = loan.getAnnotations();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public TableLoanAdapter(Loan loan) {
        setLoan(loan);
    }

}

package com.fintechapp.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction_History")
public class TransactionEntity {
	@Id
	@GeneratedValue
	private int trandId;
	private String userEmail;
	private String accNumber;
	private double amount;
	private String transType;
	private double debitAmount;
	private double creditAmount;
	private String narration;
	private double balanceAt;
	private LocalDate date;

	public TransactionEntity() {

	}

	public TransactionEntity(int trandId, String userEmail, String accNumber, double amount, String transType,
			double debitAmount, double creditAmount, String narration, double balanceAt, LocalDate date) {
		
		this.trandId = trandId;
		this.userEmail = userEmail;
		this.accNumber = accNumber;
		this.amount = amount;
		this.transType = transType;
		this.debitAmount = debitAmount;
		this.creditAmount = creditAmount;
		this.narration = narration;
		this.balanceAt = balanceAt;
		this.date = date;
	}

	public int getTrandId() {
		return trandId;
	}

	public void setTrandId(int trandId) {
		this.trandId = trandId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public double getBalanceAt() {
		return balanceAt;
	}

	public void setBalanceAt(double balanceAt) {
		this.balanceAt = balanceAt;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
	}

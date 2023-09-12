package com.example.demo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="loan_card")
public class Loan {
	@Id
	@Column(length=15)
	@NotNull
	private String loan_id;
	@NotEmpty(message="loan type cannot be empty")
	private String loan_type;
	@NotNull
	private int loan_duration;
	public Loan()
	{}

	
	public Loan(String id,String ltype,int duration) {
		this.loan_id=id;
		this.loan_type=ltype;
		this.loan_duration=duration;
	}
	
	public String getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}

	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
	public int getLoan_duration() {
		return loan_duration;
	}
	public void setLoan_duration(int loan_duration) {
		this.loan_duration = loan_duration;
	}
	

}

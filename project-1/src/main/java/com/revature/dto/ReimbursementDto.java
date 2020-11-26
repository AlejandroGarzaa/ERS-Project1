package com.revature.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.revature.models.Reimbursement;

public class ReimbursementDto implements Serializable {
	
	private int reimId;
	private int reimAmount;
	private String reimSubmit;
	private String reimResolve;
	private String reimDescription;
	private int author;
	private int resolver;
	private int statusId;
	private int typeId;
	
	
	public ReimbursementDto() {
		super();
	}


	public ReimbursementDto(int reimAmount, String reimSubmit, String reimResolve, String reimDescription,
			int author, int resolver, int statusId, int typeId) {
		super();
		this.reimAmount = reimAmount;
		this.reimSubmit = reimSubmit;
		this.reimResolve = reimResolve;
		this.reimDescription = reimDescription;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}


	public ReimbursementDto(int reimId, int reimAmount, String reimSubmit, String reimResolve,
			String reimDescription, int author, int resolver, int statusId, int typeId) {
		super();
		this.reimId = reimId;
		this.reimAmount = reimAmount;
		this.reimSubmit = reimSubmit;
		this.reimResolve = reimResolve;
		this.reimDescription = reimDescription;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}


	public int getReimId() {
		return reimId;
	}


	public void setReimId(int reimId) {
		this.reimId = reimId;
	}


	public int getReimAmount() {
		return reimAmount;
	}


	public void setReimAmount(int reimAmount) {
		this.reimAmount = reimAmount;
	}


	public String getReimSubmit() {
		return reimSubmit;
	}


	public void setReimSubmit(String reimSubmit) {
		this.reimSubmit = reimSubmit;
	}


	public String getReimResolve() {
		return reimResolve;
	}


	public void setReimResolve(String reimResolve) {
		this.reimResolve = reimResolve;
	}


	public String getReimDescription() {
		return reimDescription;
	}


	public void setReimDescription(String reimDescription) {
		this.reimDescription = reimDescription;
	}


	public int getAuthor() {
		return author;
	}


	public void setAuthor(int author) {
		this.author = author;
	}


	public int getResolver() {
		return resolver;
	}


	public void setResolver(int resolver) {
		this.resolver = resolver;
	}


	public int getStatusId() {
		return statusId;
	}


	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}


	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + author;
		result = prime * result + reimAmount;
		result = prime * result + ((reimDescription == null) ? 0 : reimDescription.hashCode());
		result = prime * result + reimId;
		result = prime * result + ((reimResolve == null) ? 0 : reimResolve.hashCode());
		result = prime * result + ((reimSubmit == null) ? 0 : reimSubmit.hashCode());
		result = prime * result + resolver;
		result = prime * result + statusId;
		result = prime * result + typeId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementDto other = (ReimbursementDto) obj;
		if (author != other.author)
			return false;
		if (reimAmount != other.reimAmount)
			return false;
		if (reimDescription == null) {
			if (other.reimDescription != null)
				return false;
		} else if (!reimDescription.equals(other.reimDescription))
			return false;
		if (reimId != other.reimId)
			return false;
		if (reimResolve == null) {
			if (other.reimResolve != null)
				return false;
		} else if (!reimResolve.equals(other.reimResolve))
			return false;
		if (reimSubmit == null) {
			if (other.reimSubmit != null)
				return false;
		} else if (!reimSubmit.equals(other.reimSubmit))
			return false;
		if (resolver != other.resolver)
			return false;
		if (statusId != other.statusId)
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

}

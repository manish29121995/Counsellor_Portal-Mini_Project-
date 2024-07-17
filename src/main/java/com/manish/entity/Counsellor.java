package com.manish.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Counsellor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private Integer counsellotId;
private String name;
private String email;
private String pwd;
private String phno;

@CreationTimestamp
private LocalDate createDate;

@UpdateTimestamp
private LocalDate updateDate;

@OneToMany(mappedBy = "counsellor", cascade = CascadeType.ALL)
private List<Enquiry> enquries;

public Integer getCounsellotId() {
	return counsellotId;
}

public void setCounsellotId(Integer counsellotId) {
	this.counsellotId = counsellotId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPwd() {
	return pwd;
}

public void setPwd(String pwd) {
	this.pwd = pwd;
}

public String getPhno() {
	return phno;
}

public void setPhno(String phno) {
	this.phno = phno;
}

public LocalDate getCreateDate() {
	return createDate;
}

public void setCreateDate(LocalDate createDate) {
	this.createDate = createDate;
}

public LocalDate getUpdateDate() {
	return updateDate;
}

public void setUpdateDate(LocalDate updateDate) {
	this.updateDate = updateDate;
}

public List<Enquiry> getEnquries() {
	return enquries;
}

public void setEnquries(List<Enquiry> enquries) {
	this.enquries = enquries;
}


}

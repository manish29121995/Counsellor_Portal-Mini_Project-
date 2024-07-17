package com.manish.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
private Integer enqId;
private String studentName;
private String studentPhno;
private String mode;
private String course;
private String status;

@CreationTimestamp
private LocalDate createDate;

@UpdateTimestamp
private LocalDate updateDate;

@ManyToOne
@JoinColumn(name = "counsellor_id")
private Counsellor counsellor;

public Integer getEnqId() {
	return enqId;
}

public void setEnqId(Integer enqId) {
	this.enqId = enqId;
}

public String getStudentName() {
	return studentName;
}

public void setStudentName(String studentName) {
	this.studentName = studentName;
}

public String getStudentPhno() {
	return studentPhno;
}

public void setStudentPhno(String studentPhno) {
	this.studentPhno = studentPhno;
}

public String getMode() {
	return mode;
}

public void setMode(String mode) {
	this.mode = mode;
}

public String getCourse() {
	return course;
}

public void setCourse(String course) {
	this.course = course;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
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

public Counsellor getCounsellor() {
	return counsellor;
}

public void setCounsellor(Counsellor counsellor) {
	this.counsellor = counsellor;
}


}

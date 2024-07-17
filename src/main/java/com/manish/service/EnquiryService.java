package com.manish.service;

import java.util.List;

import com.manish.dto.Dashboard;
import com.manish.entity.Enquiry;

public interface EnquiryService {
	
	public Dashboard getDashboardInfo(Integer counsellorId);
	
	public boolean addEnquiry(Enquiry enquiry, Integer counsellorId);
	
	public List<Enquiry> getAllEnquiries(Enquiry enquiry, Integer counsellorId);
	
	public Enquiry getEnquiry(Integer enquiryId);
	

}

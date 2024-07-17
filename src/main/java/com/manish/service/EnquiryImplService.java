package com.manish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.manish.dto.Dashboard;
import com.manish.entity.Counsellor;
import com.manish.entity.Enquiry;
import com.manish.repo.CounsellorRepo;
import com.manish.repo.EnquiryRepo;

@Service
public class EnquiryImplService implements EnquiryService {
	
	@Autowired
	private EnquiryRepo enquiryRepo;

	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public Dashboard getDashboardInfo(Integer counsellorId) {
	
		Long totalEnquiries = enquiryRepo.getEnquiries(counsellorId);
		Long openEnquiry = enquiryRepo.getEnquiries(counsellorId, "new");
		Long enrolledEnquiry = enquiryRepo.getEnquiries(counsellorId, "enrolled");
		Long lostEnquiry = enquiryRepo.getEnquiries(counsellorId, "lost");
		
		Dashboard d = new Dashboard();
		d.setTotalEnquiry(totalEnquiries);;
		d.setEnrolledEnquiry(enrolledEnquiry);
		d.setOpenEnquiry(openEnquiry);
		d.setLostEnquiry(lostEnquiry);
		
		return d;
	}

	@Override
	public boolean addEnquiry(Enquiry enquiry,Integer counsellorId) {
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		enquiry.setCounsellor(counsellor);
		Enquiry savedEnquiry = enquiryRepo.save(enquiry);
		return savedEnquiry.getEnqId() !=null;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Enquiry enquiry, Integer counsellorId) {
	
//		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
//		enquiry.setCounsellor(counsellor);
		Counsellor counsellor = new Counsellor();
		counsellor.setCounsellotId(counsellorId);
		
		// adding filter values to entity
		Enquiry searchCriteria = new Enquiry();
		searchCriteria.setCounsellor(counsellor);
		
		if(null!=enquiry.getCourse()&& !"".equals(enquiry.getCourse())) {
			searchCriteria.setCourse(enquiry.getCourse());
		}
		
		if(null!=enquiry.getMode()&& !"".equals(enquiry.getMode())) {
			searchCriteria.setMode(enquiry.getMode());
		}
		
		if(null!=enquiry.getStatus()&& !"".equals(enquiry.getStatus())) {
			searchCriteria.setStatus(enquiry.getStatus());
		}
		
		Example<Enquiry> of = Example.of(searchCriteria);
		return  enquiryRepo.findAll(of);
	}

	@Override
	public Enquiry getEnquiry(Integer enquiryId) {
		return enquiryRepo.findById(enquiryId).orElseThrow();
	
	}

}

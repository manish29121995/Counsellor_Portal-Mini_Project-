package com.manish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manish.entity.Enquiry;
import com.manish.service.CounsellorService;
import com.manish.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private CounsellorService counsellorService;
	
	@Autowired
	private EnquiryService enquiryService;
	
	// add enquiry page
	@GetMapping("/enquiry")
	public String addEnquiry(Model model) {
		model.addAttribute("enquiry", new Enquiry());
		return "addEnquiry";
		
	}
	
	// save Enquiry
	@PostMapping("/enquiry")
	public String saveEnquiry(Enquiry enquiry , Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer cid = (Integer)session.getAttribute("cid");
		
		boolean status = enquiryService.addEnquiry(enquiry, cid);
		if(status) {
			model.addAttribute("success", "Enquiry Saved");
		}else {
			model.addAttribute("errorMsg", "Failed to save");
		}
		model.addAttribute("enquiry", new Enquiry());
		  return "addEnquiry";
	}

	// view Enquiries
	@GetMapping("/enquiuries")
    public String getEnquiry(HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession(false);
    	Integer cid = (Integer) session.getAttribute("cid");
    	
    	List<Enquiry> list = enquiryService.getAllEnquiries(new Enquiry(), cid);
    	model.addAttribute("enqs", list);
    	model.addAttribute("enq", new Enquiry());
    	return "viewEnquiries";
    }

	// filter Enquiries
	
	@PostMapping("filter-enqs")
	public String filterEnquiries(@ModelAttribute("enq") Enquiry enquiry , HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
    	Integer cid = (Integer) session.getAttribute("cid");
	
    	List<Enquiry> list = enquiryService.getAllEnquiries(enquiry, cid);
    	model.addAttribute("enqs", list);
    	return "viewEnquiries";
	}
	
	@GetMapping("/edit")
	public String editEnquiry(@RequestParam("enqId") Integer enquiryId, Model model) {
		
		System.out.println(enquiryId);
		Enquiry enquiry = enquiryService.getEnquiry(enquiryId);
		model.addAttribute("enquiry", enquiry);
		return "addEnquiry";
	}
	

}

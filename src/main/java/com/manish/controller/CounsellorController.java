package com.manish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.manish.dto.Dashboard;
import com.manish.entity.Counsellor;
import com.manish.service.CounsellorService;
import com.manish.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private CounsellorService counsellorService;
	
	@Autowired
	private EnquiryService enquiryService;
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "index";
		
	}
	
	@PostMapping("/login")
	public String handleLogin(Counsellor counsellor ,HttpServletRequest request, Model model ) {
		Counsellor c = counsellorService.getCounsellor(counsellor.getEmail(), counsellor.getPwd());
		if(c==null) {
			model.addAttribute("errorMsg", "Invalid Credentials");
			return "index";
		}else {
		 
			HttpSession session = request.getSession(true);
			session.setAttribute("cid", c.getCounsellotId());
			
			Dashboard dashboardInfo = enquiryService.getDashboardInfo(c.getCounsellotId());
			model.addAttribute("dashboard", dashboardInfo);
			return "dashboard";
		}
	}
	
	@GetMapping("/dashboard")
	public String getDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");
		
		Dashboard dashboardInfo = enquiryService.getDashboardInfo(cid);
		model.addAttribute("dashboard",dashboardInfo);
		return "dashboard";
		
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		
		return "registerView";
	}
	
	
	@PostMapping("/register")
	public String handleRegister(Counsellor counsellor, Model model) {
		boolean saveCounsellor = counsellorService.saveCounsellor(counsellor);
		if(saveCounsellor) {
			model.addAttribute("successMsg", "Counsellor saved");
		}else {
			model.addAttribute("errrorMsg", "Counsellor failed to saved");
		}
		model.addAttribute("counsellor", new Counsellor());
		  return "registerView";
		  
	}
	
	@GetMapping("/logout")
	    public String logout(HttpServletRequest request , Model model) {
	    	HttpSession session = request.getSession(false);
	    	session.invalidate();
	    	return "redirect:/";
	    	
	    }
	
	
}

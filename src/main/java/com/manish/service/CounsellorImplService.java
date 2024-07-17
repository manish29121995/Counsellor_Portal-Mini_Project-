package com.manish.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manish.entity.Counsellor;
import com.manish.repo.CounsellorRepo;

@Service
public class CounsellorImplService implements CounsellorService {
	
	@Autowired
	private CounsellorRepo counsellorRepo;

	@Override
	public boolean saveCounsellor(Counsellor counsellor) {
		Counsellor counsellorData = counsellorRepo.findByEmail(counsellor.getEmail());
		if(counsellorData !=null) {
			return false;
		}else {
			Counsellor savedCounsellor = counsellorRepo.save(counsellor);	
			return savedCounsellor.getCounsellotId()!=null;	
		}
		 
	}

	@Override
	public Counsellor getCounsellor(String email, String pwd) {
		return counsellorRepo.findByEmailAndPwd(email, pwd);
		
	}

}

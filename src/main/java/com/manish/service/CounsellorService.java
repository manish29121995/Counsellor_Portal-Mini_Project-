package com.manish.service;

import com.manish.entity.Counsellor;

public interface CounsellorService {
	
	public boolean saveCounsellor(Counsellor counsellor);
	
	public Counsellor getCounsellor(String email , String pwd);

}

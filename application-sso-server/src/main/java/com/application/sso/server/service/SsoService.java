package com.application.sso.server.service;

import com.application.sso.server.model.User;

public interface SsoService {
	
	String login(String account, String password);
	
	void logout(String userId);
	
	void resetPassword(String userId, String oldPassword, String newPassword);
	
	void updatePassword(String userId, String password);
	
	void register(String phone, String password, String verificationCode);
	
	User getUser(String jwt);
	
	String getTokenName();
	
}

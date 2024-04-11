package edu.kh.project.email.model.service;


public interface EmailService {

	/** 이메일 보내기
	 * @param string
	 * @param email
	 * @return authkey
	 */
	String sendEmail(String string, String email);

}

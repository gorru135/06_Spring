package edu.kh.project.member.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dto.Member;


public interface MemberService {

	/** 로그인 서비스 
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember);

	/** 이메일 중복검사 서비스 
	 * @param memberEmail
	 * @return
	 */
	int checkEmail(String memberEmail);

	int checkNickname(String memberNickname);

	/** 회원가입 서비스 
	 * @param inputMember
	 * @param memberAddress
	 * @return result
	 */
	int signup(Member inputMember, String[] memberAddress);

	/** 빠른 로그인 
	 * @param memberEmail
	 * @return loginMember
	 */
	Member quickLogin(String memberEmail);

	List<Member> selectMemberList();

	/** 비밀번호 초기화
	 * @param inputNo
	 * @return
	 */
	int resetPw(int inputNo);

	int restorationMember(int inputNo);

	


}

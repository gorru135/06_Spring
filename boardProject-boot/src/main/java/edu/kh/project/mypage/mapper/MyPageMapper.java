package edu.kh.project.mypage.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper // interface에서만 적용 됨! 
public interface MyPageMapper {

	/** 회원 정보 수정 SQL
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	/** 회원 비밀번호 조회 SQL
	 * @param memberNo
	 * @return 암호화된 비밀번호
	 */
	String selectPw(int memberNo);

	/** 회원 비밀번호 수정 SQL
	 * @param paramMap
	 * @return paramMap
	 */
	int changePw(Map<String, Object> paramMap);

	int secession(int memberNo);

}

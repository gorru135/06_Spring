package edu.kh.project.mypage.service;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.mypage.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// rollbackFro 작성 안할시 런타임 입센션까지만 잡아줌!
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
@Service
public class MyPageServiceImpl implements MyPageService{

	private final MyPageMapper mapper;

	// bcypt 암호화 객체 의존성 주입(SecurityConfig 참조)
	private final BCryptPasswordEncoder bcypt;
	// 회원 정보 수정 
	@Override 
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		// 입력된 주소가 있을 경우 
		// memberAddress를 A^^^B^^^C 형태로 가공 


		// 주소 입력 x -> inputMember.getMemberAddress() -> ",,"
		if(inputMember.getMemberAddress().equals(",,")) {
			
			// 주소에 null 대입 
			inputMember.setMemberAddress(null);
		} else {
			// memberAddress를 A^^^B^^^C 형태로 가공 
			String address = String.join("^^^", memberAddress);
			
			// 주소에 가공된 데이터를 대입 
			inputMember.setMemberAddress(address);
		}
		
		
		return mapper.updateInfo(inputMember);
	}

	// 비밀번호 수정 
	@Override
	public int changePw(Map<String, Object> paramMap, int memberNo) {
		
		// 현재 로그인한 회원의 암호화된 비밀번호를 DB에서 조회 
		String orginPw = mapper.selectPw(memberNo);
		
		// 입력받은 현재 비밀번호와(평문) 
		// DB에서 조회한 비밀번호 비교(암호화)
		// BCryptPasswordEncoder.matches(평문,암호화된비밀번호)
		
		// 다를 경우 
		if( !bcypt.matches((String)paramMap.get("currentPw"), orginPw)) {
			return 0;
		
		}
		// 같을 경우 
		
		// 새 비밀번호를 암호화 진행 
		String encPw = bcypt.encode((String)paramMap.get("newPw"));
		
		paramMap.put("encPw", encPw);
		paramMap.put("memberNo", memberNo);
		
		return mapper.changePw(paramMap);
	}

	// 회원 탈퇴 
	@Override
	public int secession(int memberNo, String memberPw) {
		
		// 현재 로그인한 회원의 암호화된 비밀번호를 DB에서 조회 
		String orginPw = mapper.selectPw(memberNo);
		// 다를 경우 
		if( !bcypt.matches(memberPw, orginPw)) {
			return 0;
		
		}

		
		
		return mapper.secession(memberNo);
	}
}

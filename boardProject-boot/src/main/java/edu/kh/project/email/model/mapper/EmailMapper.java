package edu.kh.project.email.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {

	/** 
	 * @param map
	 * @return result
	 */
	int updateAuthKey(Map<String, String> map);

	/**
	 * @param map
	 * @return result
	 */
	int insertAuthKey(Map<String, String> map);

	/** 
	 * @param map
	 * @return count
	 */
	int checkAuthKey(Map<String, Object> map);


}

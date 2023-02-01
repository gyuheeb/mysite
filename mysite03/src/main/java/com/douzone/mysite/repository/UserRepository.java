package com.douzone.mysite.repository;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	//INSERT
	public void insert(UserVo vo) {
		 sqlSession.insert("user.insert",vo);
	}

	//LOGIN
	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String,Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}
	
	// 회원정보 수정 FINDBYNO
	public UserVo findByNo(Long no) {
		return	sqlSession.selectOne("user.findByNo",no);	
	}

	// 회원정보 수정 Update
	public void update(UserVo vo) {
		sqlSession.update("user.update",vo);
	
	}
}
	


package com.douzone.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// FINDALL
	public List<GuestbookVo> findAll() {
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
		return list;
	}
	
	//INSERT
	public void insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert",vo);
	}
	
	//DELETE
	public int deleteByGuest(Long no,String password) {
		
		Map<String, Object> map = Map.of("no",no,"password",password);
		 return sqlSession.delete("guestbook.deleteByGuest",map);
		
	}
	

	
}
	


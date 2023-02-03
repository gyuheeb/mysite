package com.douzone.mysite.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// FINDALL
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}
	// FINDNO
	public BoardVo getContents(Long no) {
		return sqlSession.selectOne("board.getContents", no);
	}
	// INSERT
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert",vo);
		
	}
	//UPDATE
	public void upadateContents(BoardVo vo) {
		sqlSession.update("board.upadateContents",vo);
	}

	// SEARCH
	public List<BoardVo> Search(String keyword) {
		List<BoardVo> search = new ArrayList<>();
		return search;
	}

	//getTotalCount
	public int getTotalCount(String keyword) {
        return sqlSession.selectOne("board.getTotalCount",keyword);
	}
        
	// DELETE
	public void deleteContents(Long no) {
		sqlSession.delete("board.deleteContents",no);
		
	}
	// INSERT
	public void addContents(BoardVo vo) {
		sqlSession.insert("board.addContents",vo);
		
	}
	
	// FINDMAX
	public Long findMax() {
		return sqlSession.selectOne("board.findMax");
		
	}
	// updateCount
	public void updateCount(Long g_no,Long o_no) {
		Map<String,Object> map = new HashMap<>();
		map.put("g_no", g_no);
		map.put("o_no", o_no);
		
		List<BoardVo> list = sqlSession.selectList("board.selectupdateCount", map);
		for(BoardVo vo:list) {
			vo.setO_no(vo.getO_no()+1);
			sqlSession.selectOne("board.updateCount", map);
		}
		
		
	}
	
	//TotalCount
	public int TotalCount() {	
	return sqlSession.selectOne("board.TotalCount");
	
	}
	
	// VIEWCOUNT
	public void viewCount(Long no) {
		sqlSession.selectList("board.viewCount", no);
	}
	
		public List<BoardVo> findAllByPageAndKeyword(int page, String keyword, int size) {
			Map<String, Object> map = new HashMap<>();
			map.put("startOffset", (page-1)*size);
			map.put("size", size);
			map.put("keyword", keyword);
			
			return sqlSession.selectList("board.findAllByPageAndKeyword", map);
		}
}
	
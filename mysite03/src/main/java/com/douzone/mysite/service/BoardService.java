package com.douzone.mysite.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;




@Service
public class BoardService {
	private static final int LIST_SIZE= 5; //리스팅되는 게시물의 수
	private static final int PAGE_SIZE= 5; //페이지 수
	
	@Autowired
	private BoardRepository boardrepository;
	
	public List<BoardVo> list() {
		return boardrepository.findAll();
	}
	
	public Map<String, Object> getContentsList(int page, String keyword) {
		int TotalCount = boardrepository.getTotalCount(page,keyword);
		Map<String,Object> map =new HashMap<>();
		return map;

	}
	
//	public void addContents(BoardVo vo) {
//		return boardrepository.getContents(vo);
//	}
	public BoardVo getContents(Long no) {
		return boardrepository.getContents(no);
	}
//	public BoardVo getContents(Long no,Long userNo) {
//		
//		return null;
//	}
//	public void upadateContents(BoardVo vo) {
//		
//	}
//	public void deleteContents(Long no,Long userNo) {
//		
//	}
	
//
//		
//		//1. view에서 게시판 리스트를 랜더링 하기 위한 데이터 값 계산
//		int beginPage = 0;
//		int prevPage = 0;
//		int nextPage = 0;
//		int endPage = 0;
//	
//		//2.리스트 가져오기
//		List<BoardVo> list =  boardrepository.findAllByPageAndKeyWord(page, keyword);
//		
//		
//		//3.리스트 정보를 맵에 저장
//		Map<String,Object> map =new HashMap<>();
//		map.put("list", list);
//		map.put("beginPage", beginPage);
//		map.put("prevPage", prevPage);
//		map.put("endPage", endPage);
//		
//		
//		return map;
//	}


	
	

}

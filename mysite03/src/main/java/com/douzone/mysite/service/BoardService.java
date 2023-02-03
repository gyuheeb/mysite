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
	//LIST
	public List<BoardVo> list() {
		return boardrepository.findAll();
	}
	//VIEW
	public BoardVo getContents(Long no) {
		return boardrepository.getContents(no);
	}
	//UPDATE
	public void upadateContents(BoardVo vo) {
		boardrepository.upadateContents(vo);
	}
	//DETETE
	public void deleteContents(Long no) {
		boardrepository.deleteContents(no);
	}
	
	public void addContents(BoardVo vo) {
	
		boardrepository.addContents(vo);
	}
	
	public Long findMax() {
		return  boardrepository.findMax();
	}
	public void updateCount(Long g_no,Long o_no) {
		 boardrepository.updateCount(g_no,o_no);
	}
	
	public void viewCount(Long no) {
		 boardrepository.viewCount(no);
	}
	

	//PAGELIST
	public Map<String, Object> getContentsList(int page, String keyword) {
			Map<String,Object> map =new HashMap<>();

			int TotalCount = boardrepository.getTotalCount(keyword);
			
			int block = page/LIST_SIZE +1;
			int totalPage =TotalCount%LIST_SIZE == 0 ? TotalCount/block : (int)(TotalCount/block)+1;
			int endPage = block*LIST_SIZE > TotalCount ? -1 : block*LIST_SIZE ;  
			int startPage =(block*LIST_SIZE)-4;  
			int prevPage = page <1? -1: page-1;	
			int nextPage = page > totalPage ?-1: page+1;
							
			
			//끝페이지
			
			//2.리스트 가져오기
			List<BoardVo> list = boardrepository.findAllByPageAndKeyword(page, keyword, LIST_SIZE);
			
			//3.리스트 정보를 맵에 저장
		
			map.put("list", list);
			map.put("keyword", keyword);
			map.put("block", block);
			map.put("totalPage", totalPage);
			map.put("endPage", endPage);
			map.put("startPage", startPage);
			map.put("prevPage", prevPage);
			map.put("nextPage", nextPage);
			
			
			return map;
		}
	
	
	

}

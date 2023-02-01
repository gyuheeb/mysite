package com.douzone.mysite.repository;

import java.util.ArrayList;
import java.util.List;
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
	
	//SEARCH
	public List<BoardVo> Search(String keyword) {
		List<BoardVo> search = new ArrayList<>();
		return search;
	}
	
//	// INSERT
//	public void insert(BoardVo vo) {
//		sqlSession.insert("board.insert",vo);
//		
//	}
//	
//	
////	public List<BoardVo> findAllByPageAndKeyWord(int page, String keyword, int size) {
////		Map<String,Object> map = new HashMap<>();
////		map.put("startOffset", (page-1)*size);
////		map.put("size", size);
////		map.put("keyword", keyword);
////		
////		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
////	
////	
////	}
	public int getTotalCount(int page, String keyword) {
		return sqlSession.selectOne("board.getTotalCount",keyword);
		 
	}
	// FINDNO
	public BoardVo findNo(Long no) {
		return sqlSession.selectOne("no",no);
	}
//
//	// FINDMAXNO
//	public Long findMax() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Long result=0L;
//		
//		try {
//			conn = getConnection();
//
//			String sql = "select ifnull(max(g_no),0) from board";
//			pstmt = conn.prepareStatement(sql);
//
//			
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//
//				result=(rs.getLong(1));
//				
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//
//	}
//
//	// UPDATE
//	public void update(BoardVo vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = "update board set title= ?, contents= ? where no =?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setLong(3, vo.getNo());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	// DELETE
//	public void delete(Long no) {
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = "delete from board where no = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setLong(1, no);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	// VIEWCOUNT
//	public void viewCount(Long no) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		BoardVo vo = new BoardVo();
//
//		try {
//			conn = getConnection();
//
//			String sql = "update board set hit = hit+1 where no = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setLong(1, no);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//	//UPDATECOUNT
//	public void updateCount(Long g_no,Long o_no) {
//
//		List<BoardVo> result = new ArrayList<>();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = "select * from board  where g_no =? and o_no > ? ";
//			pstmt = conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BoardVo vo = new BoardVo();
//				vo.setNo(rs.getLong(1));
//				vo.setTitle(rs.getString(2));
//				vo.setContents(rs.getString(3));
//				vo.setHit(rs.getLong(4));
//				vo.setReg_date(rs.getString(5));
//				vo.setG_no(rs.getLong(6));
//				vo.setO_no(rs.getLong(7));
//				vo.setDepth(rs.getLong(8));
//				vo.setUser_no(rs.getLong(9));
//	
//				result.add(vo);
//			}
//			
//			for(BoardVo aa:result) {
//				String sql1 = "update board set o_no =? where no=?";
//				pstmt = conn.prepareStatement(sql1);
//				pstmt.setLong(1, aa.getO_no());
//				pstmt.setLong(2, aa.getNo());
//				
//				pstmt.executeUpdate();
//			}
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		
//	}
//	
//	//SEARCH
//	public List<BoardVo> Search(String kwd) {
//		List<BoardVo> search = new ArrayList<>();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		 String sql ="select * from board where title";
//	      try {
//	    	  	conn = getConnection();
//	            if(kwd != null && !kwd.equals("") ){
//	                sql +=" LIKE '%"+ kwd.trim() +"%' order by g_no desc,o_no asc";
//	            }
//
//
//
//		
//			pstmt = conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BoardVo vo = new BoardVo();
//				vo.setNo(rs.getLong(1));
//				vo.setTitle(rs.getString(2));
//				vo.setContents(rs.getString(3));
//				vo.setHit(rs.getLong(4));
//				vo.setReg_date(rs.getString(5));
//				vo.setG_no(rs.getLong(6));
//				vo.setO_no(rs.getLong(7));
//				vo.setDepth(rs.getLong(8));
//				vo.setUser_no(rs.getLong(9));
//	
//				search.add(vo);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return search;
//	
//		
//	}
	
//----------------------------------------------------------------------------
	
	


}
package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAllByPageAndKeyWord(int page, String keyword, int size) {
		Map<String,Object> map = new HashMap<>();
		map.put("startOffset", (page-1)*size);
		map.put("size", size);
		map.put("keyword", keyword);
		
		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
	
	
	}
	public int getTotalCount(String keyword) {
		sqlSession.selectOne("board.getTotalCount",keyword);
	}

	// FINDALL
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,b.name from board a,user b where a.user_no=b.no order by g_no desc,o_no asc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setReg_date(rs.getString(5));
				vo.setG_no(rs.getLong(6));
				vo.setO_no(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUser_no(rs.getLong(9));
				vo.setName(rs.getString(10));
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// INSERT
	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board(no,title,contents,reg_date,g_no,o_no,depth,user_no) values(null, ?, ?, now(),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getG_no());
			pstmt.setLong(4, vo.getO_no());
			pstmt.setLong(5, vo.getDepth());
			pstmt.setLong(6, vo.getUser_no());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// FINDNO
	public BoardVo findNo(Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = new BoardVo();
		try {
			conn = getConnection();

			String sql = "select * from board where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setReg_date(rs.getString(5));
				vo.setG_no(rs.getLong(6));
				vo.setO_no(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUser_no(rs.getLong(9));

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	// FINDMAXNO
	public Long findMax() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long result=0L;
		
		try {
			conn = getConnection();

			String sql = "select ifnull(max(g_no),0) from board";
			pstmt = conn.prepareStatement(sql);

			
			rs = pstmt.executeQuery();
			while (rs.next()) {

				result=(rs.getLong(1));
				
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	// UPDATE
	public void update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set title= ?, contents= ? where no =?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// DELETE
	public void delete(Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// VIEWCOUNT
	public void viewCount(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		BoardVo vo = new BoardVo();

		try {
			conn = getConnection();

			String sql = "update board set hit = hit+1 where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	//UPDATECOUNT
	public void updateCount(Long g_no,Long o_no) {

		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select * from board  where g_no =? and o_no > ? ";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setReg_date(rs.getString(5));
				vo.setG_no(rs.getLong(6));
				vo.setO_no(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUser_no(rs.getLong(9));
	
				result.add(vo);
			}
			
			for(BoardVo aa:result) {
				String sql1 = "update board set o_no =? where no=?";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setLong(1, aa.getO_no());
				pstmt.setLong(2, aa.getNo());
				
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}
	
	//SEARCH
	public List<BoardVo> Search(String kwd) {
		List<BoardVo> search = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		 String sql ="select * from board where title";
	      try {
	    	  	conn = getConnection();
	            if(kwd != null && !kwd.equals("") ){
	                sql +=" LIKE '%"+ kwd.trim() +"%' order by g_no desc,o_no asc";
	            }



		
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setReg_date(rs.getString(5));
				vo.setG_no(rs.getLong(6));
				vo.setO_no(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUser_no(rs.getLong(9));
	
				search.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return search;
	
		
	}
	
//----------------------------------------------------------------------------
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.10.118:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
	


}
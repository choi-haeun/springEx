package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtill;

@Repository("boardDAO")
public class BoardDAO {
	private Connection conn= null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String BOARD_INSERT = "INSERT INTO BOARD(SEQ,TITLE,WRITER,CONTENT)"
			+ " VALUES((SELECT NVL(MAX(SEQ),0)+1 FROM BOARD),?,?,?)";
	private final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?,CONTENT=? WEHRE SEQ=?";
	private final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?";
	private final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";
	private final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY SEQ DESC";
	
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("JDBC로 insertBoard() 기능 처리");
		
		try {
		conn = JDBCUtill.getConnection();
		stmt = conn.prepareStatement(BOARD_INSERT);
		stmt.setString(1, vo.getTitle());
		stmt.setString(2, vo.getWriter());
		stmt.setString(3, vo.getContent());
		stmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(stmt, conn);
		}
	}
	
	//글 수정	
	public void updateBoard(BoardVO vo) {
		System.out.println("=====>JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(stmt, conn);
		}
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("=====>JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(stmt, conn);
		}
	}
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("=====>JDBC로 getBoard() 기능 처리");
		BoardVO board = null;
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			while(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(stmt, conn);
		}
		return board;
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("====>JDBC로 getBoardList()기능 처리");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(stmt, conn);
		}
		return boardList;
	}
									
}
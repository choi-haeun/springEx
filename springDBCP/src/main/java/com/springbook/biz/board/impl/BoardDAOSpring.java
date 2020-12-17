package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOSpring{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//mysql 명령어
	//private final String BOARD_INSERT = "INSERT INTO board(seq,title,writer,content,regdate)"
	//							+"VALUES((SELECT IFNULL(mas(seq),0)+1 FORM board),?,?,?,NOW());
	//sql명령어
	private final String BOARD_INSERT = "INSERT INTO board(seq,title,writer,content) VALUES "
			+ "((SELECT NVL(max(seq),0)+1 FROM board),?,?,?)";
	private final String BOARD_UPDATE = "UPDATE board SET title=?,content=? WHERE seq=?";
	private final String BOARD_DELETE = "DELETE board WHERE seq=?";
	private final String BOARD_GET = "SELECT * FROM board WHERE seq=?";
	private final String BOARD_LIST = "SELECT * FROM board ORDER BY seq DESC";
	
//	@Autowired
//	public void setSuperDataSource(DataSource dataSource) {
//		super.setDataSource(dataSource);
//	}
	
	//CRUD method
	//글등록
	public void insertBoard(BoardVO vo) {
		System.out.println("======>Spring JDBC insertBoard() 기능처리");
		//getJdbcTemplate().update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());
		jdbcTemplate.update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());
	}
	
	//글수정
	public void updateBoard(BoardVO vo) {
		System.out.println("=======>Spring JDBC updateBoard() 기능처리");
		//getJdbcTemplate().update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
		jdbcTemplate.update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
	}
	
	//글삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("======>Spring JDBC deleteBoard() 기능처리");
		//getJdbcTemplate().update(BOARD_DELETE,vo.getSeq());
		jdbcTemplate.update(BOARD_DELETE,vo.getSeq());
	}
	
	//글 상세조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("======>Spring JDBC getBoard()");
		Object[] args = {vo.getSeq()};
		//return getJdbcTemplate().queryForObject(BOARD_GET, args,new BoardRowMapper());
		return jdbcTemplate.queryForObject(BOARD_GET, args,new BoardRowMapper());
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("======>Spring JDBC getBoardList()");
		//return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
}

class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		
		return board;
	}
	
}

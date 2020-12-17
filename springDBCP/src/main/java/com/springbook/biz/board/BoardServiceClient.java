package com.springbook.biz.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {

	public static void main(String[] args) {
		//1.Spring 컨테이너를 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2.Spring 컨테이너로부터 BoardServiceImpl 객체를 Lookup
		BoardService boardService = (BoardService)container.getBean("boardService");
		
		//3.글등록 기능 테스트
		BoardVO vo = new BoardVO();
//		vo.setTitle("임시제목");
//		vo.setWriter("최하은");
//		vo.setContent("임시내용 .....7");
//		boardService.insertBoard(vo);
		
		//글 수정
//		BoardVO vo = new BoardVO();
//		vo.setTitle("update 테스트");
//		vo.setContent("글 수정 기능 테스트");
//		vo.setSeq(3);
//		boardService.updateBoard(vo);
		
		//글 삭제
//		BoardVO vo = new BoardVO();
//		vo.setSeq(2);
//		boardService.deleteBoard(vo);
		
		
		//4.글 목록 검색 기능 테스트
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for(BoardVO board:boardList) {
			System.out.println("----->"+board.toString());
		}
		
		
		
		
		//Spring 컨테이너 종료
		container.close();
	}

}

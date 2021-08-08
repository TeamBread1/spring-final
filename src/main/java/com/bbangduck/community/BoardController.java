package com.bbangduck.community;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
public class BoardController {
	
private BoardRepository repo;
	
	@Autowired
	public BoardController(BoardRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value="/board")
	public List<Board> getBoardList(){
		return repo.findAll(Sort.by("id").descending());
	}
	


	@PostMapping(value = "/board")
	public Board addBoard(@RequestBody Board board, HttpServletResponse res) {
		
		
		if(board.getTitle()  == null || board.getTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		
		
		if(board.getContent() == null || board.getContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		
			return repo.save(board);
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/board/{id}")
	public Board getBoard(@PathVariable Long id, HttpServletResponse res) {
		Optional<Board> board =repo.findById(id);
		
		if(board.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return board.get();
		
	}
	

	@DeleteMapping(value ="/board/{id}")
	public boolean removeContact(@PathVariable Long id, HttpServletResponse res) {
		
		Optional<Board> board = repo.findById(id);
		
		if(board.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		
		repo.deleteById(id);
		return true;
	}
	

	@PutMapping(value="/board/{id}")
	public Board modifyBoard(@PathVariable Long id, @RequestBody Board board, HttpServletResponse res) {
		Optional<Board> findedBoard = repo.findById(id);
		
		if (findedBoard.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		Board toUpdateBoard = findedBoard.get();
		toUpdateBoard.setTitle(board.getTitle());
		toUpdateBoard.setContent(board.getContent());

		
		return repo.save(toUpdateBoard);
		
	}

}
package com.bbangduck.community;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

	private BoardRepository repo;

	@Autowired
	public BoardController(BoardRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/board")
	public List<BoardWithoutComment> getBoardList() {

		return repo.findAllOnlyBoard();
	}

	@PostMapping(value = "/board")
	public Board addBoard(@RequestBody Board board, HttpServletResponse res) {

		if (board.getPostTitle() == null || board.getPostTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		if (board.getPostContent() == null || board.getPostContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		if (board.getPostTitle() == null || board.getPostTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (board.getPostPwd() == null || board.getPostPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (board.getPostAuthor() == null || board.getPostAuthor().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time = new Date();
		String time1 = format.format(time);

		board.setPostDate(time1);
		board.setPostLike(0);
		board.setPostImage(board.getPostImage());

		return repo.save(board);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/board/{id}")
	public BoardWithoutComment getBoard(@PathVariable Long id, HttpServletResponse res) {

		Optional<BoardWithoutComment> board = repo.findByIdOnlyBoard(id);
		if (board.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return board.get();

	}

	@DeleteMapping(value = "/board/{id}")
	public boolean removeContact(@PathVariable Long id, HttpServletResponse res) {

		Optional<BoardWithoutComment> board = repo.findByIdOnlyBoard(id);

		if (board.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		repo.deleteById(id);
		return true;
	}

	@PutMapping(value = "/board/{id}")
	public Board modifyBoard(@PathVariable Long id, @RequestBody Board board, HttpServletResponse res) {
		Optional<BoardWithoutComment> findedBoard = repo.findByIdOnlyBoard(id);

		if (findedBoard.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Board toUpdateBoard = (Board) findedBoard.get();
		toUpdateBoard.setPostTitle(board.getPostTitle());
		toUpdateBoard.setPostContent(board.getPostContent());
		toUpdateBoard.setPostPwd(board.getPostPwd());
		toUpdateBoard.setPostAuthor(board.getPostAuthor());
		toUpdateBoard.setPostImage(board.getPostImage());
		toUpdateBoard.setPostLike(board.getPostLike() + 1);

		return repo.save(toUpdateBoard);

	}

}
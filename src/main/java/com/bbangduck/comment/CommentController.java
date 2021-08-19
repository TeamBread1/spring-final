package com.bbangduck.comment;

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
import org.springframework.web.bind.annotation.RestController;

import com.bbangduck.community.Board;
import com.bbangduck.community.BoardRepository;

@RestController
public class CommentController {

	private CommentRepository repo;
	private BoardRepository boardRepo;

	@Autowired
	public CommentController(CommentRepository repo, BoardRepository boardRepo) {
		this.repo = repo;
		this.boardRepo = boardRepo;
	}

	@GetMapping(value = "/comment")
	public List<Comment> getCommentList() {

		return repo.findAllWithFetchJoin();
	}

	@PostMapping(value = "/comment/{postNo}")
	public Comment addComment(@PathVariable int postNo, @RequestBody Comment comment, HttpServletResponse res) {

		if (comment.getContent() == null || comment.getContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (comment.getPwd() == null || comment.getPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Board board = boardRepo.findById((long) postNo).orElseThrow(IllegalArgumentException::new);
		comment.setBoard(board);
		return repo.save(comment);
	}

	@PutMapping(value = "/comment/{commentNo}")
	public Comment modifyComment(@PathVariable int commentNo, @RequestBody Comment comment, HttpServletResponse res) {

		Optional<Comment> findedComment = repo.findByIdWithFetchJoin(commentNo);
		if (findedComment.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		Comment toUpdateComment = findedComment.get();

		toUpdateComment.setContent(comment.getContent());
		return repo.save(toUpdateComment);
	}

	@DeleteMapping(value = "/comment/{commentNo}")
	public boolean deleteComment(@PathVariable int commentNo) {

		repo.deleteById(commentNo);
		return true;
	}
}

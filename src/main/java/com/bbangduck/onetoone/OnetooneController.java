package com.bbangduck.onetoone;
import java.text.SimpleDateFormat;
import java.util.Date;
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


@RestController
public class OnetooneController {
	
	private OnetooneRepository repo;
	private AnswerRepository answerRepo;
	
	@Autowired
	public OnetooneController(OnetooneRepository repo, AnswerRepository answerRepo) {
		this.repo = repo;
		this.answerRepo = answerRepo;
	}
	
	@GetMapping(value="/onetoone")
	public List<Onetoone> getOnetooneList(){
		return repo.findAll(Sort.by("id").descending());
	}
	

	@GetMapping(value = "/onetoone/paging")
	public Page<Onetoone> getContactListPaging(@RequestParam int page, @RequestParam int size){
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}



	@PostMapping(value = "/onetoone")
	public Onetoone addOnetoone(@RequestBody Onetoone onetoone, HttpServletResponse res) {
		
		
		if(onetoone.getQnaTitle()  == null || onetoone.getQnaTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		if(onetoone.getQnaAuthor() == null || onetoone.getQnaAuthor().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(onetoone.getQnaPwd() == null || onetoone.getQnaPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		if(onetoone.getQnaContent() == null || onetoone.getQnaContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		

		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm");
		Date time = new Date();
		String time1 = format.format(time);
		
		
		onetoone.setQnaDate(time1);
		onetoone.setQnaState("답변대기중");
		onetoone.setQnaPic(onetoone.getQnaPic());
		onetoone.setAnswerPwd("4321");

		
		return repo.save(onetoone);
		
		
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/onetoone/{id}")
	public Onetoone getOnetoone(@PathVariable int id, HttpServletResponse res) {
		Optional<Onetoone> onetoone =repo.findById(id);
		
		if(onetoone.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return onetoone.get();
		
	}
	

	@DeleteMapping(value ="/onetoone/{id}")
	public boolean removeContact(@PathVariable int id, HttpServletResponse res) {
		
		Optional<Onetoone> onetoone = repo.findById(id);
		
		if(onetoone.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		
		repo.deleteById(id);
		return true;
	}
	

	@PutMapping(value="/onetoone/{id}")
	public Onetoone modifyOnetoone(@PathVariable int id, @RequestBody Onetoone onetoone, HttpServletResponse res) {
		Optional<Onetoone> findedOnetoone = repo.findById(id);
		
		if (findedOnetoone.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		if(onetoone.getQnaTitle()  == null || onetoone.getQnaTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		if(onetoone.getQnaAuthor() == null || onetoone.getQnaAuthor().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(onetoone.getQnaPwd() == null || onetoone.getQnaPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}

		if(onetoone.getQnaPwd() == null || onetoone.getQnaPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		
		Onetoone toUpdateOnetoone = findedOnetoone.get();
		toUpdateOnetoone.setQnaTitle(onetoone.getQnaTitle());
		toUpdateOnetoone.setQnaAuthor(onetoone.getQnaAuthor());
		toUpdateOnetoone.setQnaPwd(onetoone.getQnaPwd());
		toUpdateOnetoone.setQnaContent(onetoone.getQnaContent());
		toUpdateOnetoone.setQnaPic(onetoone.getQnaPic());

		
		return repo.save(toUpdateOnetoone);
		
	}
	
	@PostMapping(value = "/onetoone/{boardId}/answer")
	public Answer addAnswer(@PathVariable int boardId, @RequestBody Answer answer, HttpServletResponse res) {
		
		
		if(answer.getAnswerContent() == null || answer.getAnswerContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		if(answer.getAnswerTitle() == null || answer.getAnswerTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		

		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm");
		Date time = new Date();
		String time1 = format.format(time);
				
		Onetoone onetoone = repo.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 쓰기 실패: 게시글 번호를 찾을 수 없음");
		});
		
		answer.setOnetoone(onetoone);		
		answer.setAnswerDate(time1);
		answer.setAnswerPic(answer.getAnswerPic());
		onetoone.setQnaState("답변완료");
		
		return answerRepo.save(answer);
		
		
	}

}
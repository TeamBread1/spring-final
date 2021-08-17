package com.bbangduck.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbangduck.community.BoardRepository;

@RestController
public class HomeController {

	private BoardRepository repo;

	@Autowired
	public HomeController(BoardRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/home")
	public List<Best6Post> getBest6Data() {

		return repo.findBest6Post();
	}

}

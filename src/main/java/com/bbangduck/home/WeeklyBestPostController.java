package com.bbangduck.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeeklyBestPostController {

	private WeeklyBestPostRepository repo;

	@Autowired
	WeeklyBestPostController(WeeklyBestPostRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/home")
	public List<Post> getWeeklyBestData() {

//		return repo.getPostData();
//		return repo.findAll(Sort.by("postLike").descending());
		return repo.findWeeklyBestData();
	}
}

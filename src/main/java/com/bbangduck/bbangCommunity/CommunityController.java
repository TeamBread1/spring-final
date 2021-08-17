package com.bbangduck.bbangCommunity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
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
public class CommunityController {
	
	private CommunityRepository repo;
	
	@Autowired
	public CommunityController(CommunityRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value="/community")
	public List<Community> getCommunityList(){
		return repo.findAll(Sort.by("id").descending());
	}
	


	@PostMapping(value = "/community")
	public Community addCommunity(@RequestBody Community community, HttpServletResponse res) {
		
		
		if(community.getPostTitle()  == null || community.getPostTitle().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		
		
		if(community.getPostContent() == null || community.getPostContent().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		
		if(community.getPostPwd() == null || community.getPostPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		
		if(community.getPostAuthor() == null || community.getPostAuthor().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm");
		Date time = new Date();
		String time1 = format.format(time);
		
		community.setPostDate(time1);
		community.setPostLike(0);
		community.setPostPic(community.getPostPic());

		
		return repo.save(community);
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/community/{id}")
	public Community getCommunity(@PathVariable Long id, HttpServletResponse res) {
		Optional<Community> community =repo.findById(id);
		
		if(community.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return community.get();
		
	}
	

	@DeleteMapping(value ="/community/{id}")
	public boolean removeCommunity(@PathVariable Long id, HttpServletResponse res) {
		
		Optional<Community> community = repo.findById(id);
		
		if(community.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		
		repo.deleteById(id);
		return true;
	}
	

	@PutMapping(value="/community/{id}")
	public Community modifyCommunity(@PathVariable Long id, @RequestBody Community community, HttpServletResponse res) {
		Optional<Community> findedCommunity = repo.findById(id);
		
		if (findedCommunity.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		if(community.getPostPwd() == null || community.getPostPwd().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
			
		}
		
		Community toUpdateCommunity = findedCommunity.get();
		toUpdateCommunity.setPostTitle(community.getPostTitle());
		toUpdateCommunity.setPostContent(community.getPostContent());
		toUpdateCommunity.setPostPwd(community.getPostPwd());
		toUpdateCommunity.setPostAuthor(community.getPostAuthor());
		toUpdateCommunity.setPostPic(community.getPostPic());

		
		return repo.save(toUpdateCommunity);
		
	}


}

package com.bbangduck.heart;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;




@RestController
@RequiredArgsConstructor
public class HeartController {

	private final HeartService heartService;
	
	@GetMapping(value ="/board/{id}/heart")
	public HashMap<String, Object> ReadHeart(@PathVariable Long id) {
		return heartService.ReadHeart(id);
	}
	
	@PostMapping(value ="/board/{id}/heart")
	public ResponseEntity CreateHeart(@PathVariable Long id) {
		 Heart heart = heartService.CreateHeart(id);
		if (heart == null) {
			 Message message = Message.builder()
	                    .message1("게시물이 존재하지 않습니다.")
	                    .build();
	            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return ResponseEntity.ok().build();
	}
	
	 @DeleteMapping(value ="/board/{id}/heart")
	    public ResponseEntity DeleteHeart(@PathVariable Long id){
	        Heart heart = heartService.DeleteHeart(id);
	        if (heart == null){
	            Message message = Message.builder()
	                    .message1("게시물이 존재하지 않습니다.")
	                    .build();
	            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return ResponseEntity.ok().build();
	    }
}



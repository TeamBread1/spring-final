package com.bbangduck.heart;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbangduck.community.Board;
import com.bbangduck.community.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class HeartService {
	private final HeartRepository heartRepository;
	private final BoardRepository boardRepository;
	
	public HashMap<String, Object> ReadHeart(Long id) {
		Heart heart = heartRepository.findByBoardId(id);
//		List<Heart> heartCount = heartRepository.findByBoardId(id);
		HashMap<String, Object> map = new HashMap<>();
//		Integer Count = heartCount.size();
		if(heart == null) {
			map.put("check", false);
//            map.put("heartCount", Count);
            return map;
		}
			map.put("check", true);
//	        map.put("heartCount", Count);
	        return map;
	}
	
	@Transactional
    public Heart CreateHeart(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBoardId(id);
        if (heart == null) {
        	Heart newHeart = new Heart();
        	newHeart.setHeart(true);
        	board.addHeart(newHeart);
        	heartRepository.save(newHeart);
        	return newHeart;
        }
        
        return null;

}
	  public Heart DeleteHeart(Long id){
	        Board board = boardRepository.findById(id).orElseThrow(
	                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
	        );
	       
	        Heart heart = heartRepository.findByBoardId(id);
	        if (heart != null){
	            board.deleteHeart(heart);
	            heartRepository.deleteById(heart.getId());
	            return heart;
	        }
	        return null;
	    }
	
}
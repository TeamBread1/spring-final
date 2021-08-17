package com.bbangduck.heart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface HeartRepository extends JpaRepository<Heart, Long> {


	  Heart findByBoardId(Long id);   
//    List<Heart> findByBoardId(Long id);
}
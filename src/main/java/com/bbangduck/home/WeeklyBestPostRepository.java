package com.bbangduck.home;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyBestPostRepository extends JpaRepository<Post, Integer> {

	@Query(value = "SELECT * FROM post order by post_like desc limit 6", nativeQuery = true)
	List<Post> findWeeklyBestData();
}

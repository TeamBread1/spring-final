package com.bbangduck.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query(value = "SELECT no, content, pwd, post_no as postNo FROM comment order by no desc", nativeQuery = true)
	List<CommentWithPostNo> findAllWithPostNo();
}

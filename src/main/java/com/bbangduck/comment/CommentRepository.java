package com.bbangduck.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query("select c from Comment c left join fetch c.board where c.no =:no")
	Optional<Comment> findByIdWithFetchJoin(@Param("no") int no);

	@Query("select c from Comment c join fetch c.board")
	List<Comment> findAllWithFetchJoin();
}

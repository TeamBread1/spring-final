package com.bbangduck.community;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bbangduck.home.Best6Post;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query(value = "SELECT id, post_title as postTitle, post_content as postContent, post_date as postDate, post_author as postAuthor, post_image as postImage, post_like as postLike, post_pwd as postPwd FROM board order by id desc", nativeQuery = true)
	List<BoardWithoutComment> findAllOnlyBoard();

	@Query(value = "SELECT id, post_title as postTitle, post_content as postContent, post_date as postDate, post_author as postAuthor, post_image as postImage, post_like as postLike, post_pwd as postPwd FROM board WHERE id = :id", nativeQuery = true)
	Optional<BoardWithoutComment> findByIdOnlyBoard(@Param("id") Long id);
//	@Query(value = "SELECT b FROM board b", nativeQuery = true)
//	List<Board> findAllData();
//	@Query("select b from Board b left join fetch b.comment")
//	List<Board> findAllWithCommentList();

	@Query(value = "SELECT id as id, post_title as postTitle, post_content as postContent, post_image as postImage FROM board order by post_like desc limit 6", nativeQuery = true)
	List<Best6Post> findBest6Post();

//	@Query(value = "SELECT id, post_title as postTitle, post_content as postContent, post_date as postDate, post_author as postAuthor, post_image as postImage, post_like as postLike, post_pwd as postPwd FROM board WHERE ", nativeQuery = true)
}

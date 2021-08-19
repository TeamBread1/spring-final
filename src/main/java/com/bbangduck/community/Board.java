package com.bbangduck.community;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.bbangduck.comment.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String postTitle;

	@Lob
	@Column(length = 100000)
	private String postContent;

	@Column(nullable = false, length = 20)
	private String postDate;

	@Column(nullable = false)
	private String postAuthor;

	@Lob
	private String postImage;

	private int postLike;

	@Column(nullable = false)
	private String postPwd;

	@JsonIgnore
	// OneToMany의 fetch기본값은 lazy
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Comment> comment;

}

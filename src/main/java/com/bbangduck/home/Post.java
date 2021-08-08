package com.bbangduck.home;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postNum;
	private String postTitle;
	private String postAuthor;
	private String postContent;
	@Lob
	@Column(length = 10000)
	private String postImage;
	private String postDate;
	private String postLike;
	private String postReply;
	private String postPwd;

}

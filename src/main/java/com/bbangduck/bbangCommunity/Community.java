package com.bbangduck.bbangCommunity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String postTitle;
	
	@Lob
	@Column(length=100000)
	private String postContent;
	
	@Column(nullable = false)
	private String postPwd;
	
	@Column(nullable = false, length = 20)
	private String postDate;
	
	@Column(nullable = false)
	private String postAuthor;
	
	private int postLike;
	
	@Lob
	@Column(length=100000)
	private String postPic;
}

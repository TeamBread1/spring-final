package com.bbangduck.home;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class WeeklyBestPost {

	// 1. 게시글 번호
	@Id
	private int post_num;
	// 2. 게시글 제목
	private String post_title;
	// 3. 게시글 내용
	private String post_content;
	// 4. 게시글 이미지
	@Lob
	@Column(length = 10000)
	private String post_image;
}

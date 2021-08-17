package com.bbangduck.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bbangduck.community.Board;

import lombok.Data;

@Data
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	private String pwd;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postNo")
	private Board board;
}

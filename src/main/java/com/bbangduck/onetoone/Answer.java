package com.bbangduck.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data @Entity 
public class Answer {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(nullable = false)
	private String answerTitle;
	
	@Column(nullable = false, length = 3000)
	private String answerContent;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="qnaId")
	private Onetoone onetoone;
	
	@Column(nullable = false, length = 20)
	private String answerDate;
	
	@Lob
	@Column(length=100000)
	private String answerPic;
	
	
	
}

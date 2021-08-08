package com.bbangduck.onetoone;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data @Entity 
public class Onetoone {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(nullable = false)
	private String qnaTitle;
	
	@Column(nullable = false)
	private String qnaAuthor;
	
	@Column(nullable = false, length = 3000)
	private String qnaContent;
	
	
	@Column(nullable = false, length = 20)
	private String qnaDate;
	
	@Column(nullable = false)
	private String qnaPwd;
	
	@Column(nullable = false, length = 10)
	private String qnaState;

	
	@Lob
	@Column(length=100000)
	private String qnaPic;
	
	@OneToMany(mappedBy="onetoone",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@OrderBy("id desc")
	@JsonIgnoreProperties({"onetoone"})
	private List<Answer> answer;
	
	private String answerPwd;
	
}
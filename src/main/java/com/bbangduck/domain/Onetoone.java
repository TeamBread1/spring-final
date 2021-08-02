package com.bbangduck.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data // getter/setter
@Entity // ���� ����� ORM�� ��(���̺��� ������)
public class Onetoone {
	
	@Id // PK�� �ش� ������ �ϰڴ�.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // �ش� �����ͺ��̽� ��ȣ ������ ����
	private Long qnaNum;
	
	private String qnaTitle;
	private String qnaAuthor;
	private String qnaContent;
	private Long qnaDate;
	private String qnaPwd;
	private String qnaState;
	

}

package com.bbangduck.contactus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;
	private String job;
	private String loc;
	private String dept;
	private String eAddress;
	private String kakaoId;
	private String faceBookId;
	private String twitterId;
	@Lob
	@Column(length = 10000)
	private String image;
}

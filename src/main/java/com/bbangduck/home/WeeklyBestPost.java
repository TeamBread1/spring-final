package com.bbangduck.home;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class WeeklyBestPost {

	// 1. �Խñ� ��ȣ
	@Id
	private int post_num;
	// 2. �Խñ� ����
	private String post_title;
	// 3. �Խñ� ����
	private String post_content;
	// 4. �Խñ� �̹���
	@Lob
	@Column(length = 10000)
	private String post_image;
}

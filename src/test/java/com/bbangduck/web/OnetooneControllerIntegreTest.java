package com.bbangduck.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.bbangduck.domain.Onetoone;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// ���� �׽�Ʈ
// ��Ʈ�ѷ��� ��ü ������ ���� �͵��� �׽�Ʈ��
// ��� Bean���� �Ȱ��� IoC�� �ø��� �׽�Ʈ �ϴ� ��
// ���� �׽�Ʈ�� �� ����. ������ ���� �׽�Ʈ�� ���ؼ� �� ����

// @SpringBootTest : ��� �ֵ��� �޸𸮿� �� ��
// ������ ȯ�濡�� ������ ȯ��� ������.
// �׷� �� �ϳ�?
// ������ ������ �ʰ� �׽�Ʈ �� �� �ִٴ� ��


// WebEnvironment.MOCK : ���� ������ �ø��°� �ƴ϶� �ٸ� ������ ���� �׽�Ʈ
// WebEnvironment.RANDOM_POR : ���� �������� �׽�Ʈ��.

// @AutoConfigureMockMvc : mockMvc ������ ��������, mockmvc�� �޸𸮿� ����ִ� ����
// @WebMvcTest ������ �Ƚ��൵ �Ǵ� ������ �� ������̼� �ȿ� �̹� AutoConfigureMockMvc�� ���ԵǾ� �ֱ� ����
// @Transactional : ������ �׽�Ʈ�Լ��� ����� ������ Ʈ������� rollback ���ִ� ������̼�

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class OnetooneControllerIntegreTest {
	
	
	// @Autowired : �޸𸮿� ���־�� ��
	// MockMvc : perform�� �Ἥ ��Ʈ�ѷ��� �ּҷ� �׽�Ʈ�غ� �� �ִ� ���̺귯��
	// �� ���̺귯���� ������ 
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void save() {
		log.info("save_�׽�Ʈ ����========================");
	}
	
	@Test
	public void save_�׽�Ʈ() throws Exception {
		// given (�׽�Ʈ�� �ϱ� ���� �غ�)
		// ������Ʈ�� ��Ʈ������ �ٲٴ� �Լ�
		Onetoone one = new Onetoone(null, "����", "�۾���", "content", null, null, null);
		String content = new ObjectMapper().writeValueAsString(new Onetoone(null, "����", "�۾���", "content", null, null, null));
		
		// when (�׽�Ʈ ����)
		ResultActions resultAction = mockMvc.perform(post("/onetoone")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
	
		// then(����)
		resultAction
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.qna_author").value("�۾���"))
			.andDo(MockMvcResultHandlers.print());
	}


}

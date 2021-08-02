package com.bbangduck.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.bbangduck.domain.Onetoone;
import com.bbangduck.service.OnetooneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// ���� �׽�Ʈ
// ��Ʈ�ѷ��� �׽�Ʈ��(��Ʈ�ѷ� ���� ������ ����)
// ���� ���, Controller, Filter, ControllerAdvice ���� �޸𸮿� ���� ��

// @WebMvcTest : �޸𸮿� ���� ��Ʈ�ѷ��� ���� ��ü�鸸 �޸𸮿� ��

// ������ ������ ȯ������ Ȯ���ϰ� �ʹٸ�, 
// @ExtendWith(SpringExtension.class) ��� ������̼��� �ٿ��� ��
// JUnit �׽�Ʈ�� �� ��, � ������ ������ ȯ�濡�� �׽�Ʈ�� �ϰ� ������ ���� ������̼��� �� �ٿ���� ��.
// @WebMvcTest �ȿ� �̹� @ExtendWith(Spring~)�� �پ� ����(��Ʈ�� + ���콺 Ÿ�� ���� ����)

// JUnit4������ ���� @RunWith(SpringRunner.class) ��� ������̼��� ���� �ٿ����� ������,
// JUnit5�� ����� ���� ������ �ʾƵ� ��
// Runwith(~)�� ������ ȯ������ Ȯ���ϴ� ���. 
// (JUnit4������ �� ������̼��� WebMvcTest ������̼� �ȿ� ������ ����)
// ������ JUnit5�� ��� @ExtendWith(SpringExtension.class)�� �ֱ� ������ �Ƚ��൵ �ȴ�...!

@Slf4j
@WebMvcTest
public class OnetooneControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	// @Mock�� ���ָ� ������ ������ ȯ�濡 �޸𸮰� �ȶ�(��Ű�� ȯ�濡�� ��)
	// @MockBean ���
	// IoC ȯ�濡 bean �������
	// mock�� ��¥�� ����� �ø�
	@MockBean
	private OnetooneService oneService;

	// BDDMockito ���� given, when, then
	@Test
	public void save_�׽�Ʈ() throws Exception {
		// given (�׽�Ʈ�� �ϱ� ���� �غ�)
		// ������Ʈ�� ��Ʈ������ �ٲٴ� �Լ�
		Onetoone one = new Onetoone(null, "����", "�۾���", "content", null, null, null);
		String content = new ObjectMapper().writeValueAsString(new Onetoone(null, "����", "�۾���", "content", null, null, null));
		when(oneService.save(one)).thenReturn(new Onetoone(null, "����", "�۾���", null, null, null, null));
		
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

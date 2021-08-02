package com.bbangduck.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bbangduck.domain.OnetooneRepository;

// ���� �׽�Ʈ (service�� ���õ� �ֵ鸶 IoC�� �����(�޸�)
// ���񽺿� ���õ� ��ü�� �������͸� �ۿ� ���µ�
// �װ� ��¥ ��ü�� ���� �� �ִµ� �̰� �������ִ� �� Mockito ȯ��

@ExtendWith(MockitoExtension.class)
public class OnetooneServiceUnitTest {

	// @Mock
	// �޸𸮿� ��(������ IoC�� �ƴ� ��Ű���� �޸� ������ ��)
	// ��Ű�� ȯ���� �������͸��� �ȵ�� �ֱ� ������ ���� ��������� ��
	
	// @InjectMocks : ���� ��ü�� ������� ��, @Mock�� ��ϵ� ��� �ֵ��� ���Թ���
	@InjectMocks
	private OnetooneService oneService;
	
	@Mock
	private OnetooneRepository oneRepo;
	
	
}

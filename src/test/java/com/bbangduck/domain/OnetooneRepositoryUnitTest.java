package com.bbangduck.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

// ���� �׽�Ʈ(DB ���õ� Bean�� IoC�� ��ϵǸ� ��)
// Replace.ANY -> ����� ��¥ DB�� �׽�Ʈ(���� DB�� �ƴ�)
// Replace.NONE -> ���� DB�� �׽�Ʈ
// DataJpaTest : �������͸����� �� IoC�� ����ص� -> @Mock�� ���� �ʾƵ� �� ��� @Autowired ��

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DataJpaTest
public class OnetooneRepositoryUnitTest {

	@Autowired
	private OnetooneRepository oneRepo;
}

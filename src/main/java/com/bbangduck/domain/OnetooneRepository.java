package com.bbangduck.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository�� ����� ���� ������ IoC�� ������ ����� �Ǵµ�
// JpaRepository�� extends�� �ϸ� ���� ������
// JpaRepository�� CRUD �Լ��� ��� ���� 
public interface OnetooneRepository extends JpaRepository<Onetoone, Long>{

}

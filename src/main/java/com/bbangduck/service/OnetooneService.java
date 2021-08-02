package com.bbangduck.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbangduck.domain.Onetoone;
import com.bbangduck.domain.OnetooneRepository;

import lombok.RequiredArgsConstructor;

// ����� ������ �� �ְ� Ʈ����� ������ �� �ִ� Service
// �������͸��� �����ͺ��̽��� �����͸� insert�ϰų� �������ų� �ϴ°�
// ���񽺴� �����͸� �������� �ٸ������� ��������, 
// �������� �����ͺ��̽��� ������ �����ϸ鼭 � ����� �����°� ����
// �װ� ��ü�� �ѹ��Ų�ٴ��� �ٽ� Ŀ�Խ�Ű�� ��
// ���� ���, �۱��̶�� �Լ��� ������
// �������丮�� �������� �Լ��� �����ϰ� �װ� �� �� ���ư��� Ŀ���ϰ�
// �ȵǸ� �ٽ� �ѹ��ϴ� ��

@RequiredArgsConstructor
@Service
public class OnetooneService {
	
	private final OnetooneRepository oneRepo;
	
	// Ʈ����� ����
	@Transactional // ���� �Լ��� ����� �� ������ ����Ǵ� ���еǸ� �ѹ�, �����ϸ� Ŀ�Ե�
	public Onetoone save(Onetoone one) {
		return oneRepo.save(one);
	}
	
	
	// JPA�� ���氨����� ���� ����� ����
	// � ��ƼƼ�� ����� �� JPA���� ��� ���Ѻ��� ����
	// readonly�� �ɷ������� �� ����� ��Ȱ��ȭ �Ǿ ���� ������ �ٿ���
	// update�� ���ռ��� ��������, insert�� ���ɵ��������� ������(��������)
	// �� update �Ǵ� ������ �ݿ����� �ʰ� ������(insert�� �ݿ���)
	@Transactional(readOnly =true) 
	public Onetoone select(Long id) {
		return oneRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("id�� Ȯ�����ּ���!"));
	}
	
	@Transactional(readOnly =true) 
	public List<Onetoone> selectAll(){
		return oneRepo.findAll();
	}
	
	@Transactional
	public Onetoone modify(Long id, Onetoone one) {
		// ��Ƽ üŷ update
		// ���� �����ͺ��̽��� ���� ��ƼƼ�� �������� �� ���� ����ȭ�� �� 
		// ������ ���� �޸� ������ ���� �ΰ� �ִ� ���� ����.
		// ����ȭ�� �����͸� set���ִ� ������ �ٲ�ġ�� �� �� ����.
		// ����ȭ�� ���� ���Ӽ� ���ؽ�Ʈ�� ������
		
		Onetoone oneEntity = oneRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("id�� Ȯ�����ּ���!")); 
		oneEntity.setQnaContent(one.getQnaContent());
		oneEntity.setQnaTitle(one.getQnaTitle());
		oneEntity.setQnaState(one.getQnaState());
		
		return oneEntity;
	}
	
	// �Լ� ���� -> Ʈ����� ���� -> ����ȭ �Ǿ� �ִ� �����͸� DB�� ���Ž�Ŵ(Flush) => �̰� Ŀ�Ե� ---> �̷� ������ ��Ƽüŷ�̶��� ��
	
	
	@Transactional
	public String delete(Long id) {
		oneRepo.deleteById(id); 
		return "ok";
	}
	
	

}

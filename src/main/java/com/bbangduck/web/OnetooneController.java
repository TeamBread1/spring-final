package com.bbangduck.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbangduck.domain.Onetoone;
import com.bbangduck.service.OnetooneService;

import lombok.RequiredArgsConstructor;


// �������� ����� �޸𸮿� �ø� ��(IoC �����̳ʿ� �ø� ��)
// �����ڸ� ���ؼ� �÷��ִµ� 
// final�� �ɸ� @RequiredArgsConstructor�� �ڵ����� �����ڸ� �������
@RequiredArgsConstructor
@RestController
public class OnetooneController {
	
	private final OnetooneService oneService;
	
	// <?> : return �� �� � ��� int�� ���� �ְ� string�� ���� �ִµ�
	// ?�� ������ ��� Ÿ���� �� return �� �� ����(�ڹ� ���׸�)
	
	// ResponseEntity : return�� �� HttphStatus�� ���� ������

	
	// ��� �������� (selectAll)
	@GetMapping("/onetoone")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(oneService.selectAll(), HttpStatus.OK);
	}
	
	// �����ϱ�
	// @RequestBody : ������ ���� �� json���� �ް�����
	@PostMapping("/onetoone")
	public ResponseEntity<?> save(@RequestBody Onetoone one){
		return new ResponseEntity<>(oneService.save(one), HttpStatus.OK);
	}
	 

	// �Ѱ� �������� (select)
	// @RequestBody : ������ ���� �� json���� �ް�����
	@GetMapping("/onetoone/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(oneService.select(id), HttpStatus.OK);
	}
	
	// �����ϱ� (update)
	@PutMapping("/onetoone/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Onetoone one){
		return new ResponseEntity<>(oneService.modify(id, one), HttpStatus.OK);
	}
	
	// �����ϱ�(delete)
	@DeleteMapping("/onetoone/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		return new ResponseEntity<>(oneService.delete(id), HttpStatus.OK);
	}
}

package com.bbangduck.community;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;


import com.bbangduck.heart.Heart;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String postTitle;
	
	@Lob
	@Column(length=100000)
	private String postContent;
	
	@Column(nullable = false)
	private String postPwd;
	
	@Column(nullable = false, length = 20)
	private String postDate;
	
	@Column(nullable = false)
	private String postAuthor;
	

	
	@JsonIgnore
    @OneToMany
    @Builder.Default
    private Set<Heart> hearts = new HashSet<>();
	
	  public void addHeart(Heart heart){
	        this.hearts.add(heart);
	        heart.setBoard(this);
	    }

	    public void deleteHeart(Heart heart){
	        this.hearts.remove(heart);
	        heart.setBoard(null);
	    }
	
}

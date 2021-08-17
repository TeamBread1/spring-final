package com.bbangduck.heart;

import lombok.*;


import com.bbangduck.community.Board;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isHeart;

    @ManyToOne
    private Board board;
    
    public Heart(Board board) {
    	this.board = board;
    }


}
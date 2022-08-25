package com.project.bootboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardDate;
}

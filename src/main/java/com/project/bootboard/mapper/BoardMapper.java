package com.project.bootboard.mapper;

import com.project.bootboard.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto> getBoardList(@Param("beginRow") int beginRow,
                                @Param("rowPerPage") int rowPerPage);

    int getBoardTotal();

    int boardInsert(@Param("boardDto") BoardDto boardDto);

    BoardDto boardDetail(@Param("boardNo") int boardNo);

    int showUp(@Param("boardNo") int boardNo);

    int boardUpdate(@Param("boardDto") BoardDto boardDto);
}

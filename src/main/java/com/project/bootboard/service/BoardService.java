package com.project.bootboard.service;

import com.project.bootboard.dto.BoardDto;
import com.project.bootboard.dto.PageNationDto;
import com.project.bootboard.mapper.BoardMapper;
import com.project.bootboard.util.PageNationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardService {

    private final BoardMapper boardMapper;

    public Map<String, Object> getBoardList(String current) {
        // 보드 총갯수
        int total = boardMapper.getBoardTotal();
        // 만들어논 메서드
        PageNationDto pageNation = PageNationUtil.getPageNation(current, total, "/board/list", 10);
        // 보드리스트 가져오기
        List<BoardDto> boardList = boardMapper.getBoardList(pageNation.getBeginRow(), pageNation.getRowPerPage());
        // 담을통
        Map<String, Object> map = new HashMap<>();
        map.put("pageNation", pageNation);
        map.put("boardList", boardList);
        log.info(boardList);
        return map;
    }

    public int boardInsert(BoardDto boardDto) {
        return boardMapper.boardInsert(boardDto);
    }

    @Transactional // 트랜잭션 편리하게 해줌 롤백처리
    public BoardDto boardDetail(int boardNo) {
        // 조회수오르고
        boardMapper.showUp(boardNo);
        // 보드상세 갖다주고
        return boardMapper.boardDetail(boardNo);
    }

    public int boardUpdate(BoardDto boardDto) {
        return boardMapper.boardUpdate(boardDto);
    }
}

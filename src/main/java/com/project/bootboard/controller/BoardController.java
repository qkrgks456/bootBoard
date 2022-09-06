package com.project.bootboard.controller;

import com.project.bootboard.dto.BoardDto;
import com.project.bootboard.dto.MemberDto;
import com.project.bootboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor // 사실 생성자로 받아야 한다 대신 해준다
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    // 주소는 대시케이스로 하자
    @GetMapping("/list") // 파라미터 받는법
    public String boardList(@RequestParam(required = false, value = "current") String current,
                            @ModelAttribute("check") String check,
                            Authentication authentication,
                            Model model) {
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        Map<String, Object> map = boardService.getBoardList(current);
        map.forEach((key, value) -> model.addAttribute(key, value));
        // 파일명
        return "/board/board-list";
    }

    @PostMapping("/insert")// 통으로 받기
    public String boardInsert(BoardDto boardDto) {
        log.info("%s", boardDto);
        boardService.boardInsert(boardDto);

        //리스트로 뿌려줘야 하니까
        return "redirect:/board/list?check=insert";
    }

    // 게시판 상세
    @GetMapping("/detail")
    public String boardDetail(@RequestParam(value = "boardNo") int boardNo, Model model) {
        BoardDto boardDto = boardService.boardDetail(boardNo);
        model.addAttribute("board", boardDto);
        return "/board/board-detail";
    }

    @PostMapping("/update")
    public String boardUpdate(BoardDto boardDto) {
        boardService.boardUpdate(boardDto);

        return "/board/board-detail";
    }

    @PostMapping
    public String boardDelete() {
        return "redirect:/board/list?check=insert";
    }


    // 이동 API

    // 입력폼
    @GetMapping("/insert-form")
    public String insertForm() {
        return "/board/board-insert-form";
    }


    //수정폼
    @PostMapping("/update-form")
    public String updateForm() {
        return "/board/board-detail";
    }
}

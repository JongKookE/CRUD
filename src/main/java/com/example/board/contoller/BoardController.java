package com.example.board.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String showMain(){                
        return "boardwrite";
    }

    @RequestMapping("/write/{num}")
    public String showMain(@PathVariable int num, Model model){
        int res = num;
        model.addAttribute("str", "num : " + res);
        return "boardwrite";
    }

    @PostMapping("/board/writeProcessing")
    public String BoardWriteProcessing(Board board, Model model){
        model.addAttribute("takeThis", board.getTitle());
        System.out.println("제목 : " + board.getTitle());
        System.out.println("내용 : " + board.getContent());
        boardService.write(board);
        return "takethis";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.BoardList());
        return "boardlist";
    }
    
    @GetMapping("/test")
    public String testThymeleaf(Model model){
        model.addAttribute("test", "I'm JongKook");
        return "test";
    }


}

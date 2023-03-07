package com.example.board.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String goToMain(){
        return "boardmain";
    }
    @GetMapping("/write")
    public String showMain(Board board, Model model){
        model.addAttribute("board", new Board());
        return "boardwrite";
    }

    // @PostMapping("/board/writeProcessing")
    // public String checkBoard(@Valid Board board, BindingResult bindingResult) {

	// 	if (bindingResult.hasErrors()) {
	// 		return "boardwrite";
	// 	}
	// 	return "redirect:/write";
	    
    // }

    @PostMapping("/board/writeProcessing")
    public String BoardWriteProcessing(@Valid Board board, BindingResult bindingResult, Model model, MultipartFile file) throws Exception{
        System.out.println("제목 : " + board.getTitle());
        System.out.println("내용 : " + board.getContent());
        if (bindingResult.hasErrors()) 
			return "boardwrite";
		
        boardService.write(board, file);
        model.addAttribute("message", "글 작성이 완료 되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, 
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, 
                            @RequestParam(name = "searchKeyword", required = false) String searchKeyword){
        Page<Board> list = null;
        if(searchKeyword == null){
            list = boardService.BoardList(pageable);
        }else{
            list = boardService.boardSearchList(searchKeyword, pageable);
        }
                
        // Pageable 인터페이스는 0부터 시작하기 때문에 첫번째 페이지를 1로 만들어줘야한다.
         // page.getPageNumber(); 동일
        int nowPage = list.getPageable().getPageNumber() + 1;  
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        //검색이 안되었던 이유 -> searchKeyword 값에 따라 list가 변하는데 밑의 라인에서 list를 받아오지 않고
        //BoardService.BoardList를 받아오니까 검색이 안되고 첫번째 페이지로 넘어갔었음
        model.addAttribute("list", boardService.BoardList(pageable));        
        model.addAttribute("list", list);        
        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }
 
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception{
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp, file);
        return "redirect:/board/list";
    }
}

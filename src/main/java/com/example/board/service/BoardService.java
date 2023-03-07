package com.example.board.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board, MultipartFile file) throws Exception{
        if (file != null) {
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; 
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" +file.getOriginalFilename();
            File saveFile = new File(path, fileName);
            file.transferTo(saveFile);
            board.setFilename(fileName);
            board.setFilepath("/files/" + fileName);
        }
        else{
            board.setFilename(null);
            board.setFilepath(null);
        }
        boardRepository.save(board);
    }

    public Page<Board> BoardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    // 특정 게시글 불러오기
    @Transactional
    public Board boardView(Integer id){
        //HttpServletRequest request
        //Cookie[] cookie = request.getCookies();
        Board board = boardRepository.findById(id).get();
        board.setViewCount(board.getViewCount() + 1);
        return board;
    }

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}

package com.example.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board, MultipartFile file) throws Exception{
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; 
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" +file.getOriginalFilename();
        File saveFile = new File(path, fileName);
        file.transferTo(saveFile);
        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);
        boardRepository.save(board);
    }

    public List<Board> BoardList(){
        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }
}

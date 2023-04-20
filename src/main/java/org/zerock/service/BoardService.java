package org.zerock.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.entity.Board;
import org.zerock.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
    private BoardRepository boardRepository;

    //글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{
    	
    	if(!file.isEmpty()) {
	        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";
	        UUID uuid = UUID.randomUUID();
	        String fileName = uuid + "_"+file.getOriginalFilename();
	        File saveFile = new File(projectPath, fileName);
	        file.transferTo(saveFile);
	        board.setFilename(fileName);
	        board.setFilepath("/files/"+fileName);
    	}
    	
        boardRepository.save(board);
    }

    //게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }
    
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
    	
    	return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }
    
    //조회수
    public int updateView(Integer id) {
    	return boardRepository.updateView(id);
    }
}

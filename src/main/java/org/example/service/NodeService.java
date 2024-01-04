package org.example.service;

import org.example.repo.NoteRepository;
import org.example.dto.NoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.logging.Logger;

@Service
public class NodeService {
    Logger logger = Logger.getLogger(String.valueOf(NodeService.class));

    @Autowired
    NoteRepository noteRepository;

    public ResponseEntity<List<NoteDetail>> findALlDetails () {
        try {
            List<NoteDetail> noteDetailList = noteRepository.findAll();
            return new ResponseEntity<>(noteDetailList, HttpStatus.CREATED);
        }catch (Exception e){
            logger.info("Error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<NoteDetail> findDetailsById (Integer id) {
        try {
            NoteDetail nd =  noteRepository.findByNoteDetailId(id);
            if(nd == null){
                return new ResponseEntity<>(nd, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(nd, HttpStatus.CREATED);
        }catch (Exception e){
            logger.info("Error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<NoteDetail> createNote(String note) {
        NoteDetail noteDetail = new NoteDetail();
        noteDetail.setNote(note);
        try {
            noteDetail = noteRepository.save(noteDetail);
            logger.info("noteDetail " + noteDetail.toString());
            return new ResponseEntity<>(noteDetail, HttpStatus.CREATED);
        }catch (Exception  e){
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<NoteDetail> updateNote(Integer id , String note) {
        try {
            NoteDetail nd =  noteRepository.findByNoteDetailId(id);
            if(nd == null){
                return new ResponseEntity<>(nd, HttpStatus.BAD_REQUEST);
            }
            nd.setNote(note);
            nd = noteRepository.save(nd);
            return new ResponseEntity<>(nd, HttpStatus.CREATED);
        }catch (Exception e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteNote(Integer id) {
        try {
            NoteDetail nd =  noteRepository.findByNoteDetailId(id);
            if(nd == null){
                return new ResponseEntity<>("ID not present", HttpStatus.BAD_REQUEST);
            }
            noteRepository.delete(nd);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.CREATED);
        }catch (Exception e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> shareNote(Integer from , Integer to) {
        NoteDetail fromNote =  noteRepository.findByNoteDetailId(from);
        NoteDetail toNote =  noteRepository.findByNoteDetailId(to);
        if(fromNote == null || toNote == null) {
            return new ResponseEntity<>("One of the ID not present", HttpStatus.BAD_REQUEST);
        }
        //need some clarity
        return new ResponseEntity<>("Sharing "+fromNote.getNote() +" Quote to " + toNote.getNote(), HttpStatus.CREATED);

    }

    public ResponseEntity<List<NoteDetail>> getByNotes (String note) {
        try {
            List<NoteDetail> noteDetailList = noteRepository.getNotesByText(note);
            return new ResponseEntity<>(noteDetailList, HttpStatus.CREATED);
        }catch (Exception e){
            logger.info("Error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

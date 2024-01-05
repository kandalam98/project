package org.example.controller;

import org.example.dto.NoteDetail;
import org.example.service.NodeService;
import org.example.service.TokenBucketConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NodeService nodeService;
    @Autowired
    TokenBucketConsumer tokenBucketConsumer;

    @GetMapping("/api/notes")
    public ResponseEntity<List<NoteDetail>> getAllNotes() {
        if(tokenBucketConsumer.isAllow()) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return nodeService.findALlDetails();
    }

    @GetMapping("/api/notes/{id}")
    public ResponseEntity<NoteDetail> getAllNotes(@PathVariable("id") Integer id) {
        if(tokenBucketConsumer.isAllow()) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return nodeService.findDetailsById(id);
    }

    @PostMapping("/api/{notes}")
    public ResponseEntity<NoteDetail> createNewNote (@PathVariable("notes") String notes) {
        if(tokenBucketConsumer.isAllow()) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return nodeService.createNote(notes);
    }

    @PutMapping("api/id/{id}/notes/{notes}")
    public ResponseEntity<NoteDetail> updateNote(@PathVariable("id") Integer id , @PathVariable("notes") String notes) {
        if(tokenBucketConsumer.isAllow()) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return nodeService.updateNote(id,notes);
    }
    @DeleteMapping("/api/note/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable("id") Integer id) {
        if(tokenBucketConsumer.isAllow()) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return nodeService.deleteNote(id);
    }

    @PostMapping("/api/notes/{id}/share/{id2}")
    public ResponseEntity<String> createNewNote (@PathVariable("id") Integer fromId , @PathVariable("id2") Integer toId  ) {
        if(tokenBucketConsumer.isAllow()) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return nodeService.shareNote(fromId, toId);
    }

    @GetMapping("/api/find/note/{note}")
    public ResponseEntity<List<NoteDetail>> getByNotes(@PathVariable("note") String notes) {
        if(tokenBucketConsumer.isAllow()) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return nodeService.getByNotes(notes);
    }

}

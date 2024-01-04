package org.example.controller;

import org.example.dto.NoteDetail;
import org.example.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.Base64;
import java.util.List;

@RestController
public class NodeController {

    @Autowired
    NodeService nodeService;


    @GetMapping("/api/notes")
    public ResponseEntity<List<NoteDetail>> getAllNotes() {
        return nodeService.findALlDetails();
    }

    @GetMapping("/api/notes/{id}")
    public ResponseEntity<NoteDetail> getAllNotes(@PathVariable("id") Integer id) {
        return nodeService.findDetailsById(id);
    }

    @PostMapping("/api/{notes}")
    public ResponseEntity<NoteDetail> createNewNote (@PathVariable("notes") String notes) {
        return nodeService.createNote(notes);
    }

    @PutMapping("api/id/{id}/notes/{notes}")
    public ResponseEntity<NoteDetail> updateNote(@PathVariable("id") Integer id , @PathVariable("notes") String notes) {
        return nodeService.updateNote(id,notes);
    }
    @DeleteMapping("/api/note/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable("id") Integer id) {
        return nodeService.deleteNote(id);
    }

    @PostMapping("/api/notes/{id}/share/{id2}")
    public ResponseEntity<String> createNewNote (@PathVariable("id") Integer fromId , @PathVariable("id2") Integer toId  ) {
        return nodeService.shareNote(fromId, toId);
    }

    @GetMapping("/api/find/note/{note}")
    public ResponseEntity<List<NoteDetail>> getByNotes(@PathVariable("note") String notes) {
        return nodeService.getByNotes(notes);
    }

    @GetMapping("/greeting")
    public String greeting(Authentication authentication) {

        String userName = authentication.getCredentials().toString();
        String encoding = Base64.getEncoder().encodeToString((userName).getBytes());
        String authHeader = "Basic " + encoding;

        return "Spring Security In-memory Authentication Example - Welcome " + userName;
    }





}

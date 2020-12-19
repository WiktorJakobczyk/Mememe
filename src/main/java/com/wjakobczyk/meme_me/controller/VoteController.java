package com.wjakobczyk.meme_me.controller;


import com.wjakobczyk.meme_me.dto.VoteDto;
import com.wjakobczyk.meme_me.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vote")
@AllArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/{id}")
    public ResponseEntity<?> vote(@PathVariable Long id, @RequestBody VoteDto voteDto){
        voteService.vote(id, voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

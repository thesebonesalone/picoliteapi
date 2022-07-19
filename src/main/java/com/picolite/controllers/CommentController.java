package com.picolite.controllers;

import com.picolite.models.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @CrossOrigin
    @PostMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> postComment(@RequestParam("username") String username, @RequestParam("content") String content)
    {
        Comment c = new Comment();
        c.setContent(content);
        c.setUsername(username);
        System.out.println(username + " : " + content);
        //this is where the comment would be saved
        return new ResponseEntity<>(new Comment(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> getComment()
    {
        Comment comment = new Comment();
        comment.setUsername("Test Username");
        comment.setContent("Test Comment");

        return new ResponseEntity<>(comment,HttpStatus.OK);
    }
}

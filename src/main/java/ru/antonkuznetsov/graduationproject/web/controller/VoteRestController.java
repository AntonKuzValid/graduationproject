package ru.antonkuznetsov.graduationproject.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.antonkuznetsov.graduationproject.AuthorizedUser;
import ru.antonkuznetsov.graduationproject.model.Vote;
import ru.antonkuznetsov.graduationproject.service.interfaces.VoteService;

import java.util.List;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/votes";

    @Autowired
    VoteService voteService;

    @GetMapping()
    public List<Vote> getAll() {
        return voteService.getAll();
    }

    @GetMapping(value = "/vote/{menuId}")
    public ResponseEntity<Vote> vote(@PathVariable("menuId") int menuId) {
        Vote vote = voteService.vote(menuId, AuthorizedUser.id());
        if (vote != null) {
            log.info("User with id {} voted successfully, vote with id {} was saved", AuthorizedUser.id(), vote.getId());
            return new ResponseEntity<Vote>(vote, HttpStatus.OK);
        }
        log.info("User with id {} can't vote", AuthorizedUser.id());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}

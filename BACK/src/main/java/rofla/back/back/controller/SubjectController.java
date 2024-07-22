package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.service.SubjectService;
import rofla.back.back.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Subject")
public class SubjectController {
    private final SubjectService subjectService;
    private final UserService userService;
    @PostMapping("/{username}")
    public ResponseEntity<?> sendAllSubjectTime(@PathVariable("username") String username){
        try{
            return ResponseEntity.ok(subjectService.sendAllSubjectTime(username));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

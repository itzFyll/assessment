package org.aodocs.aodocassessmentapp.logic.controller;

import jakarta.validation.Valid;
import org.aodocs.aodocassessmentapp.logic.service.JwtService;
import org.aodocs.aodocassessmentapp.logic.service.TestSheetSequenceService;
import org.aodocs.aodocassessmentapp.model.dto.EmailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
@Validated
public class SheetsController {
    private final Logger _logger = LoggerFactory.getLogger(SheetsController.class);

    private final TestSheetSequenceService _testSheetSequenceService;
    private final JwtService _jwtService;

    @Autowired
    public SheetsController(TestSheetSequenceService testSheetSequenceService,
                            JwtService jwtService) {
        _testSheetSequenceService = testSheetSequenceService;
        _jwtService = jwtService;
    }

    @PostMapping("/sheets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity launchTestSheetsSequence(
            @RequestHeader(name="Authorization", required = false) String token,
            @Valid @RequestBody(required = false) EmailDto email) throws IOException {
        // Really basic auth, should use SecurityFilterChain
        if(!_jwtService.verifyToken(token)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        _testSheetSequenceService.testSequence(email);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

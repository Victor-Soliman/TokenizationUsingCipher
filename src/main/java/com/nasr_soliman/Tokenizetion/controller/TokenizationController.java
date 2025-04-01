package com.nasr_soliman.Tokenizetion.controller;


import com.nasr_soliman.Tokenizetion.entity.TokenizationRequest;
import com.nasr_soliman.Tokenizetion.service.TokenizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokenization")
public class TokenizationController {

    @Autowired
    private TokenizationService tokenizationService;

    @PostMapping("/tokenize")
    public ResponseEntity<String> tokenize(@RequestBody TokenizationRequest request) {
        try {
            String token = tokenizationService.tokenize(request.getCardNumber());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred during tokenization: " + e.getMessage());
        }
    }

    @PostMapping("/detokenize")
    public ResponseEntity<String> detokenize(@RequestBody String token) {
        try {
            String originalData = tokenizationService.detokenize(token);
            return originalData != null ? ResponseEntity.ok(originalData) : ResponseEntity.status(404).body("Token not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred during detokenization: " + e.getMessage());
        }
    }
}
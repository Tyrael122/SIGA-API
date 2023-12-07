package com.makesoftware.siga.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ControllerUtils {
    // TODO: Create a tryFind that accepts a function and returns T,
    //  and throws a ResponseStatusException with the message if it fails.

    public static <T> T findById(long id, JpaRepository<T, Long> repository, Messages errorMessage) {
        try {
            return repository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage.getMessage());
        }
    }

    public static <T> ResponseEntity<Map<String, String>> deleteById(long id, JpaRepository<T, Long> repository, Messages deletionMessage) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", deletionMessage.getMessage());

        return ResponseEntity.ok().body(response);
    }
}

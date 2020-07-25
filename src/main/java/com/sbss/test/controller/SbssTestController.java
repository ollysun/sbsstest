package com.sbss.test.controller;

import com.sbss.test.domain.Profile;
import com.sbss.test.exception.ProfileNotFoundException;
import com.sbss.test.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/profiles")
public class SbssTestController {

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping
    public List<Profile> findAllBooks() {
        return profileRepository.findAll();
    }

    // Find
    @GetMapping("/{id}")
    Profile findOne(@PathVariable @Min(1) Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("profile not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile createProfile(@Valid @RequestBody Profile profile) {
        return profileRepository.save(profile);
    }
}

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
    public Profile findProfile(@PathVariable @Min(1) Long id) {
        Profile profile = profileRepository.findProfileById(id);
        if (profile==null)
                throw new ProfileNotFoundException("Profile with this id = " + id + " not found");
        return profile;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile createProfile(@Valid @RequestBody Profile profile) {
        return profileRepository.save(profile);
    }
}

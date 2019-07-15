package com.lambdaschool.javacountries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NameController
{
    // localhost:8080/names/all
    // return all countries alphabetically by name
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        return new ResponseEntity<>(JavacountriesApplication.ourCountryList.getAlphabeticalCountries(), HttpStatus.OK);
    }

    // localhost:8080/names/start/{letter}
    // return countries that start with that letter alphabetically
    @GetMapping(value = "/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getSameLetterCountries(@PathVariable char letter)
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnCtry, HttpStatus.OK);
    }

    // localhost:8080/names/size/{number}
    // return countries alphabetically that have a name equal to or greater than a given length
    @GetMapping(value = "/size/{number}", produces = {"application/json"})
    public ResponseEntity<?> getByCountryNameSize(@PathVariable int number)
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getName().length() >= number);
        return new ResponseEntity<>(rtnCtry, HttpStatus.OK);
    }
}

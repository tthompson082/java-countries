package com.lambdaschool.javacountries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/age")
public class AgeController
{
    // localhost:8080/age/age/{age}
    // return the countries that have a medan age greater than or equal to the given age
    @GetMapping(value = "/age/{age}", produces = {"application/json"})
    public ResponseEntity<?> countriesByMedianAge(@PathVariable int age)
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getMedianAge() >= age);
        return new ResponseEntity<>(rtnCtry, HttpStatus.OK);
    }
}

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

    // localhost:8080/age/min
    // return the country with the lowest median age
    @GetMapping(value = "/min", produces = {"application/json"})
    public  ResponseEntity<?> smallestMedianAge()
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getMedianAge() > 0);
        rtnCtry.sort((o1, o2) -> o1.getMedianAge() - o2.getMedianAge());
        return new ResponseEntity<>(rtnCtry.get(0), HttpStatus.OK);
    }

    // localhost:8080/age/max
    // return the country with the lowest median age
    @GetMapping(value = "/max", produces = {"application/json"})
    public  ResponseEntity<?> largestMedianAge()
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getMedianAge() > 0);
        rtnCtry.sort((o1, o2) -> o2.getMedianAge() - o1.getMedianAge());
        return new ResponseEntity<>(rtnCtry.get(0), HttpStatus.OK);
    }

    //STRETCH
    // localhost:8080/age/median
    // return the country with the median median age
    @GetMapping(value = "/median", produces = {"application/json"})
    public  ResponseEntity<?> medianPopulation()
    {
        // to get the median first add all of the countries to rtnCtry
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() > 0);
        // then sort by median age
        rtnCtry.sort((o1,o2) -> (o2.getMedianAge() - o1.getMedianAge()));
        // finally create an if statement to handle the possibility of there being an even number of elements in the list
        if ((rtnCtry.size()%2) == 0)
        {
            return new ResponseEntity<>(rtnCtry.get(rtnCtry.size()/2 + (1/2)), HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(rtnCtry.get(rtnCtry.size()/2), HttpStatus.OK);
        }
    }
}

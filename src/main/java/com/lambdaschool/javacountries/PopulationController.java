package com.lambdaschool.javacountries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/population")
public class PopulationController
{
    // localhost:8080/population/size/{people}
    // return countries that have a population greater than or equal to the given population
    @GetMapping(value = "/size/{people}", produces = {"application/json"})
    public ResponseEntity<?> countriesByPopulation(@PathVariable int people)
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() >= people);
        return new ResponseEntity<>(rtnCtry, HttpStatus.OK);
    }

    // localhost:8080/population/min
    // return the country with the smallest population
    @GetMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> smallestPopulation()
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() > 0);
        rtnCtry.sort((o1, o2) -> (o1.getPopulation() - o2.getPopulation()));
        return new ResponseEntity<>(rtnCtry.get(0), HttpStatus.OK);
    }

    // localhost:8080/population/max
    // return the country with the largest population
    @GetMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> largestPopulation()
    {
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() > 0);
        rtnCtry.sort((o1, o2) -> (o2.getPopulation() - o1.getPopulation()));
        return new ResponseEntity<>(rtnCtry.get(0), HttpStatus.OK);
    }

    //STRETCH
    // localhost:8080/population/median
    // return the country with the median population
    @GetMapping(value = "/median", produces = {"application/json"})
    public  ResponseEntity<?> medianPopulation()
    {
        // to get the median first add all of the countries to rtnCtry
        ArrayList<Country> rtnCtry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() > 0);
        // then sort by population
        rtnCtry.sort((o1,o2) -> (o2.getPopulation() - o1.getPopulation()));
        // finally create an if statement to handle the possibility of there being an even number of elements in the list
        if ((rtnCtry.size()/2) % 2 == 0)
        {
            return new ResponseEntity<>(rtnCtry.get(rtnCtry.size()/2), HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(rtnCtry.get(rtnCtry.size()/2 + 1), HttpStatus.OK);
        }
    }
}


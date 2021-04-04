package ro.fasttrackit.curs5homeworkkotlin.controller

import org.springframework.web.bind.annotation.*
import ro.fasttrackit.curs5homeworkkotlin.service.CountryService

@RestController
@RequestMapping("continents")
class ContinentController(private val countryService: CountryService) {

    @GetMapping("{continentName}/countries")
    fun getCountriesOnContinents(
        @PathVariable continentName: String,
        @RequestParam(required = false) minPopulation: Long?
    ) = countryService.getContinentCountries(continentName, minPopulation)

    @GetMapping("countries")
    fun mapCountriesToContinents() = countryService.mapCountriesByContinent()
}

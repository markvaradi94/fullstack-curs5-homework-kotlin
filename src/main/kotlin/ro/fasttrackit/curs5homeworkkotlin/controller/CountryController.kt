package ro.fasttrackit.curs5homeworkkotlin.controller

import org.springframework.web.bind.annotation.*
import ro.fasttrackit.curs5homeworkkotlin.domain.Country
import ro.fasttrackit.curs5homeworkkotlin.service.CountryService

@RestController
@RequestMapping("countries")
class CountryController(private val countryService: CountryService) {

    @GetMapping
    fun getCountries(
        @RequestParam(required = false) includeNeighbour: String?,
        @RequestParam(required = false) excludeNeighbour: String?
    ) = countryService.getCountries(includeNeighbour, excludeNeighbour)

    @GetMapping("names")
    fun getCountryNames() = countryService.getCountryNames()

    @GetMapping("{countryId}/capital")
    fun getCapitalOfCountry(@PathVariable countryId: Int) = countryService.getCapitalOfCountry(countryId)

    @GetMapping("{countryId}/population")
    fun getPopulationOfCountry(@PathVariable countryId: Int) = countryService.getPopulationOfCountry(countryId)

    @GetMapping("{countryId}/neighbours")
    fun getNeighboursOfCountry(@PathVariable countryId: Int) = countryService.getCountryNeighbours(countryId)

    @GetMapping("population")
    fun mapCountryNamesToPopulation() = countryService.mapCountryNamesToPopulation()

    @GetMapping("mine")
    fun getMyCountry(@RequestHeader(value = "X-Country", defaultValue = "Romania") countryName: String): Country =
        countryService.getMyCountry(countryName)
}

package ro.fasttrackit.curs5homeworkkotlin.service

import org.springframework.stereotype.Service
import ro.fasttrackit.curs5homeworkkotlin.domain.Country
import ro.fasttrackit.curs5homeworkkotlin.reader.CountryReader
import ro.fasttrackit.curs5homeworkkotlin.repository.CountryRepository
import java.util.function.Predicate
import java.util.function.Supplier

@Service
class CountryService(private val countryReader: CountryReader, private val countryRepository: CountryRepository) {

    init {
        persistCountriesInDb()
    }

    fun getCapitalOfCountry(countryId: Int) = getOrThrow(countryId).capital

    fun getPopulationOfCountry(countryId: Int) = getOrThrow(countryId).population

    fun getCountryNames() = allCountries().map { it.name }

    fun getCountryNeighbours(countryId: Int): List<String> {
        val countryToFind = getOrThrow(countryId)
        return parseCountryNeighbours(countryToFind)
    }

    fun getContinentCountries(continentName: String, minPopulation: Long?): List<Country> {
        return if (minPopulation == null) getCountriesOnContinent(continentName)
        else getCountriesOnContinentWithMinPopulation(continentName, minPopulation)
    }

    fun getCountriesOnContinent(continentName: String) = allCountries()
        .filter { it.continent.equals(continentName, ignoreCase = true) }

    fun getCountriesOnContinentWithMinPopulation(continentName: String, minPopulation: Long) =
        getCountriesOnContinent(continentName)
            .filter { it.population >= minPopulation }

    fun mapCountryNamesToPopulation() = allCountries().associate { it.name to it.population }

    fun mapCountriesByContinent(): Map<String, List<Country>> = allCountries().groupBy { it.continent }

    fun getCountries(includeNeighbour: String?, excludeNeighbour: String?): List<Country> {
        return if (includeNeighbour == null && excludeNeighbour == null) allCountries()
        else getCountriesByNeighbours(includeNeighbour, excludeNeighbour)
    }

    fun getMyCountry(countryName: String) = allCountries().first { it.name.equals(countryName, ignoreCase = true) }

    private fun allCountries() = countryRepository.findAll()

    private fun getCountriesByNeighbours(includeNeighbour: String?, excludeNeighbour: String?) =
        allCountries().filter { satisfiesNeighboursCondition(includeNeighbour, excludeNeighbour, it) }

    private fun satisfiesNeighboursCondition(includeNeighbour: String?, excludeNeighbour: String?, country: Country) =
        country.neighbours.contains(includeNeighbour!!) && !country.neighbours.contains(excludeNeighbour!!)

    private fun parseCountryNeighbours(country: Country): List<String> =
        country.neighbours.split("~").toList()


    private fun getOrThrow(countryId: Int): Country {
        return countryRepository
            .findById(countryId)
            .orElseThrow { RuntimeException("Could not find country with ID: $countryId") }
    }

    private fun persistCountriesInDb() {
        countryReader.readCountries().forEach(this::saveCountry)
    }

    private fun saveCountry(country: Country) = countryRepository.save(country)
}

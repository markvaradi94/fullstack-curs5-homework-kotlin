package ro.fasttrackit.curs5homeworkkotlin.reader

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import ro.fasttrackit.curs5homeworkkotlin.config.CountryFileConfig
import ro.fasttrackit.curs5homeworkkotlin.domain.Country
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors.toList

interface CountryReader {
    fun readCountries(): List<Country>
}

@Component
@Profile("file")
class FileCountryReader(private val fileConfig: CountryFileConfig) : CountryReader {
    override fun readCountries(): List<Country> {
        return Files.lines(Path.of(fileConfig.sourceFile))
            .map(this::readCountry)
            .collect(toList())
    }

    private fun readCountry(line: String): Country {
        val tokens = line.split("|")
        return Country(
            tokens[0],
            tokens[1],
            tokens[2].toLong(),
            tokens[3].toLong(),
            tokens[4],
            if (tokens.size > 5) tokens[5] else ""
        )
    }
}

@Component
@Profile("memory")
class InMemoryCountryReader : CountryReader {
    override fun readCountries(): List<Country> =
        listOf(
            Country(
                "Brazil",
                "Brasília",
                206135893L,
                8515767L,
                "Americas",
                "ARG~BOL~COL~GUF~GUY~PRY~PER~SUR~URY~VEN"
            ),
            Country(
                "Burkina Faso",
                "Ouagadougou",
                19034397L,
                272967L,
                "Africa",
                "BEN~CIV~GHA~MLI~NER~TGO"

            ),
            Country(
                "Mongolia",
                "Ulan Bator",
                3093100L,
                1564110L,
                "Asia",
                "CHN~RUS"

            ),
            Country(
                "Nicaragua",
                "Managua",
                6262703L,
                130373L,
                "Americas",
                "CRI~HND"

            ),
            Country(
                "Romania",
                "Bucharest",
                19861408L,
                238391L,
                "Europe",
                "BGR~HUN~MDA~SRB~UKR"

            )
        )
}





package ro.fasttrackit.curs5homeworkkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import ro.fasttrackit.curs5homeworkkotlin.domain.Country

interface CountryRepository : JpaRepository<Country, Int>

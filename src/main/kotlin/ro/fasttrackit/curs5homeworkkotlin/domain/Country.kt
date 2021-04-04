package ro.fasttrackit.curs5homeworkkotlin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Country(
    @JsonProperty var name: String,
    @JsonProperty var capital: String,
    @JsonProperty var population: Long,
    @JsonProperty var area: Long,
    @JsonProperty var continent: String,
    @JsonProperty var neighbours: String
) {
    @Id
    @GeneratedValue
    val id: Int = 0
}

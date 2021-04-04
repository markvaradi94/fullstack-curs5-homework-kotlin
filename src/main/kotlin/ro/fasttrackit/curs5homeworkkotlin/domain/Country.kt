package ro.fasttrackit.curs5homeworkkotlin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Country(
    @JsonProperty val name: String,
    @JsonProperty val capital: String,
    @JsonProperty val population: Long,
    @JsonProperty val area: Long,
    @JsonProperty val continent: String,
    @JsonProperty val neighbours: String
) {
    @Id
    @GeneratedValue
    val id: Int = 0
}

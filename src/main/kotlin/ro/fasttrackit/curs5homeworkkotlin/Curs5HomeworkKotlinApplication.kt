package ro.fasttrackit.curs5homeworkkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class Curs5HomeworkKotlinApplication

fun main(args: Array<String>) {
    runApplication<Curs5HomeworkKotlinApplication>(*args)
}

package ro.fasttrackit.curs5homeworkkotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties

@EnableConfigurationProperties(CountryFileConfig::class)
@ConfigurationProperties(prefix = "country")
@ConstructorBinding
data class CountryFileConfig(val sourceFile: String)

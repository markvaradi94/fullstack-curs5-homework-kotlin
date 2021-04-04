package ro.fasttrackit.curs5homeworkkotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "country")
class CountryFileConfig {
    private lateinit var sourceFile: String;

    fun getSourceFile(): String? {
        return sourceFile
    }

    fun setSourceFile(sourceFile: String?) {
        this.sourceFile = sourceFile!!
    }
}

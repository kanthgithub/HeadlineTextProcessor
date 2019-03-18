package io.headlines.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.headlines.model.CityModel;
import io.headlines.model.CountryModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class JsonDataDictionaryService {

    public static final String CITIES_JSON = "cities.json";

    public static final String COUNTRIES_JSON = "countries.json";

    private static final Logger log = LoggerFactory.getLogger(JsonDataDictionaryService.class);

    private Set cities = new HashSet();

    private Set countries = new HashSet();

    @PostConstruct
    public void init() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        CityModelWrapper cityModelWrapper = objectMapper.readValue(new File(CITIES_JSON), CityModelWrapper.class);
        cities = cityModelWrapper.getCities().stream().map(city -> city.getName()).collect(Collectors.toSet());
        log.info("cities: {}", cities);

        CountryModelWrapper countryModelWrapper = objectMapper.readValue(new File(COUNTRIES_JSON), CountryModelWrapper.class);
        countries = countryModelWrapper.getCountries().stream().map(country -> country.getName()).collect(Collectors.toSet());
        log.info("countries: {}", countries);
    }

    public String transformCityMentionString(String textToken) {
        log.info("searchForMentionsAndTransform - City for {}",textToken);

        return searchForMentionsAndTransform(textToken, cities);
    }

    public String transformCountryMentionString(String textToken) {

        log.info("searchForMentionsAndTransform - Country for {}",textToken);

        return searchForMentionsAndTransform(textToken, countries);
    }

    public static String searchForMentionsAndTransform(String textToken, Set<String> dictionaryTokens) {

        String transformedMentionString = textToken;
        String patternString = "\\b(" +StringUtils.join(dictionaryTokens, "|") + ")\\b";
        log.info("pattern string: {}",patternString);
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(textToken);

        while (matcher.find()) {

            log.info("matcher matches the token");

            String mention = matcher.group(0);

                if(mention!=null){
                    log.info("matched String: {} at index: {} ", mention,String.valueOf(0));
                    String capitalizedMention = WordUtils.capitalize(mention);
                    log.info("capitalized String:{} to {}",mention,capitalizedMention);
                    transformedMentionString =
                            transformedMentionString.replaceAll(mention, capitalizedMention);
            }
        }

        return transformedMentionString;
    }

    public static class CityModelWrapper {
        List<CityModel> cities;

        public List<CityModel> getCities() {
            return cities;
        }

        public CityModelWrapper setCities(List<CityModel> cities) {
            this.cities = cities;
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CityModelWrapper{");
            sb.append("cities=").append(cities);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class CountryModelWrapper {
        List<CountryModel> countries;

        public List<CountryModel> getCountries() {
            return countries;
        }

        public CountryModelWrapper setCountries(List<CountryModel> countries) {
            this.countries = countries;
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CountryModelWrapper{");
            sb.append("countries=").append(countries);
            sb.append('}');
            return sb.toString();
        }
    }
}

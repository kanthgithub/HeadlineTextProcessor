package io.headlines.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.headlines.model.CityModel;
import io.headlines.model.CountryModel;
import net.amygdalum.stringsearchalgorithms.search.StringFinder;
import net.amygdalum.stringsearchalgorithms.search.StringMatch;
import net.amygdalum.stringsearchalgorithms.search.chars.WuManber;
import net.amygdalum.util.io.CharProvider;
import net.amygdalum.util.io.StringCharProvider;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

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
        log.debug("searchForMentionsAndTransform - City for {}",textToken);

        return searchForMentionsAndTransform(textToken, cities);
    }

    public String transformCountryMentionString(String textToken) {

        log.debug("searchForMentionsAndTransform - Country for {}",textToken);

        return searchForMentionsAndTransform(textToken, countries);
    }

    public static String searchForMentionsAndTransform(String textToken, Set<String> dictionaryTokens) {

        WuManber stringSearch_WuManber = new WuManber(dictionaryTokens);
        CharProvider text_WuManber = new StringCharProvider(WordUtils.capitalizeFully(textToken), 0);
        StringFinder finder_WuManber = stringSearch_WuManber.createFinder(text_WuManber, LONGEST_MATCH, NON_OVERLAP);
        List<StringMatch> all_WuManber = finder_WuManber.findAll();

        String transformedString = textToken;

        for(StringMatch stringMatch : all_WuManber){
           Long startIndex =  stringMatch.start();
           Long endIndex = stringMatch.end();
           String text = stringMatch.text();
           String replaceWithText = WordUtils.capitalizeFully(text);
           transformedString = transformedString.replace(transformedString.substring(startIndex.intValue(),endIndex.intValue()), replaceWithText);
        }

        return transformedString;
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

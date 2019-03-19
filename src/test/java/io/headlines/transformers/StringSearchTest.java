package io.headlines.transformers;

import net.amygdalum.stringsearchalgorithms.search.StringFinder;
import net.amygdalum.stringsearchalgorithms.search.StringMatch;
import net.amygdalum.stringsearchalgorithms.search.chars.AhoCorasick;
import net.amygdalum.stringsearchalgorithms.search.chars.Horspool;
import net.amygdalum.stringsearchalgorithms.search.chars.WuManber;
import net.amygdalum.util.io.CharProvider;
import net.amygdalum.util.io.StringCharProvider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

public class StringSearchTest {

    Logger log = LoggerFactory.getLogger(StringSearchTest.class);

    @Test
    public void test_Assert_Country_Match(){

        //given
        Horspool stringSearch = new Horspool("word");
        CharProvider text = new StringCharProvider("text with word in it", 0);
        StringFinder finder = stringSearch.createFinder(text);
        //StringMatch next = finder.findNext();
        List<StringMatch> all = finder.findAll();

        //log.info("next: {}",next);
        log.info("all: {}",all);

        AhoCorasick stringSearch_AhoCorasick = new AhoCorasick(Arrays.asList("word", "longer word"));
        CharProvider text_AhoCorasick = new StringCharProvider("text with longer word and Word in it", 0);
        StringFinder finder_AhoCorasick = stringSearch_AhoCorasick.createFinder(text_AhoCorasick, LONGEST_MATCH, NON_OVERLAP);
        List<StringMatch> all_AhoCorasick = finder_AhoCorasick.findAll();

        log.info("all_AhoCorasick: {}",all_AhoCorasick);

        WuManber stringSearch_WuManber = new WuManber(Arrays.asList("Word", "longer word"));
        CharProvider text_WuManber = new StringCharProvider("text with longer word and WorD in it", 0);
        StringFinder finder_WuManber = stringSearch_WuManber.createFinder(text_WuManber, LONGEST_MATCH, NON_OVERLAP);
        List<StringMatch> all_WuManber = finder_WuManber.findAll();
        log.info("all_WuManber: {}",all_WuManber);
    }

}

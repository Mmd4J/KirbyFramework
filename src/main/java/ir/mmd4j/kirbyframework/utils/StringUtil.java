package ir.mmd4j.kirbyframework.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    /**
     * a shorter version of ChatColor.translateAlternateColorCodes('&',text)
     * @param text the text trying to translate its color
     * @return
     */
    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * returns list of possible words that fit the word also cuts the word itself.
     * @param word given non completed word
     * @param words list of possible words to complete
     * @return
     */
    public static List<String> completedWords(String word, List<String> words) {
        return words.stream().filter(w -> w.toLowerCase().startsWith(word.toLowerCase())).map(String::toLowerCase)
                .map(w -> w.substring(word.length())).collect(Collectors.toList());
    }

    /**
     * Shows the possible list of words that are started by the specified word
     * @param word given word to check
     * @param words possible wordlist
     * @return
     */
    public static List<String> listWordsStartWith(String word, List<String> words) {
        return words.stream().filter(w -> w.toLowerCase().startsWith(word.toLowerCase())).map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}

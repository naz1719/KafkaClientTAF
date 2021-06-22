package com.project.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class with utilities for manipulation with strings
 */
public final class StringUtils {

   private static final String ALPHABET_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   private static final String NUMERIC_CHARACTERS = "0123456789";
   private static final String SPECIAL_CHARACTERS = "~`!@#$%^&*()-_=+[{]}\\\\|;:\\'\\\",<.>/?";
   private static final String DEFAULT_CHARACTERS = ALPHABET_CHARACTERS + NUMERIC_CHARACTERS;
   private static final String LETTERS_AND_NUMBERS_CHARACTERS = DEFAULT_CHARACTERS ;

   private StringUtils() {
   }

   public static final String NEW_LINE = "\n";

   /**
    * Converts Map to string representation
    * @param map to be converted to string
    */
   public static String mapToString(Map<String, String> map) {
      return map.entrySet()
            .stream()
            .map(entry -> entry.getKey() + " - " + entry.getValue())
            .collect(Collectors.joining(", "));
   }

   /**
    * Generates random string english alphabet letters
    */
   public static String getRandomStringWithAlphabetCharacters(int length){
      return RandomStringUtils.random(length, ALPHABET_CHARACTERS);
   }


   /**
    * Generates random string containing digits and english alphabet letters
    */
   public static String getRandomString(int length){
      return RandomStringUtils.random(length, DEFAULT_CHARACTERS);
   }

   /**
    * Generates random string containing digits, english alphabet letters and letters with accent
    */
   public static String getRandomStringWithoutSpecialCharacters(int length){
      return RandomStringUtils.random(length, LETTERS_AND_NUMBERS_CHARACTERS);
   }

    /**
     * Format search result string based on template and entity field values
     * @param template message template
     * @param entity entity
     * @return String that contains message about search result
     */
   public static String formatEntitySearchResultString(String template, Object entity){
      String entityToSting = StringUtils.toPrettyJsonString(entity);
      entityToSting = StringUtils.indentString(entityToSting, 1);
      return String.format(template, entityToSting);
   }

   /**
    * Indent each lines in string with tabs
    * @param string original string
    * @param tabsNumber number of tabs to add
    * @return String with added indentation
    */
   public static String indentString(String string, int tabsNumber){
      char[] charArray = new char[tabsNumber];
      Arrays.fill(charArray, '\t');
      String stringWithTabs = new String(charArray);
      return stringWithTabs + string.replaceAll(NEW_LINE, NEW_LINE + stringWithTabs);
   }

   /**
    * Modifies Json string for pretty printing
    * @param object original object
    * @return formatted string
    */
   public static String toPrettyJsonString(Object object){
      ObjectMapper mapper = new ObjectMapper();
      String prettyJson;
      try{
         prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
      }catch (IOException e){
         prettyJson = object.toString();
      }
      return prettyJson;
   }

}

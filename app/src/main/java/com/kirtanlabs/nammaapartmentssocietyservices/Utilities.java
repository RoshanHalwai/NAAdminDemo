package com.kirtanlabs.nammaapartmentssocietyservices;

/**
 * KirtanLabs Pvt. Ltd.
 * Created by Roshan Halwai on 8/2/2018
 */
public class Utilities {

    /**
     * Takes each letter of a word and converts them to upper case
     *
     * @param stringToCapitalize input string
     * @return Capitalized Letter of each word
     */
    public static String capitalizeString(String stringToCapitalize) {
        String[] splits = stringToCapitalize.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String eachWord = splits[i];
            if (i > 0 && eachWord.length() > 0) {
                sb.append(" ");
            }
            String cap = eachWord.substring(0, 1).toUpperCase()
                    + eachWord.substring(1);
            sb.append(cap);
        }
        return sb.toString();
    }
}

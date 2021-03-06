package org.rob4001.iAuction;

import java.io.File;
import java.io.IOException;
import org.bukkit.entity.Player;

/**
 * Copyright (C) 2011  Nijikokun <nijikokun@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * Misc.java
 * <br /><br />
 * Miscellaneous functions / variables, and other things that really don't go anywhere else.
 *
 * @author Nijikokun <nijikokun@gmail.com>
 */
public class Misc {

    public Misc() {
    }

    /**
     * Checks the length of an array against the amount plus one,
     * Allowing us to know the true amount of numbers.
     * <br /><br />
     * Example:
     * <blockquote><pre>
     * arguments({0,1,2}, 2); // < 4 length = [0,1,2] = true. It does end at 2.
     * </pre></blockquote>
     *
     * @param array The array we are checking the length of
     * @param amount The amount necessary to continue onwards.
     *
     * @return <code>Boolean</code> - True or false based on length.
     */
    public static Boolean arguments(String[] array, int amount) {
        return (array.length < (amount + 2)) ? true : false;
    }

    /**
     * Checks text against one variables.
     *
     * @param text The text that we were provided with.
     * @param against The first variable that needs to be checked against
     *
     * @return <code>Boolean</code> - True or false based on text.
     */
    public static Boolean is(String text, String against) {
        return (text.equalsIgnoreCase(against)) ? true : false;
    }

    /**
     * Checks text against two variables, if it equals at least one returns true.
     *
     * @param text The text that we were provided with.
     * @param against The first variable that needs to be checked against
     * @param or The second variable that it could possibly be.
     *
     * @return <code>Boolean</code> - True or false based on text.
     */
    public static Boolean isEither(String text, String against, String or) {
        return (text.equalsIgnoreCase(against) || text.equalsIgnoreCase(or)) ? true : false;
    }

    /**
     * Basic formatting on Currency
     *
     * @param Balance The player balance or amount being payed.
     * @param currency The iConomy currency name
     *
     * @return <code>String</code> - Formatted with commas & currency
     */
    public static String formatCurrency(int Balance, String currency) {
        return insertCommas(String.valueOf(Balance)) + " " + currency;
    }

    /**
     * Basic formatting for commas.
     *
     * @param str The string we are attempting to format
     *
     * @return <code>String</code> - Formatted with commas
     */
    public static String insertCommas(String str) {
        if (str.length() < 4) {
            return str;
        }

        return insertCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
    }

    /**
     * Convert int to string
     *
     * @return <code>String</code>
     */
    public static String string(int i) {
        return String.valueOf(i);
    }

    /**
     * String function.
     * <br /><br />
     * Combines a split string at a specific index.
     *
     * @param startIndex
     * @param string
     * @param seperator
     * @return
     */
    public static String combine(int startIndex, String[] string, String seperator) {
        StringBuilder builder = new StringBuilder();

        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }

        builder.deleteCharAt(builder.length() - seperator.length()); // remove
        return builder.toString();
    }

    /**
     * Capitalization.
     *
     * @param s
     * @return
     */
    public static String capitalize(String s){
        return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }

    /** Turns "SomeName" into "Some Name" or "MyABC" into "My ABC".
    (Inserts a space before a capital letter unless it is at the beginning of
    the string or preceded by a capital letter.) */
    public static String camelToPhrase(String str) {
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            if (i > 0 && Character.isUpperCase(str.charAt(i)) && !Character.isUpperCase(str.charAt(i - 1))) {
                newStr += ' ';
            }
            newStr += str.charAt(i);
        }
        return newStr;
    }

    /**
     * Validate an alphanumeric string with ._- as well.
     *
     * @param name
     * @return boolean
     */
    public static boolean validate(String name) {
        return name.matches("([0-9a-zA-Z._-]+)");
    }

    /**
     * Repeat c (i) amount of times.
     *
     * @param c
     * @param i
     * @return
     */
    public static String repeat(char c, int i) {
        String tst = "";
        for (int j = 0; j < i; j++) {
            tst = tst + c;
        }
        return tst;
    }

    /**
     * Retrieve player from server if they exist.
     * <br><br>
     * Based on name.
     *
     * @param name
     * @return
     */
    public static Player player(String name) {
        if (iAuction.server.getOnlinePlayers().length < 1) {
            return null;
        }

        Player[] online = iAuction.server.getOnlinePlayers();
        Player player = null;

        for (Player needle : online) {
            if (needle.getName().equals(name)) {
                player = needle;
                break;
            } else if (needle.getDisplayName().equals(name)) {
                player = needle;
                break;
            }
        }

        return player;
    }

    /**
     * Match players based on name. Include Display name as well.
     *
     * @param name
     * @return
     */
    public static Player playerMatch(String name) {
        if (iAuction.server.getOnlinePlayers().length < 1) {
            return null;
        }

        Player[] online = iAuction.server.getOnlinePlayers();
        Player lastPlayer = null;

        for (Player player : online) {
            String playerName = player.getName();
            String playerDisplayName = player.getDisplayName();

            if (playerName.equalsIgnoreCase(name)) {
                lastPlayer = player;
                break;
            } else if (playerDisplayName.equalsIgnoreCase(name)) {
                lastPlayer = player;
                break;
            }

            if (playerName.toLowerCase().indexOf(name.toLowerCase()) != -1) {
                if (lastPlayer != null) {
                    return null;
                }

                lastPlayer = player;
            } else if (playerDisplayName.toLowerCase().indexOf(name.toLowerCase()) != -1) {
                if (lastPlayer != null) {
                    return null;
                }

                lastPlayer = player;
            }
        }

        return lastPlayer;
    }

    /**
     * Create file quick and easy without having to initilize a file other than directory.
     *
     * @param directory
     * @param name
     */
    public static void touch(File directory, String name) {
        try {
            (new File(directory, name)).createNewFile();
        } catch (IOException ex) {
        }
    }

    /**
     * Create a file through string rather than directory / filename method.
     *
     * @param name
     */
    public static void touch(String name) {
        try {
            (new File(name)).createNewFile();
        } catch (IOException ex) {
        }
    }
}
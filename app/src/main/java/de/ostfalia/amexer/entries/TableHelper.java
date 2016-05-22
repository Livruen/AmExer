package de.ostfalia.amexer.entries;

import java.util.ArrayList;
/**
 * The class is a helepr class for Data center and library activity.
 * It parses the string times from the tables into a int array with the times.
 * It checks also if current time is in opening time range from the library
 *  or data center .
 *
 * @autor Natasza Szczypien
 * Created by livruen on 22.05.16.
 */

public class TableHelper {
    /**
     * Parses the
     * @param rowTime1, rowTime2  in the Format "  00:00-00:00  "
     * @return  to an array with strings [00,00,00,00]
     */
    public ArrayList<Integer> prepareTime(String rowTime1 /*,String rowTime2*/) {

        /* parses the rowTime*/
        rowTime1 = rowTime1.trim();
        ArrayList<Integer> tempList = new ArrayList<>(); // [00:00-00:00]
        String[] twoTemps = rowTime1.split("-");
        String[] timeOne = twoTemps[0].toString().split(":");
        String[] timeTwo = twoTemps[1].toString().split(":");

        tempList.add(Integer.parseInt(timeOne[0].trim()));
        tempList.add(Integer.parseInt(timeOne[1].trim()));
        tempList.add(Integer.parseInt(timeTwo[0].trim()));
        tempList.add(Integer.parseInt(timeTwo[1].trim()));

        return tempList;
    }
    /**
     * Checks if Current time is in the open -time-range
     *
     * @param tempOpenHour1
     * @param tempOpenMinute1
     * @param tempCloseHour1
     * @param tempCloseMinute1
     * @param currentHour
     * @param currentMinute
     * @return
     */
    public boolean isOpen(int tempOpenHour1, int tempOpenMinute1, int tempCloseHour1, int tempCloseMinute1, int currentHour, int currentMinute) {

        if (currentHour == tempOpenHour1) {
            if (currentMinute >= tempOpenMinute1) {
                return true;
            } else {
                return false;
            }

        } else if (currentHour > tempOpenHour1 && currentHour <= tempCloseHour1) {
            return true;
        } else {
            return false;
        }
    }
}

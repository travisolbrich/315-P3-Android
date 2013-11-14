package olbrich.csce315.birdbuddy.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */
public enum Season {

    Spring,

    Summer,

    Fall,

    Winter;

    public static Season seasonForDate(Date date) {

        Season season = Spring;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH);

        switch (month) {
            case Calendar.DECEMBER:
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
                season = Season.Winter;
                break;
            case Calendar.MARCH:
            case Calendar.APRIL:
            case Calendar.MAY:
                season = Season.Spring;
                break;
            case Calendar.JUNE:
            case Calendar.JULY:
            case Calendar.AUGUST:
                season = Season.Summer;
                break;
            case Calendar.SEPTEMBER:
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
                season = Season.Fall;
                break;
        }

        return season;
    }

}

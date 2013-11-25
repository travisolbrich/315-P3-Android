package olbrich.csce315.birdbuddy.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */
public enum CalendarSeason {

    Spring,

    Summer,

    Fall,

    Winter;

    public static CalendarSeason seasonForDate(Date date) {

        CalendarSeason season = Spring;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH);

        switch (month) {
            case Calendar.DECEMBER:
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
                season = CalendarSeason.Winter;
                break;
            case Calendar.MARCH:
            case Calendar.APRIL:
            case Calendar.MAY:
                season = CalendarSeason.Spring;
                break;
            case Calendar.JUNE:
            case Calendar.JULY:
            case Calendar.AUGUST:
                season = CalendarSeason.Summer;
                break;
            case Calendar.SEPTEMBER:
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
                season = CalendarSeason.Fall;
                break;
        }

        return season;
    }

}

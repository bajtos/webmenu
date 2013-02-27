package webmenu.viewmodel;

import java.util.*;
import java.text.*;
import javax.servlet.ServletRequest;

import webmenu.model.OneDayMenu;

public class DeliveryViewModel
{
    private final static String requestAttributeName = DeliveryViewModel.class.getName();
    private Map<String, DayMenuViewModel> menuMap;
    private String urlPrefix;
    private Calendar date;
    private String locationName;
    private String warningText;
    private boolean isDefaultDate;

    public String getWarningText() {
       return warningText;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean getIsDefaultDate() {
       return isDefaultDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public DayMenuViewModel getMenu(String restaurant) {
        return menuMap.get(restaurant);
    }

    public String getDayUrl(int dayOfWeek) {
        String part = "";
        switch (dayOfWeek)
        {
            case Calendar.MONDAY:
                part = "pondeli";
                break;
            case Calendar.TUESDAY:
                part = "utery";
                break;
            case Calendar.WEDNESDAY:
                part = "streda";
                break;
            case Calendar.THURSDAY:
                part = "ctvrtek";
                break;
            case Calendar.FRIDAY:
                part = "patek";
                break;
        }
        return MessageFormat.format("{0}/{1}", urlPrefix, part);
    }

    public String getDayName(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return "pondělí";
            case Calendar.TUESDAY:
                return "úterý";
            case Calendar.WEDNESDAY:
                return "středa";
            case Calendar.THURSDAY:
                return "čtvrtek";
            case Calendar.FRIDAY:
                return "pátek";
            case Calendar.SATURDAY:
                return "sobota";
            case Calendar.SUNDAY:
                return "neděle";
            default:
                throw new IllegalArgumentException();
        }
    }

    public static DeliveryViewModel get(ServletRequest request) {
        return (DeliveryViewModel)request.getAttribute(requestAttributeName);
    }

    public void set(ServletRequest request) {
        request.setAttribute(requestAttributeName, this);
    }

    public void setMenu(String restaurant, DayMenuViewModel menu) {
        menuMap.put(restaurant, menu);
    }

    public void setWarningText(String text) {
       warningText = text;
    }

    public DeliveryViewModel(String urlPrefix, String locationName, Calendar date) {
        if (date == null) {
           isDefaultDate = true;
           this.date = Calendar.getInstance();
        } else {
           this.date = date;
        }

        this.locationName = locationName;
        this.urlPrefix = urlPrefix;
        menuMap = new HashMap<String, DayMenuViewModel>();
    }
}

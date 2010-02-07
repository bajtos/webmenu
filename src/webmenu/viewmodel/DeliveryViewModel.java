package webmenu.viewmodel;

import java.util.*;
import java.text.*;
import javax.servlet.ServletRequest;

import webmenu.model.OneDayMenu;

public class DeliveryViewModel
{
    private final static String requestAttributeName = DeliveryViewModel.class.getName();
    private Map<String, OneDayMenu> menuMap;
    private String urlPrefix;
    private Calendar date;
    private String locationName;

    public Date getDate()
    {
        return date.getTime();
    }

    public String getLocationName()
    {
        return locationName;
    }

    public OneDayMenu getMenu(String restaurant)
    {
        return menuMap.get(restaurant);
    }

    public String getDayUrl(int dayOfWeek)
    {
        Calendar day = (Calendar)date.clone();
        day.getTime(); // from some strange reasons we have to call this so that the calendar updates its internal state
        day.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return MessageFormat.format("{0}/{1,date,yyyy/MM/dd}", urlPrefix, day.getTime());
    }

    public static DeliveryViewModel get(ServletRequest request)
    {
        return (DeliveryViewModel)request.getAttribute(requestAttributeName);
    }

    public void set(ServletRequest request)
    {
        request.setAttribute(requestAttributeName, this);
    }

    public void setMenu(String restaurant, OneDayMenu menu)
    {
        menuMap.put(restaurant, menu);
    }

    public DeliveryViewModel(String urlPrefix, String locationName, Calendar date)
    {
        this.date = date;
        this.locationName = locationName;
        this.urlPrefix = urlPrefix;
        menuMap = new HashMap<String, OneDayMenu>();
    }
}

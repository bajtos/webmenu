package webmenu.viewmodel;

import java.util.*;
import javax.servlet.ServletRequest;

import webmenu.model.OneDayMenu;

public class DeliveryViewModel
{
    private final static String requestAttributeName = DeliveryViewModel.class.getName();
    private Map<String, OneDayMenu> menuMap;
    private Date date;
    private String location;

    public Date getDate()
    {
        return date;
    }

    public String getLocation()
    {
        return location;
    }

    public OneDayMenu getMenu(String restaurant)
    {
        return menuMap.get(restaurant);
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

    public DeliveryViewModel(String location, Date date)
    {
        this.date = date;
        this.location = location;
        menuMap = new HashMap<String, OneDayMenu>();
    }
}

package webmenu.viewmodel;

import webmenu.model.*;

public class DayMenuViewModel {

    private OneDayMenu menu;
    private Restaurant restaurant;

    public OneDayMenu getMenu() { return menu; }
    public Restaurant getRestaurant() { return restaurant; }

    public DayMenuViewModel(OneDayMenu menu, Restaurant restaurant)
    {
        this.menu = menu;
        this.restaurant = restaurant;
    }
}

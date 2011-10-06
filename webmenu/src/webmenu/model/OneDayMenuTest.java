package webmenu.model;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class OneDayMenuTest
{
   @Test public void Update_SomeData_Works()
   {
      Date day = new GregorianCalendar(2009, 10, 1).getTime();
      List<SoupItem> soups = new ArrayList<SoupItem>();
      List<MenuItem> meals = new ArrayList<MenuItem>();

      OneDayMenu menu = new OneDayMenu(day, null, null);
      menu.update(new OneDayMenu(day, soups, meals));

      assertArrayEquals(soups.toArray(), menu.getSoupItems().toArray());
      assertArrayEquals(meals.toArray(), menu.getMenuItems().toArray());
   }
}


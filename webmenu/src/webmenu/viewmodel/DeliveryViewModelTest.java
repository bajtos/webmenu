package webmenu.viewmodel;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.jmock.*;
import org.junit.runner.RunWith;
import org.jmock.integration.junit4.*;

import java.util.*;

public class DeliveryViewModelTest
{
    @Test public void GetDayUrl_TodayIsMonday_ReturnsCorrectUrls()
    {
       Calendar monday = new GregorianCalendar(2010, 02-1, 01);
       DeliveryViewModel model = new DeliveryViewModel("/prefix", "location", monday);

       assertEquals("/prefix/pondeli", model.getDayUrl(Calendar.MONDAY));
       assertEquals("/prefix/utery", model.getDayUrl(Calendar.TUESDAY));
       assertEquals("/prefix/streda", model.getDayUrl(Calendar.WEDNESDAY));
       assertEquals("/prefix/ctvrtek", model.getDayUrl(Calendar.THURSDAY));
       assertEquals("/prefix/patek", model.getDayUrl(Calendar.FRIDAY));
       assertEquals(monday.getTime(), model.getDate().getTime());
    }

    @Test public void GetDayUrl_TodayIsFriday_ReturnsCorrectUrls()
    {
       Calendar friday = new GregorianCalendar(2010, 02-1, 05);
       DeliveryViewModel model = new DeliveryViewModel("/prefix", "location", friday);

       assertEquals("/prefix/pondeli", model.getDayUrl(Calendar.MONDAY));
       assertEquals("/prefix/utery", model.getDayUrl(Calendar.TUESDAY));
       assertEquals("/prefix/streda", model.getDayUrl(Calendar.WEDNESDAY));
       assertEquals("/prefix/ctvrtek", model.getDayUrl(Calendar.THURSDAY));
       assertEquals("/prefix/patek", model.getDayUrl(Calendar.FRIDAY));
    }
}

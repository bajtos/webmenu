package webmenu.data;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class GoogleDataStoreTest {

    @Test public void CreateId_SameValues_ReturnsSameId()
    {
        String restaurant = "any";
        Date day = new GregorianCalendar(2009, 10, 1).getTime(); // any day

        String id1 = GoogleDataStore.createId(restaurant, day);
        String id2 = GoogleDataStore.createId(restaurant, day);
        assertEquals(id1, id2);
    }

    @Test public void CreateId_DifferentDays_RetursDifferentIds()
    {
        String restaurant = "any";
        Date day1 = new GregorianCalendar(2009, 10, 1).getTime(); // any day
        Date day2 = new GregorianCalendar(2009, 10, 2).getTime(); // any day

        String id1 = GoogleDataStore.createId(restaurant, day1);
        String id2 = GoogleDataStore.createId(restaurant, day2);
        assertThat(id1, not(equalTo(id2)));
    }

    @Test public void CreateId_DifferentRestaurants_RetursDifferentIds()
    {
        String restaurant1 = "any1";
        String restaurant2 = "any2";
        Date day = new GregorianCalendar(2009, 10, 1).getTime(); // any day

        String id1 = GoogleDataStore.createId(restaurant1, day);
        String id2 = GoogleDataStore.createId(restaurant2, day);
        assertThat(id1, not(equalTo(id2)));
    }
}

package webmenu.model;

import javax.jdo.annotations.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class GlobalData
{
    public static final String TheKey = "global-data";

    @PrimaryKey
    private String key = TheKey;

    @Persistent
    private String warningText;

    public String getWarningText() { return warningText != null ? warningText : ""; }
    public void setWarningText(String value) { warningText = "" == value ? null : value; }
}

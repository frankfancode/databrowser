package frankfancode.databrowser.sqlite;

import java.io.Serializable;

/**
 * Created by frank on 17-3-18.
 */

public class TableEntity implements Serializable {
    public String[] titles;
    public String[][] tableData;

    public int getColumnCount() {
        return (titles == null) ? 0 : titles.length;
    }

}

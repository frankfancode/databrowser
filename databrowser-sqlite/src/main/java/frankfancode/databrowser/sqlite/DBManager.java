package frankfancode.databrowser.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.RemoteException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frank on 17-3-11.
 */

public class DBManager {

    private Context context;

    public static DBManager getInstance() {
        return DBManagerHolder.instance;
    }

    /**
     * use application context
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<String> getDBList() {
        String[] dbNames = context.databaseList(); // or ContextWrapper
        return Arrays.asList(dbNames);
    }

    public List<String> getTableList(String databaseName) {
        //创建数据库
        SQLiteDatabase db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select name from sqlite_master where type='table' order by name", null);
        if (null != cursor) {
            List<String> tableNameList = new ArrayList<>();
            while (cursor.moveToNext()) {
                //遍历出表名
                String tableName = cursor.getString(0);
                tableNameList.add(tableName);
            }
            return tableNameList;
        }

        return null;
    }

    public Map getTableData(String databaseName, String tableName) {

        HashMap<String, String> tableDataMap = null;
        //创建数据库
        SQLiteDatabase db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select * from " + tableName + "  order by _id", null);
        if (cursor != null) {
            tableDataMap = new HashMap<>();

            cursor.moveToFirst();

            String columnNames = Arrays.toString(cursor.getColumnNames());

            tableDataMap.put("columnnames", columnNames);
            tableDataMap.put("data", cursorToString(cursor));
        }
        return tableDataMap;
    }

    private String cursorToString(Cursor crs) {
        JSONArray arr = new JSONArray();
        crs.moveToFirst();
        while (!crs.isAfterLast()) {
            int nColumns = crs.getColumnCount();
            JSONObject row = new JSONObject();
            for (int i = 0; i < nColumns; i++) {
                String colName = crs.getColumnName(i);
                if (colName != null) {
                    String val = "";
                    try {
                        switch (crs.getType(i)) {
                            case Cursor.FIELD_TYPE_BLOB:
                                row.put(colName, crs.getBlob(i).toString());
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                row.put(colName, crs.getDouble(i));
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                row.put(colName, crs.getLong(i));
                                break;
                            case Cursor.FIELD_TYPE_NULL:
                                row.put(colName, null);
                                break;
                            case Cursor.FIELD_TYPE_STRING:
                                row.put(colName, crs.getString(i));
                                break;
                        }
                    } catch (JSONException e) {
                    }
                }
            }
            arr.put(row);
            if (!crs.moveToNext())
                break;
        }
        return arr.toString();
    }

    private static class DBManagerHolder {
        private final static DBManager instance = new DBManager();
    }
}
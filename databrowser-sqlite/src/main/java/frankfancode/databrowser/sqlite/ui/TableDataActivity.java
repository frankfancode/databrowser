package frankfancode.databrowser.sqlite.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import frankfancode.databrowser.sqlite.Constant;
import frankfancode.databrowser.sqlite.DBManager;
import frankfancode.databrowser.sqlite.R;

public class TableDataActivity extends AppCompatActivity {
    private String mDbName;
    private String mTableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_data);
        mDbName = getIntent().getStringExtra(Constant.INTENT_DATABASE_NAME);
        mTableName = getIntent().getStringExtra(Constant.INTENT_TABLE_NAME);
        DBManager.getInstance().getTable(mDbName, mTableName);
    }
}

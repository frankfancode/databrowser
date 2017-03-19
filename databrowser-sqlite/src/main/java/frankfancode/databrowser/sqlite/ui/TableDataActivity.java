package frankfancode.databrowser.sqlite.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.HorizontalScrollView;

import frankfancode.databrowser.sqlite.Constant;
import frankfancode.databrowser.sqlite.DBManager;
import frankfancode.databrowser.sqlite.R;
import frankfancode.databrowser.sqlite.TableDataAdapter;
import frankfancode.databrowser.sqlite.TableEntity;

public class TableDataActivity extends AppCompatActivity {
    private String mDbName;
    private String mTableName;

    private GridView mGvTable;
    private HorizontalScrollView hsv;
    private TableDataAdapter mAdapter;
    private TableEntity mTableEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_data);
        initIntent();
        initView();

        getData();
    }

    private void getData() {
        mTableEntity = DBManager.getInstance().getTable(mDbName, mTableName);
        mAdapter = new TableDataAdapter();
        mAdapter.setData(mTableEntity);
        mGvTable.setNumColumns(mTableEntity.getColumnCount());
        mGvTable.setAdapter(mAdapter);
    }

    private void initView() {
        mGvTable = (GridView) findViewById(R.id.gv_table);
//        hsv = (HorizontalScrollView) findViewById(R.id.hsv);
    }

    private void initIntent() {
        mDbName = getIntent().getStringExtra(Constant.INTENT_DATABASE_NAME);
        mTableName = getIntent().getStringExtra(Constant.INTENT_TABLE_NAME);
    }
}

package frankfancode.databrowser.sqlite.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import frankfancode.databrowser.sqlite.constants.Constant;
import frankfancode.databrowser.sqlite.db.DBManager;
import frankfancode.databrowser.sqlite.R;
import frankfancode.databrowser.sqlite.adapter.TableDataAdapter;
import frankfancode.databrowser.sqlite.entity.TableEntity;

public class TableDataActivity extends AppCompatActivity {
    private String mDbName;
    private String mTableName;

    private GridView mGvTable;
    private HorizontalScrollView hsv;
    private LinearLayout mTablelayout;
    private TableDataAdapter mAdapter;
    private TableEntity mTableEntity;
    private int columnWidth=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_data);
        initIntent();
        initView();

        getData();
    }

    private void getData() {
        mTableEntity = DBManager.getInstance().getTable(mDbName, mTableName,Long.MAX_VALUE);
        mAdapter = new TableDataAdapter();
        mAdapter.setData(mTableEntity);
        mGvTable.setNumColumns(mTableEntity.getColumnCount());
        setTableWidth(mTableEntity.getColumnCount());
        mGvTable.setAdapter(mAdapter);
    }

    private void initView() {
        mGvTable = (GridView) findViewById(R.id.table_gv);
        mTablelayout = (LinearLayout) findViewById(R.id.tablelayout);
        mGvTable.setColumnWidth(columnWidth);
    }

    private void setTableWidth(int column) {
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mGvTable.getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            linearParams.width = mGvTable.getRequestedColumnWidth() * column;
        } else {
            linearParams.width = columnWidth * column;
        }

        linearParams.width = linearParams.width < getScreenWidth() ? getScreenWidth() : linearParams.width;
        mGvTable.setLayoutParams(linearParams);
    }

    private void initIntent() {
        mDbName = getIntent().getStringExtra(Constant.INTENT_DATABASE_NAME);
        mTableName = getIntent().getStringExtra(Constant.INTENT_TABLE_NAME);
    }

    private int getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}

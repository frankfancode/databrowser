package frankfancode.databrowser.sqlite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import frankfancode.databrowser.sqlite.constants.Constant;
import frankfancode.databrowser.sqlite.db.DBManager;
import frankfancode.databrowser.sqlite.R;
import frankfancode.databrowser.sqlite.adapter.TableAdapter;

public class TableListActivity extends AppCompatActivity {

    private String mDatabaseName;
    private List<String> mTableList;
    private TableAdapter mAdapter;
    private ListView mLvTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        mLvTable = (ListView) findViewById(R.id.lv_table);
        mDatabaseName = getIntent().getStringExtra(Constant.INTENT_DATABASE_NAME);
        mTableList = DBManager.getInstance().getTableList(mDatabaseName);
        mAdapter=new TableAdapter();
        mAdapter.setData(mTableList);
        mLvTable.setAdapter(mAdapter);
        mLvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TableListActivity.this,TableDataActivity.class);
                intent.putExtra(Constant.INTENT_DATABASE_NAME,mDatabaseName);
                intent.putExtra(Constant.INTENT_TABLE_NAME,mTableList.get(position));
                startActivity(intent);
            }
        });
    }
}

package frankfancode.databrowser.sqlite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import frankfancode.databrowser.sqlite.Constant;
import frankfancode.databrowser.sqlite.DBAdapter;
import frankfancode.databrowser.sqlite.DBManager;
import frankfancode.databrowser.sqlite.R;

public class DbListActivity extends AppCompatActivity {

    private ListView mLvDb;
    private DBAdapter mDbAdapter;
    private List<String> mDatabaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_list);
        mLvDb = (ListView) findViewById(R.id.lv_db);
        mDbAdapter = new DBAdapter();
        DBManager.getInstance().init(getApplicationContext());
        mDatabaseList = DBManager.getInstance().getDBList();
        mDbAdapter.setDbs(mDatabaseList);
        mLvDb.setAdapter(mDbAdapter);
        mLvDb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DbListActivity.this, TableListActivity.class);
                intent.putExtra(Constant.INTENT_DATABASE_NAME, mDatabaseList.get(position));
                startActivity(intent);
            }
        });
    }
}

package frankfancode.databrowser.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by frank on 17-3-11.
 */

public class DBAdapter extends BaseAdapter {
    private List<String> dbs;

    public void setDbs(List<String> dbs) {
        this.dbs = dbs;
    }

    @Override
    public int getCount() {
        return dbs == null ? 0 : dbs.size();
    }

    @Override
    public Object getItem(int position) {
        return dbs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_database, parent, false);
        }
        TextView textview = (TextView) convertView.findViewById(R.id.tv_name);
        textview.setText(dbs.get(position));
        return textview;
    }
}

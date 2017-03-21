package frankfancode.databrowser.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import frankfancode.databrowser.sqlite.R;
import frankfancode.databrowser.sqlite.entity.TableEntity;


public class TableDataAdapter extends BaseAdapter {
    private TableEntity tableEntity;
    private int columnCount;


    public void setData(TableEntity tableEntity) {
        this.tableEntity = tableEntity;
        columnCount = tableEntity.getColumnCount();

    }

    @Override
    public int getCount() {
        return tableEntity == null ? 0 : tableEntity.getTotalGridCount();
    }

    @Override
    public String getItem(int position) {
        int row = position / columnCount;
        int column = position % columnCount;
        if (row<1){
            return tableEntity.titles[column];
        }else {
            return tableEntity.tableData[row-1][column];
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
        }
        TextView textview = (TextView) convertView.findViewById(R.id.tv_grid);
        textview.setText(getItem(position));
        return textview;
    }
}

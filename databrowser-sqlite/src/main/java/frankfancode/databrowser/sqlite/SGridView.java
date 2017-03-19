package frankfancode.databrowser.sqlite;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义gridview，解决ScrollView中嵌套gridview显示不正常的问题（1行）
 */
public class SGridView extends GridView {
    public SGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SGridView(Context context) {
        super(context);
    }

    public SGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
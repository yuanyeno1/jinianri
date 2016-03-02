package examplecom.yuanye.com.jinianri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import examplecom.yuanye.com.jinianri.R;
import examplecom.yuanye.com.jinianri.utils.DataConfigUtil;

/**
 * Created by weizhenhua on 2016/2/19.
 */
public class HomeListAdapter extends BaseAdapter {

    private List<String> items;
    private Context context;

    public HomeListAdapter(Context context) {
        this.items = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_home_item, null);
            viewHolder = new ViewHolder();

            viewHolder.info = (TextView) convertView.findViewById(R.id.home_info_tv);
            viewHolder.days = (TextView) convertView.findViewById(R.id.home_days_tv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.info.setText("自2016年2月7日，我们在一起");
        viewHolder.days.setText(DataConfigUtil.getDays() + "");

        return convertView;
    }

    static class ViewHolder {
        TextView info, days;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}

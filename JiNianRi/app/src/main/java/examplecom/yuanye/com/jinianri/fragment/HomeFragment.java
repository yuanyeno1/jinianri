package examplecom.yuanye.com.jinianri.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import examplecom.yuanye.com.jinianri.R;
import examplecom.yuanye.com.jinianri.adapter.HomeListAdapter;

/**
 * Created by weizhenhua on 2016/2/18.
 */
public class HomeFragment extends Fragment{

    private View view;
    private ListView listView;
    private List<String> items;
    private HomeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new HomeListAdapter(getActivity());
        initView();
        porPost();
    }

    private void porPost() {
        items = new ArrayList<>();

        for (int i = 0;i < 10;i++){
            items.add("123");
        }
        adapter.setData(items);
        listView.setAdapter(adapter);

    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.home_listview);
        listView.setDividerHeight(0);
    }
}

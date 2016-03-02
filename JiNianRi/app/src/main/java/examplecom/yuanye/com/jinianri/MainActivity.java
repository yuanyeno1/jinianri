package examplecom.yuanye.com.jinianri;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

import examplecom.yuanye.com.jinianri.base.NoScrollViewPager;
import examplecom.yuanye.com.jinianri.bean.HomeBean;
import examplecom.yuanye.com.jinianri.fragment.BirthFragment;
import examplecom.yuanye.com.jinianri.fragment.FindFragment;
import examplecom.yuanye.com.jinianri.fragment.HomeFragment;
import examplecom.yuanye.com.jinianri.utils.PopwindowUtils;
import examplecom.yuanye.com.jinianri.widget.SystemBarTintManager;

public class MainActivity extends AppCompatActivity{

    protected SystemBarTintManager tintManager;
    private RadioGroup radioGroup;
    private NoScrollViewPager viewPager;
    private TextView title_tv;
    private List<Fragment> fragments;
    private ImageView imageView;
    private int index = 0;
    private PopwindowUtils mPopwindowUtils;
    DbUtils db;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_main);

        //设置沉浸式状态栏
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.maincolor);

        initView();
       // initDataBase();
       // porPost();
        initFragment();
        initAdapter();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                imageView.setVisibility(View.VISIBLE);

                switch (checkedId) {
                    case R.id.menu_home:
                        index = 0;
                        title_tv.setText("纪念日");
                        break;
                    case R.id.menu_birth:
                        index = 1;
                        title_tv.setText("生日管理");
                        break;
                    case R.id.menu_find:
                        index = 2;
                        imageView.setVisibility(View.GONE);
                        title_tv.setText("发现");
                        break;
                }
                viewPager.setCurrentItem(index);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(index){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void porPost() {

        for (int i = 0;i < 10;i++){
            HomeBean bean = new HomeBean();
            bean.setTitle("123");
            bean.setDate("123");
            try {
                db.save(bean);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    private void initDataBase() {
        //创建数据库
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(MainActivity.this);
        config.setDbName("hometime"); //db名
        config.setDbVersion(1);  //db版本
        db = DbUtils.create(config);//db还有其他的一些构造方法，比如含有更新表版本的监听器的
        try {
            db.createTableIfNotExist(HomeBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initAdapter() {

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

    }

    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new BirthFragment());
        fragments.add(new FindFragment());
    }

    private void initView() {

        title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText("纪念日");
        imageView = (ImageView) findViewById(R.id.right_img);
        viewPager = (NoScrollViewPager) findViewById(R.id.main_viewpager);
        radioGroup = (RadioGroup) findViewById(R.id.menu_tab);

        setImage((RadioButton) findViewById(R.id.menu_home), R.drawable.tab_home_bg);
        setImage((RadioButton) findViewById(R.id.menu_birth), R.drawable.tab_birth_bg);
        setImage((RadioButton) findViewById(R.id.menu_find), R.drawable.tab_find_bg);

    }

    /**
     * 设置图片大小
     *
     * @param radioButton
     */
    public void setImage(RadioButton radioButton, int id) {
        Drawable drawable = getResources().getDrawable(id);
        drawable.setBounds(0, 0, dip2px(MainActivity.this, 30)
                , dip2px(MainActivity.this, 30));// 第一0是距左边距离，第二0是距上边距离，40分别是长宽
        radioButton.setCompoundDrawables(null, drawable, null, null);
       // radioButton.setCompoundDrawablePadding(dip2px(MainActivity.this, 10));
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://examplecom.yuanye.com.jinianri/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://examplecom.yuanye.com.jinianri/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

package com.example.haier.sheji;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.haier.sheji.find.FindFragment;
import com.example.haier.sheji.homepager.HomePagerFragment;
import com.example.haier.sheji.homepager.hudong.HuDongFragment;
import com.example.haier.sheji.myself.MySelfFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private long exitTime=0;

    private RadioGroup radioGroup;
    private FragmentManager manager;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        manager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.activity_home_radiogroup);

        initFragment(); //进行Fragment实例化

        //需要手动的将 发现的Fragment添加到 容器中
        radioGroup.setOnCheckedChangeListener(this);
        //默认第一个是选中
        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);


    }

    private void initFragment() {

        fragments = new Fragment[4];

        HomePagerFragment homePagerFragment = new HomePagerFragment();
        fragments[0]=homePagerFragment;

        FindFragment find = new FindFragment();
        fragments[1] = find;

        HuDongFragment zhuBaoFragment = new HuDongFragment();
        fragments[2]=zhuBaoFragment;

        MySelfFragment mySelfFragment = new MySelfFragment();
        fragments[3]=mySelfFragment;

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode== KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){

             if(System.currentTimeMillis()-exitTime>2000){

                 Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                 exitTime=System.currentTimeMillis();
             }else{

                 finish();
                 System.exit(0);
             }
            return  true;
        }

        return false;

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        //add/repalce -->事务-->FragmentManger
        FragmentTransaction fragmentTranscation=manager.beginTransaction();

        switch (checkedId){

            case  R.id.activity_home_rb_home:
                //切换到发现
                fragmentTranscation.replace(R.id.activity_home_framelayout,fragments[0]);
                break;

            case R.id.activity_home_rb_find:
                //切换到订阅
                fragmentTranscation.replace(R.id.activity_home_framelayout,fragments[1]);
                break;

            case  R.id.activity_home_rb_zhubao:
                //切换到下载
                fragmentTranscation.replace(R.id.activity_home_framelayout,fragments[2]);
                break;

            case  R.id.activity_home_rb_my:
                //切换到我的
                fragmentTranscation.replace(R.id.activity_home_framelayout,fragments[3]);

        }

        fragmentTranscation.commit();//提交事务
    }
}

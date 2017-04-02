package com.example.haier.sheji;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private long exitTime=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
}

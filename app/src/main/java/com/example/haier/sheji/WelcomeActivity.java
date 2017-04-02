package com.example.haier.sheji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends BaseActivity{

    @InjectView(R.id.activity_welcome_viewpager)
    ViewPager activityWelcomeViewpager;
    @InjectView(R.id.activity_welcome_image_point1)
    ImageView activityWelcomeImagePoint1;
    @InjectView(R.id.activity_welcome_image_point2)
    ImageView activityWelcomeImagePoint2;
    @InjectView(R.id.activity_welcome_image_point3)
    ImageView activityWelcomeImagePoint3;
    @InjectView(R.id.activity_welcome_image_point4)
    ImageView activityWelcomeImagePoint4;

    private int[] imags={R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3,R.mipmap.guide_4};
    private List<ImageView> listPoints;
    private List<ImageView> data;
    private WecomePagerAdapter welcomePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);

        data=new ArrayList<>();
        listPoints=new ArrayList<>();
        listPoints.add(activityWelcomeImagePoint1);
        listPoints.add(activityWelcomeImagePoint2);
        listPoints.add(activityWelcomeImagePoint3);
        listPoints.add(activityWelcomeImagePoint4);
        listPoints.get(0).setSelected(true);

        ininData();
        welcomePagerAdapter=new WecomePagerAdapter(data);
        activityWelcomeViewpager.setAdapter(welcomePagerAdapter);


      activityWelcomeViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

          }

          @Override
          public void onPageSelected(int position) {

              for (int i = 0; i < listPoints.size(); i++) {
                  listPoints.get(i).setSelected(i==position);
              }

              if(position==listPoints.size()-1){

                  new Timer().schedule(new TimerTask() {
                      @Override
                      public void run() {
                          startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                          finish();
                      }
                  },1000);

              }

          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });

    }

    private void ininData() {

        for (int i = 0; i < imags.length; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY)                           ;
            imageView.setImageResource(imags[i]);
            data.add(imageView);
        }
    }



}

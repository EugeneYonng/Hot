package com.hotdoor.products.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;

public class StartActivity extends Activity {
    private  ViewPager startPager;
    private float xPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        startPager = (ViewPager)this.findViewById(R.id.start_pager);
        startPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object obj) {
                container.removeView((View) obj);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                TextView text = new TextView(StartActivity.this);
                text.setGravity(Gravity.CENTER);
                text.setTextSize(30);
                text.setTextColor(Color.WHITE);
                text.setText("Page " + position);
                text.setPadding(30, 30, 30, 30);
                int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64,
                        (int) Math.floor(Math.random() * 128) + 64);
                text.setBackgroundColor(bg);
                container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                return text;
            }

        });

        startPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(startPager.getCurrentItem() == 2) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            xPosition = event.getX();
                            break;
                        case MotionEvent.ACTION_UP:
                            if(event.getX() < xPosition) {
                                Intent intent = new Intent(MainActivity.ACTION_MAINACTIVITY);
                                StartActivity.this.startActivity(intent);
                                finish();
                            }
                    }
                }

                return false;
            }
        });

        CircleIndicator startIndicator=(CircleIndicator)findViewById(R.id.start_indicator);
        startIndicator.setViewPager(startPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

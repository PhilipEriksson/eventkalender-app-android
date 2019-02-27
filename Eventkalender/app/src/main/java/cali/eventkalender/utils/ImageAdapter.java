package cali.eventkalender.utils;


import android.content.Context;

import android.support.v4.view.PagerAdapter;

import android.support.v4.view.ViewPager;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ImageView;

import cali.eventkalender.R;



public class ImageAdapter extends PagerAdapter {

    Context context;

/**  När man väl har unika bilder för evenemang och nationer så måste någon parameter skickas in här för att välja bilder, eller
 * en metod i den här klassen. */
    private int[] GalImages = new int[] {

         R.mipmap.malmo,
         R.mipmap.malmo_3,
         R.mipmap.malmo_2

    };

    public ImageAdapter(Context context){

        this.context=context;

    }

    @Override
    public int getCount() {

        return GalImages.length;

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == ((ImageView) object);

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);

        imageView.setImageResource(GalImages[position]);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        ((ViewPager) container).addView(imageView, 0);

        return imageView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ImageView) object);

    }

}
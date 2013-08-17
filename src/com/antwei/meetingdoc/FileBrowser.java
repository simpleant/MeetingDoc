/**
 * 
 */
package com.antwei.meetingdoc;

import java.lang.reflect.Field;

import com.antwei.meetingdoc.adapter.SectionsPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.TextView;

/**
 * @author antwei
 * 
 */
public class FileBrowser extends FragmentActivity {

	private SectionsPagerAdapter mSectionsPagerAdapter;

	private PagerTitleStrip mPagerTitleStrip;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	private void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.file_browser_main);
		mPagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
		try {
			Field field=mPagerTitleStrip.getClass().getDeclaredField("mCurrText");
			field.setAccessible(true);
			TextView view=(TextView) field.get(mPagerTitleStrip);
			view.setTextSize(18f);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
	}

}

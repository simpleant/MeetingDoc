package com.antwei.meetingdoc.adapter;

import com.antwei.meetingdoc.fragment.DocListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		
		return DocListFragment.newInstance(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		switch(position){
		case 0:
			return "原文件";
		case 1:
			return "已阅件";
		}
		return super.getPageTitle(position);
	}

}

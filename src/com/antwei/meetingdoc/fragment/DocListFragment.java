package com.antwei.meetingdoc.fragment;

import java.io.File;
import java.util.ArrayList;

import com.antwei.meetingdoc.R;
import com.antwei.meetingdoc.adapter.DocListAdapter;
import com.antwei.meetingdoc.service.ScanSD;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class DocListFragment extends ListFragment {

	private Context context;
	private DocListAdapter docListAdapter;
	private ArrayList<File> coll;
	private String[] filePath = { "/MeetingDoc", "/Office_Docs" };
	private ScanSD scanSD;
	private File mFile;
	private int mNum;

	public static DocListFragment newInstance(int position) {
		DocListFragment mListFragment = new DocListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("path", position);
		mListFragment.setArguments(bundle);
		return mListFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.context = getActivity().getBaseContext();
		mNum = getArguments() != null ? getArguments().getInt("path") : 1;
		System.out.println("mNum:"+mNum);
		if(scanSD == null){
			scanSD = new ScanSD();
		}
		switch (mNum) {
		case 0:
			mFile = new File(scanSD.getRootPath()+filePath[0]);
			System.out.println("mFile:"+mFile.toString());
			coll = scanSD.findFile(mFile, null);
			System.out.println("coll.size:"+coll.size());
			break;
		case 1:
			mFile = new File(scanSD.getRootPath()+filePath[1]);
			System.out.println("mFile:"+mFile.toString());
			coll = scanSD.findFile(mFile, null);
			System.out.println("coll.size:"+coll.size());
			break;
		}
//		mFile = new File(scanSD.getRootPath()+filePath[0]);
//		coll = scanSD.findFile(mFile, null);
		
		docListAdapter = new DocListAdapter(context,coll);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View docPagerList = inflater.inflate(R.layout.doc_list_main, container,
				false);
		return docPagerList;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListAdapter(docListAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String curpath = l.getItemAtPosition(position).toString();
		Toast.makeText(context, curpath, Toast.LENGTH_LONG).show();
		openFile(new File(curpath));
	}
	
	private void openFile(File file) {
		// TODO Auto-generated method stub
			ComponentName cName = new ComponentName("com.yozo.office", "emo.main.MainAppProxy");
			Intent intent = new Intent();
			intent.setComponent(cName);
			intent.setData(Uri.fromFile(file));
			startActivity(intent);
	}
}

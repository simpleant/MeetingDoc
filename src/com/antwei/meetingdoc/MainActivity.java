package com.antwei.meetingdoc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.antwei.meetingdoc.adapter.DocListAdapter;
import com.antwei.meetingdoc.service.ScanSD;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ScanSD scanSD;
	private ListView lvDocList;
	private ArrayList<File> docArray;
	private DocListAdapter docAdapter;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initall();
		lvDocList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

//				System.out.println(parent.getItemAtPosition(position).toString());
				String path = parent.getItemAtPosition(position).toString();
//				String path = (String) item.get("ItemTitle");
				System.out.println(path);
				File file = new File(path);
//				Intent intent = new Intent(Intent.ACTION_VIEW);
				openFile(file);
				
			}

			
		});
	
	}
	
	
	private void initall() {
		// TODO Auto-generated method stub
		
		ActionBar actionBar = getActionBar();
		Resources barRes = getResources();
		Drawable myDrawable = barRes.getDrawable(R.drawable.bg_actionbar);
		actionBar.setBackgroundDrawable(myDrawable);
		
		scanSD = new ScanSD();
		lvDocList = (ListView)findViewById(R.id.lv_doclist);
//		docArray = new ArrayList<File>();
		docArray = scanSD.getFileList("doc","docx");
		
//		final SimpleAdapter adpDocList = new SimpleAdapter(getBaseContext(),scanSD.getMapData(docArray),R.layout.doc_list,
//				new String[]{"ItemText"},new int[]{R.id.tv_doc});
		docAdapter = new DocListAdapter(getBaseContext(),docArray);
		lvDocList.setAdapter(docAdapter);
		
	}


	private void openFile(File file) {
		// TODO Auto-generated method stub
			ComponentName cName = new ComponentName("com.yozo.office", "emo.main.MainAppProxy");
			Intent intent = new Intent();
			intent.setComponent(cName);
			intent.setData(Uri.fromFile(file));
			startActivity(intent);
	}
	
	protected void openFile(File file, Intent intent) {
		if(file.getName().endsWith(".txt")){
			intent.setDataAndType(Uri.fromFile(file), "text/plain");
			startActivity(intent);
		}else if(file.getName().endsWith(".doc")||file.getName().endsWith(".docx")){
//			intent.setDataAndType(Uri.fromFile(file), "emo.main.MainAppProxy");
			ComponentName cName = new ComponentName("emo.main", "emo.main.MainAppProxy");
//			intent.setClassName(getBaseContext(), "emo.main.MainAppProxy");
			intent.setData(Uri.fromFile(file));
			intent.setComponent(cName);
			startActivity(intent);
		}
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

package com.antwei.meetingdoc.adapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.antwei.meetingdoc.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DocListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<File> coll;
	private LayoutInflater mInflater;

	public DocListAdapter(Context context, ArrayList<File> coll) {
		this.coll = coll;
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return coll.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return coll.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		File file = coll.get(position);
		long date = file.lastModified();
		long size = file.length()/1024;
		String filePath = file.toString();
		
		String fileName = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.doc_list, null);
			viewHolder = new ViewHolder();
			viewHolder.docImage = (ImageView)convertView.findViewById(R.id.imgv_doctype);
			viewHolder.docName = (TextView)convertView.findViewById(R.id.tv_doc);
			viewHolder.docDate = (TextView)convertView.findViewById(R.id.tv_docdate);
			viewHolder.docSize = (TextView)convertView.findViewById(R.id.tv_docsize);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		if(fileName.endsWith(".doc")){
			viewHolder.docImage.setBackgroundResource(R.drawable.img_doc);
		}else if(fileName.endsWith(".docx")){
			viewHolder.docImage.setBackgroundResource(R.drawable.img_docx);
		}
		viewHolder.docName.setText(fileName);
		viewHolder.docDate.setText(getDate(date));
		viewHolder.docSize.setText(size+"k");
		return convertView;
	}

	private static class ViewHolder {
		public ImageView docImage;
		public TextView docName;
		public TextView docDate;
		public TextView docSize;
	}
	
	private String getDate(long date){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat day = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		calendar.setTimeInMillis(date);
		
		return day.format(calendar.getTime());
	}

}

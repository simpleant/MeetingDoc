package com.antwei.meetingdoc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Environment;

public class ScanSD {

	private String[] FILETYPES = { "doc", "docx" };
	private ArrayList<File> list;
	private String mPath;
	private String rootPath;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public ScanSD(String path, String... fileType) {
		this.FILETYPES = fileType;
		list = new ArrayList<File>();
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			mPath = Environment.getExternalStorageDirectory().getPath() + "/"
					+ path;
		}
		File folder = new File(mPath);
		if (folder == null || !folder.exists()) {
			folder.mkdir();
		}

	}

	public ScanSD() {
		list = new ArrayList<File>();
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			rootPath = Environment.getExternalStorageDirectory().getPath();
		}
		

	}

	public ArrayList<Map<String, Object>> getMapData(ArrayList<File> list) {
		ArrayList<Map<String, Object>> mapData = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> item;
		int i = 0;
		String path;
		String name;
		for (i = 0; i < list.size(); i++) {
			item = new HashMap<String, Object>();
			path = list.get(i).toString();
			name = path.substring(path.lastIndexOf("/") + 1, path.length());
			item.put("ItemText", name);
			item.put("ItemTitle", path);
			mapData.add(item);
		}
		return mapData;
	}

	public ArrayList<File> FindFile(File file, String... keySearch) {

		ArrayList<File> list = new ArrayList<File>();

		if (null == file) {
			file = new File(mPath);
		}
		if (file.isDirectory()) {

			File[] all_file = file.listFiles();

			if (all_file != null) {

				for (File tempf : all_file) {

					if (tempf.isDirectory()) {

						list.addAll(FindFile(tempf, keySearch));

					}

					else

					{
						for (String nowKey : keySearch) {
							if (tempf.getName().toLowerCase().endsWith(nowKey)) {

								list.add(tempf);
								break;

							}
						}

					}

				}

			}

		}

		return list;

	}

	protected void getAllFiles(File file, String... fileType) {

		if (fileType == null) {
			fileType = FILETYPES;
		}
		if (file == null) {
			File folder = new File(mPath);
			if (folder == null || !folder.exists())
				folder.mkdir();
		} else {
			File files[] = file.listFiles();
			if (files != null) {
				for (File f : files) {

					if (f.isDirectory()) {
						getAllFiles(f, fileType);
					} else {
						for (String curFile : fileType) {
							if (f.getName().indexOf(curFile) > 0) {
								this.list.add(f);
							}
						}

					}
				}
			}
		}

	}

	public ArrayList<File> findFile(File file, String... keyType) {
		ArrayList<File> list = new ArrayList<File>();

		if (null == file) {
			file = new File(mPath);
		}
		File folder = file;
		if (folder == null || !folder.exists()) {
			folder.mkdir();
		}

		if (keyType == null) {
			keyType = FILETYPES;
		}

		if (file.isDirectory()) {
			File[] curFiles = file.listFiles();
			if (curFiles != null) {
				for (File temp : curFiles) {
					list.addAll(findFile(temp, keyType));
				}
			}
		} else {
			for (String curFielType : keyType) {
				if (file.getName().lastIndexOf(curFielType) > 0) {
					list.add(file);
					break;
				}

			}
		}
		return list;
	}

	public ArrayList<File> getFileList(String... fileType) {
		this.list = FindFile(null, fileType);
		return list;
	}

}
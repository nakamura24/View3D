/* 
 * FileListActivity.java
 * 
 * Copyright (C) 2014 M.Nakamura
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 *  http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */
package jp.example.view3d;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FileListActivity extends Activity {
	private static String Tag = "FileListActivity";
	private ArrayList<String> entryValues = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(Tag, "onCreate");
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_file_list);

			// SDカードのFileを取得
			File file = Environment.getExternalStorageDirectory();
			Log.d(Tag, "getAbsolutePath()=" + file.getAbsolutePath());
			getSubDir(file.getAbsolutePath());
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_single_choice);
			for (String object : entryValues) {
				adapter.add(object);
			}
			ListView listView1 = (ListView) findViewById(R.id.listView1);
			listView1.setAdapter(adapter);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	private void getSubDir(String subdir) {
		Log.i(Tag, "getSubDir - " + subdir);
		try {
			if (subdir == null)
				return;
			File subDir = new File(subdir);
			String subFileNames[] = subDir.list();
			if (subFileNames == null)
				return;
			for (String subFileName : subFileNames) {
				File subFile = new File(subDir + "/" + subFileName);
				if (subFile.isDirectory()) {
					getSubDir(subDir + "/" + subFileName);
				} else if (subFile.getName().toLowerCase(Locale.getDefault())
						.endsWith("jpg")
						|| subFile.getName().toLowerCase(Locale.getDefault())
								.endsWith("png")) {
					Log.d(Tag, "subFile - " + subFile);
					if (!subDir.getAbsolutePath().contains("/.")) {
						entryValues.add(subDir + "/" + subFile.getName());
					}
				}
			}
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	public void onButtonClick(View view) {
		Log.i(Tag, "onButtonClick");
		try {
			ListView listView1 = (ListView) findViewById(R.id.listView1);
			int position = listView1.getCheckedItemPosition();
			Log.d(Tag, "position - " + position);
			Intent intent = new Intent();
			if (position != AdapterView.INVALID_POSITION
					&& entryValues.size() > 0) {
				intent.putExtra("file", entryValues.get(position));
			}
			setResult(RESULT_OK, intent);
			finish();
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}
}

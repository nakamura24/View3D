/* Copyright (C) 2014 M.Nakamura
 *
 * This software is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 2.1 Japan License.
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://creativecommons.org/licenses/by-nc-sa/2.1/jp/legalcode
 */
package jp.example.view3d;

import static jp.example.view3d.Constant.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final String Tag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(Tag, "onCreate");
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.i(Tag, "onActivityResult");
		try {
			switch (requestCode) {
			case ACTIVITY_FILE_LEFT:
				Log.d(Tag, "ACTIVITY_FILE_LEFT");
				String left = intent.getStringExtra("file");
				Bitmap leftImage = BitmapFactory.decodeFile(left);
				ImageView imageView_left = (ImageView) findViewById(R.id.imageView_left);
				imageView_left.setImageBitmap(leftImage);
				break;
			case ACTIVITY_FILE_RIGHT:
				Log.d(Tag, "ACTIVITY_FILE_RIGHT");
				String right = intent.getStringExtra("file");
				Bitmap rightImage = BitmapFactory.decodeFile(right);
				ImageView imageView_right = (ImageView) findViewById(R.id.imageView_right);
				imageView_right.setImageBitmap(rightImage);
				break;
			}
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	public void onLeftClick(View view) {
		Log.i(Tag, "onLeftClick");
		try {
			Intent intent = new Intent(this, FileListActivity.class);
			startActivityForResult(intent, ACTIVITY_FILE_LEFT);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	public void onRightClick(View view) {
		Log.i(Tag, "onRightClick");
		try {
			Intent intent = new Intent(this, FileListActivity.class);
			startActivityForResult(intent, ACTIVITY_FILE_RIGHT);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}
}

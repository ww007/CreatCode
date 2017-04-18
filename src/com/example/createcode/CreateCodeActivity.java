package com.example.createcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.UnsupportedEncodingException;

/**
 * 生成条形码
 * @author ww
 *
 */
public class CreateCodeActivity extends Activity {
	EditText etCodeKey;
	Button btnCreateCode;
	ImageView iv2Code;
	ImageView ivBarCode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_code);

		initView();
	}

	private void initView() {
		etCodeKey = (EditText) findViewById(R.id.et_code_key);
		btnCreateCode = (Button) findViewById(R.id.btn_create_code);
		ivBarCode = (ImageView) findViewById(R.id.iv_bar_code);
		iv2Code = (ImageView) findViewById(R.id.iv_2_code);

		btnCreateCode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String key = etCodeKey.getText().toString();
				if (TextUtils.isEmpty(key)) {
					Toast.makeText(CreateCodeActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
				} else {
					create2Code(key);
					createBarCode(key);
				}
			}
		});

	}

	/**
	 * 生成一维码
	 * 
	 * @param key
	 * @return
	 */
	private Bitmap createBarCode(String key) {
		Bitmap qrCode = null;
		try {
			qrCode = EncodingHandler.createBarCode(key, 600, 300);
			ivBarCode.setImageBitmap(qrCode);
		} catch (Exception e) {
			Toast.makeText(this, "输入的内容一维码不支持！", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return qrCode;
	}

	/**
	 * 生成二维码
	 * 
	 * @param key
	 */
	private Bitmap create2Code(String key) {
		Bitmap qrCode = null;
		try {
			qrCode = EncodingHandler.create2Code(key, 400);
			iv2Code.setImageBitmap(qrCode);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return qrCode;
	}

}

package com.example.s151304064;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String[] PHONES_PROJECTION = new String[] {
		Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, 
		Phone.CONTACT_ID }; 
	private ListView lv;
	private List<Person> persons;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 100:
				Button btn = (Button) findViewById(R.id.btn_getcontent);
				btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO 自动生成的方法存根
						lv.setAdapter(new MyAdapter());
					}
				});
				
				break;
			}
		};
	};
	
	private class MyAdapter extends BaseAdapter{

		private static final String TAG="MyAdapter";
		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return persons.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return persons.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public View getView(int positon, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			Person person = persons.get(positon);
			View view = View.inflate(MainActivity.this,R.layout.list_item, null);
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_name.setText("姓名:"+person.getName());
			TextView tv_phone=(TextView) view.findViewById(R.id.tv_phone);
			tv_phone.setText("电话:"+person.getNumber());
			ImageView ima = (ImageView) view.findViewById(R.id.ima);
			//ima.setImageResource(person.getId().intValue());
			return view;
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lv);
		new Thread(){
			public void run(){
				addData();
				getPersons();
				if(persons.size()>0){
					handler.sendEmptyMessage(100);
				}
			};
		}.start();
	}
	public void addData(){
		PersonDao2 dao = new PersonDao2(this);
		long  number=885900001;
		Random random = new Random();
		for(int i=0;i<10;i++){
			dao.add("wangwu"+i, Long.toString(number+i), random.nextInt(5000));
		}
		
	}
	private void getPersons(){
		String path="Phone.CONTENT_URI";
		Uri uri = Uri.parse(path);
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
		persons = new ArrayList<Person>();
		if(cursor==null){
			return;
		}
		while(cursor.moveToNext()){
			String name = cursor.getString(0); //得到联系人名称
			String number = cursor.getString(1); //得到手机号码
			Long id = cursor.getLong(2); //得到联系人头像ID
			// 头像显示方法：ImageView控件.setImageResource(图像ID)
			/*InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);
			Bitmap bmp_head = BitmapFactory.decodeStream(input); */
			Long photoid = cursor.getLong(3);
			Person p = new Person(id,name,number, photoid);
		
			persons.add(p);
			
		}
		cursor.close();
	}
	
}

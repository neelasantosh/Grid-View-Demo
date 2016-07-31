package com.example.gridviewdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity 
{
	GridView gridViewCartoons;
	ArrayList<Cartoon> listCartoons=new ArrayList<Cartoon>();
	CartoonAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		gridViewCartoons = (GridView) findViewById(R.id.gridView1);
		
		Cartoon c1= new Cartoon("Micky", true,R.drawable.micky);
		Cartoon c2= new Cartoon("Tom", true,R.drawable.tom);
		Cartoon c3= new Cartoon("Pickachu", true,R.drawable.picka);
		Cartoon c4= new Cartoon("Donald", true,R.drawable.donald);
	
		listCartoons.add(c1);
		listCartoons.add(c2);
		listCartoons.add(c3);
		listCartoons.add(c4);
		
		adapter = new CartoonAdapter();
		gridViewCartoons.setAdapter(adapter);
		
		//show image on dialog on grid item click listener
		gridViewCartoons.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				// TODO Auto-generated method stub
				final Dialog dlg= new Dialog(HomeActivity.this);
				dlg.setTitle("Cartoon");
				dlg.setContentView(R.layout.dialog_view);
				//show cartoon info on dialog UI
				Cartoon c=listCartoons.get(arg2);
				dlg.setTitle(c.getTitle());
				
				ImageView imgDialog = (ImageView) dlg.findViewById(R.id.imageView1);
				imgDialog.setImageResource(c.getImageId());
				dlg.setCancelable(true);
				//add listener on close button
				Button closeButton=(Button) dlg.findViewById(R.id.button1);
				closeButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dlg.dismiss();
					}
				});
				dlg.show();
				
			}
		});
	
	}//eof activity
	
	class CartoonAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listCartoons.size();
		}

		@Override
		public Cartoon getItem(int position) {
			// TODO Auto-generated method stub
			return listCartoons.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater inflate = getLayoutInflater();
			View itemView = inflate.inflate(R.layout.item_view, null);	
			
			Cartoon c= listCartoons.get(position);
			ImageView imageViewCartoon = (ImageView) itemView
					.findViewById(R.id.imageView1);

			imageViewCartoon.setImageResource(c.getImageId());
			
			TextView textTitle = (TextView) itemView.findViewById(R.id.textView1);
			
			textTitle.setText(c.getTitle());
			
			
			return itemView;
		}
		
	}
}

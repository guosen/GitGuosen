package com.joke.android;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.joke.android.entity.JokeContent;
import com.joke.android.entity.JokeWrapper;
import com.joke.android.face.ICallback;
import com.joke.android.face.IUser;
import com.joke.android.face.impl.DefaultRUser;
import com.joke.android.widget.MyListViewAdapter;
import com.joke.android.widget.RefreshListView;


public class RefreshListviewActivity extends Activity implements RefreshListView.IOnRefreshListener,
															RefreshListView.IOnLoadMoreListener{
    /** Called when the activity is first created. */

	private RefreshListView mListView;
	private List<JokeContent> data=new ArrayList<JokeContent>() ;
	private MyListViewAdapter adapter;
	private RefreshDataAsynTask mRefreshAsynTask;
	private LoadMoreDataAsynTask mLoadMoreAsynTask;
	
	
	
	private ImageView newJoke;
	private IUser user;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initView(); 
        
        initData();
    }
    
    
    
    
    
    public void initView()
    {   	
    	mListView = (RefreshListView) findViewById(R.id.listView);
    	user=new DefaultRUser(this);
    	
    	newJoke=(ImageView) findViewById(R.id.header_share);
    	
    	newJoke.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RefreshListviewActivity.this, NewJokeActivity.class);
				startActivity(intent);
			}
		});
    }
    
    ICallback<JokeWrapper> callback=new ICallback<JokeWrapper>() {
		
		@Override
		public void onSucceed(JokeWrapper result) {
			// TODO Auto-generated method stub
			data =result.getJokelist();
			adapter.notifyDataSetChanged();
			
		}
		
		@Override
		public void onFail(String error) {
			// TODO Auto-generated method stub
			
		}
	};
    public void initData()
    {
		user.JokeContent(this, callback);
		/*data = new LinkedList<String>();
		String string = "课上老师问小明，面对江山和美人，你选……？小明：江山老师：为什么？小明：有了江山，就一定会有美人。但是有了美人，美人会跟有江山的人跑了。。。小伙子，你很有思想。。。";
		for(int i = 0; i < 6; i++)
		{
			string = "笑话："+i+":"+string;
			data.addFirst(string);
		}
		*/
		adapter = new MyListViewAdapter(this, data);
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(this);
		mListView.setOnLoadMoreListener(this);
	
    }

   private  void ReloadData(){
	   
	   user.JokeContent(this, callback);
	   
   }



	@Override
	public void OnRefresh() {
		// TODO Auto-generated method stub
	
		mRefreshAsynTask = new RefreshDataAsynTask();
		mRefreshAsynTask.execute();
	}
	
	

	@Override
	public void OnLoadMore() {
		// TODO Auto-generated method stub
		mLoadMoreAsynTask = new LoadMoreDataAsynTask();
		mLoadMoreAsynTask.execute();
	}
	
	
	
	
	private int index = 5;
	
	class RefreshDataAsynTask extends AsyncTask<Void , Void, Void>
	{

		@Override
		protected Void doInBackground(Void... arg0) {
			
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			ReloadData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
		
			adapter.refreshData(data);
			mListView.onRefreshComplete();
		}
		
		
		
	}

	
	private int pos = 0;
	
	class LoadMoreDataAsynTask extends AsyncTask<Void , Void, Void>
	{

		@Override
		protected Void doInBackground(Void... arg0) {
			
			
			
			/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pos++;
			data.add("talent" + pos);
			*/
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
		
			adapter.refreshData(data);
			
			if (pos > 5)
			{
				mListView.onLoadMoreComplete(true);
			}else{
				mListView.onLoadMoreComplete(false);
			}
			
		}
		
		
		
	}

	
	
	
}
package com.lin.pulltorefresh;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.lin.mr.androidnewtec.R;

import java.util.Arrays;
import java.util.LinkedList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PullToRefreshListActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView pullRefreshList;
    private LinkedList<String> mListItems;
    private ArrayAdapter<String> mAdapter;

    private String[] mStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptr_list);
        ButterKnife.bind(this);
        tvTitle.setText("PullToRefreshListActivity");

        // Set a listener to be invoked when the list should be refreshed.
        pullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                Toast.makeText(PullToRefreshListActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();

                new GetDataTask().execute();
            }

            /**
             * 上拉刷新
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                Toast.makeText(PullToRefreshListActivity.this, "上拉刷新!", Toast.LENGTH_SHORT).show();
                new GetDataTask().execute();
            }

        });

        // Add an end-of-list listener
        //设置监听最后一条
        pullRefreshList.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(PullToRefreshListActivity.this, "滑动到最后一条了!", Toast.LENGTH_SHORT).show();
            }
        });

        //得到ListView
        ListView listview = pullRefreshList.getRefreshableView();


        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));

        //创建适配器
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

        /**
         * Add Sound Event Listener
         * 添加刷新事件并且发出声音
         */
        SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(this);
        soundListener.addSoundEvent(PullToRefreshBase.State.PULL_TO_REFRESH, R.raw.pull_event);
        soundListener.addSoundEvent(PullToRefreshBase.State.RESET, R.raw.reset_sound);
        soundListener.addSoundEvent(PullToRefreshBase.State.REFRESHING, R.raw.refreshing_sound);
        pullRefreshList.setOnPullEventListener(soundListener);

        // You can also just use setListAdapter(mAdapter) or
        // mPullRefreshListView.setAdapter(mAdapter)
        //设置适配器
        listview.setAdapter(mAdapter);
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (pullRefreshList.getMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                //下拉刷新
                mListItems.addFirst("刷新请求到的新数据...");
            } else if (pullRefreshList.getMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                mListItems.addLast("上拉数据请求到了...");
            }

            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            pullRefreshList.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
}

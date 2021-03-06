package com.ifeimo.im.activity.fragment;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ifeimo.im.IMSdk;
import com.ifeimo.im.R;
import com.ifeimo.im.common.adapter.CacheMsgListAdapter;
import com.ifeimo.im.common.adapter.base.CommonAdapter;
import com.ifeimo.im.common.adapter.base.ViewHolder;
import com.ifeimo.im.common.bean.CacheMainMsgItem;
import com.ifeimo.im.common.bean.UserBean;
import com.ifeimo.im.provider.CacheMsgListProvide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpds on 2017/1/17.
 */
public class ContractFragment extends BaseFragment {
    private ListView listView;
    private List<CacheMainMsgItem> list = new ArrayList<>();
    private CacheMsgListAdapter adapter;
    private Loader<Cursor> loader;

    @Override
    protected int getContentViewByID() {
        return R.layout.fragment_contract;
    }

    @Override
    protected void init() {
        adapter = new CacheMsgListAdapter(getActivity());
        listView = (ListView) contentView.findViewById(R.id.contractListview);
        listView.setAdapter(adapter);
        loadData();
    }


    @Deprecated
    private void loadData() {

        loader = getActivity().getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader onCreateLoader(int i, Bundle bundle) {
                return new CursorLoader(getActivity(), CacheMsgListProvide.CONTENT_URI,null,
                        String.format("%s = ?", CacheMsgListProvide.DB_MEMBER_ID),new String[]{UserBean.getMemberID()},null);
            }

            @Override
            public void onLoadFinished(Loader loader, Cursor cursor) {
                adapter.swapCursor(cursor);
            }

            @Override
            public void onLoaderReset(Loader loader) {
                adapter.swapCursor(null);
            }
        });

    }
}

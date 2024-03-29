package com.example.drawturtlescore.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.drawturtlescore.R;

public class PlayerRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PlayerRvAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tvName, item);
    }
}

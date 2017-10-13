package com.zyw.horrarndoo.yizhi.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyw.horrarndoo.sdk.config.DBConfig;
import com.zyw.horrarndoo.sdk.config.ItemState;
import com.zyw.horrarndoo.sdk.utils.DBUtils;
import com.zyw.horrarndoo.sdk.utils.SpUtils;
import com.zyw.horrarndoo.sdk.utils.StringUtils;
import com.zyw.horrarndoo.yizhi.R;
import com.zyw.horrarndoo.yizhi.model.bean.gankio.GankIoCustomItemBean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/13.
 * <p>
 */

public class GankIoCustomAdapter extends BaseCompatAdapter<GankIoCustomItemBean, BaseViewHolder> {
    private String mImageSize = "?imageView2/0/w/200";

    public GankIoCustomAdapter(@LayoutRes int layoutResId, @Nullable List<GankIoCustomItemBean>
            data) {
        super(layoutResId, data);
    }

    public GankIoCustomAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public GankIoCustomAdapter(@Nullable List<GankIoCustomItemBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoCustomItemBean item) {
        if (DBUtils.getDB(mContext).isRead(DBConfig.TABLE_GANKIO_CUSTOM, item.getType() + item
                .get_id(), ItemState.STATE_IS_READ)) {
            helper.setTextColor(R.id.tv_item_title, Color.GRAY);
        } else {
            if (SpUtils.getNightModel(mContext)) {
                helper.setTextColor(R.id.tv_item_title, Color.WHITE);
            } else {
                helper.setTextColor(R.id.tv_item_title, Color.BLACK);
            }
        }

        switch (item.getType()) {
            case "福利":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_welfare);
                break;
            case "Android":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_android);
                break;
            case "iOS":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_ios);
                break;
            case "前端":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_front);
                break;
            case "休息视频":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_video);
                break;
            case "瞎推荐":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuijian);
                break;
            case "拓展资源":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuozhan);
                break;
            case "App":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_app);
                break;
        }

        helper.setText(R.id.tv_item_title, item.getDesc());
        helper.setText(R.id.tv_item_who, StringUtils.isEmpty(item.getWho()) ? "佚名" : item
                .getWho());
        helper.setText(R.id.tv_item_type, item.getType());
        helper.setText(R.id.tv_item_time, item.getCreatedAt().substring(0, 10));

        if (item.getImages() != null) {
            if (item.getImages().size() > 0)
                Glide.with(mContext).load(item.getImages().get(0) + mImageSize)
                        .asBitmap()
                        .into((ImageView) helper.getView(R.id.iv_item_image));
        }
    }
}

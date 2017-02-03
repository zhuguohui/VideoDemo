package com.zhuguohui.videodemo.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuguohui.videodemo.R;


/**
 * Created by Vincent Woo
 * Date: 2016/1/22
 * Time: 17:27
 */
public class ToastUtil {
    private Context mContext;
    private static ToastUtil mInstance;
    private Toast mToast;
    private TYPE type = TYPE.TYPE_WARING;
    private TextView mTextView;

    private enum TYPE {
        TYPE_OK, TYPE_WARING, TYPE_COLLECT, TYPE_CANCEL_COLLECT;
    }

    public static ToastUtil getInstance() {
        return mInstance;
    }

    public static void initialize(Context ctx) {
        mInstance = new ToastUtil(ctx);
    }

    private ToastUtil(Context ctx) {
        mContext = ctx;
    }

    public void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            View view = View.inflate(mContext, R.layout.view_toast, null);
            mTextView = (TextView) view.findViewById(android.R.id.message);
            mToast.setView(view);

        }
        mToast.setText(text);
        int yOffset = 0;
        int xOffset = 0;
        View view = mToast.getView();
        int width = 0;
        int height = 0;
        view.measure(0, 0);
        width = view.getMeasuredWidth();
        height = view.getMeasuredHeight();
        int imageId = R.drawable.ic_waring;
        //根据type设置图片
        switch (type) {
            case TYPE_WARING:
                imageId = R.drawable.ic_waring;
                break;
        }

        Drawable drawable = mContext.getResources().getDrawable(imageId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mTextView.setCompoundDrawables(null, drawable, null, null);
        xOffset = (mContext.getResources().getDisplayMetrics().widthPixels - width) / 2;
        yOffset = (mContext.getResources().getDisplayMetrics().heightPixels - height) / 2;
        mToast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
        mToast.show();
        //还原为默认type
        type = TYPE.TYPE_WARING;
    }

    public ToastUtil ok() {
        type = TYPE.TYPE_OK;
        return this;
    }

    public ToastUtil collect() {
        type = TYPE.TYPE_COLLECT;
        return this;
    }

    public ToastUtil cancelCollect() {
        type = TYPE.TYPE_CANCEL_COLLECT;
        return this;
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}

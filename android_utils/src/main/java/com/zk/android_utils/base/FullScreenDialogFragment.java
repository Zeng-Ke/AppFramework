package com.zk.android_utils.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.zk.android_utils.R;

/**
 * author: ZK.
 * date:   On 2018-06-28.
 */
public class FullScreenDialogFragment extends BaseDialogFragment {


   /* private Builder mBuilder;

    public abstract static class Builder<T extends FullScreenDialogFragment> implements Serializable {

        private boolean cancelable = true;


        public Builder<T> setCancelable(boolean cancel) {
            cancelable = cancel;
            return this;
        }

        public boolean isCancelable() {
            return cancelable;
        }

        public abstract T build();

    }


    public  void setBuilder(Builder builder){
        this.setArguments();
        mBuilder = builder;
    }*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.TranslucentDialogFragment);

    }




}

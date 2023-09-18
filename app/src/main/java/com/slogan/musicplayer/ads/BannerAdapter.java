package com.slogan.musicplayer.ads;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@SuppressWarnings("deprecation")
public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BaseViewHolder> {
    private static final String LOG_TAG = BannerAdapter.class.getSimpleName();

    private static final int MIN_ITEMS_FOR_AD = 2;

    private static final int TYPE_BANNER = 0;
    private static final int TYPE_DATA = 1;

    @NonNull
    private final Context context;

    @Nullable
    private List<String> data;

    public void setData(@Nullable List<String> data) {
        this.data = data;

        notifyDataSetChanged();
    }

    public BannerAdapter(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }

        if (data.size() >= MIN_ITEMS_FOR_AD) {
            return data.size() + 1;
        }

        return data.size();
    }

    @Nullable
    private String getItem(int position) {
        if (data == null) {
            return null;
        }

        if (data.size() >= MIN_ITEMS_FOR_AD) {
            if (position == 0) {
                return null;
            }

            return data.get(position - 1);
        }

        return data.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (data != null && data.size() >= MIN_ITEMS_FOR_AD) {
            if (position == 0) {
                return TYPE_BANNER;
            }
        }

        return TYPE_DATA;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            Banner banner = new Banner(context);
            banner.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            return new BannerViewHolder(banner);
        } else if (viewType == TYPE_DATA) {
            return new DataViewHolder(new TextView(context));
        } else {
            // NOTE this case is impossible
            return new BaseViewHolder(new View(context));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void onBind(@Nullable String item) {
            // none
        }
    }

    static class BannerViewHolder extends BaseViewHolder {
        BannerViewHolder(@NonNull final Banner banner) {
            super(banner);

            banner.setBannerListener(new BannerListener() {
                @Override
                public void onReceiveAd(View view) {

                    Log.v(LOG_TAG, "onReceiveAd");
                }

                @Override
                public void onFailedToReceiveAd(View view) {

                    Log.v(LOG_TAG, "onFailedToReceiveAd: " + banner.getErrorMessage());

                }

                @Override
                public void onImpression(View view) {

                    Log.v(LOG_TAG, "onImpression");
                }

                @Override
                public void onClick(View view) {

                    Log.v(LOG_TAG, "onClick");
                }
            });
        }
    }

    static class DataViewHolder extends BaseViewHolder {
        private final TextView textView;

        DataViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView;
        }

        @Override
        void onBind(@Nullable String item) {
            textView.setText(item);
        }
    }
}

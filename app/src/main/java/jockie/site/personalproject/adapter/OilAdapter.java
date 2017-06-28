package jockie.site.personalproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import jockie.site.personalproject.R;
import jockie.site.personalproject.base.BaseApp;
import jockie.site.personalproject.bean.OilBean;

/**
 * Created by yc on 2017/6/28.
 */

public class OilAdapter extends RecyclerView.Adapter<OilAdapter.ViewHolder>{

    private List<OilBean.Data> mData;

    @Override
    public OilAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oil_adapter, null);
        return new OilAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OilAdapter.ViewHolder holder, int position) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.itemFm.setLayoutParams(layoutParams);
        if(position == 0){
            holder.tvProvice.setText("省份");
            holder.tv0.setText("0号柴油");
            holder.tv93.setText("93号汽油");
            holder.tv95.setText("95汽油");
        }else{
            holder.tvProvice.setText(mData.get(position - 1).getProvince());
            holder.tv0.setText(mData.get(position - 1).getDieselOil0());
            holder.tv93.setText(mData.get(position - 1).getGasoline93());
            holder.tv95.setText(mData.get(position - 1).getGasoline97());
        }

        if(position % 2 == 0){
            holder.llRoot.setBackgroundColor(BaseApp.getContext().getResources().getColor(R.color.bg_color));
        }else{
            holder.llRoot.setBackgroundColor(BaseApp.getContext().getResources().getColor(R.color.divideColor));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null? 1 : mData.size() + 1;
    }

    public void addData(List<OilBean.Data> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private final LinearLayout llRoot;
        private final FrameLayout itemFm;
        private final TextView tvProvice;
        private final TextView tv0;
        private final TextView tv93;
        private final TextView tv95;

        public ViewHolder(View view) {
            super(view);
            llRoot = (LinearLayout) view.findViewById(R.id.item_ll_root);
            itemFm = (FrameLayout) view.findViewById(R.id.item_fm);
            tvProvice = (TextView) view.findViewById(R.id.item_tv_provice);
            tv0 = (TextView) view.findViewById(R.id.item_tv_0);
            tv93 = (TextView) view.findViewById(R.id.item_tv_93);
            tv95 = (TextView) view.findViewById(R.id.item_tv_95);
        }
    }
}

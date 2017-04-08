package com.example.fanglingfeng.sortname;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  SortName 
 *  @包名：    com.example.fanglingfeng.sortname
 *  @文件名:   ContactAdapter
 *  @创建者:   lingfeng
 *  @创建时间:  2017/4/8 17:52
 *  @描述：    TODO
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {
    private static final String TAG = "ContactAdapter";
    List<ContactInfo> mContactInfos = new ArrayList<>();
    private Context context;
    LayoutInflater mInflater;
    public ContactAdapter(Context context,List<ContactInfo> contactInfos){
        this.context = context;
        this.mContactInfos = contactInfos;
        mInflater = LayoutInflater.from(context);
    }
    @Override

    public int getCount() {
        return mContactInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return mContactInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_view, null);
            holder.title = (TextView) view.findViewById(R.id.tv_title);
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ContactInfo contactInfo = mContactInfos.get(i);
        int section = getSectionForPosition(i);
        int startPostion = getPositionForSection(section);
        if (i == startPostion) {
            holder.title.setText((char)section+"");
            holder.title.setVisibility(View.VISIBLE);
        } else {
            holder.title.setVisibility(View.GONE);
        }
        holder.name.setText(contactInfo.getRawName());

        return view;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i1 = 0; i1 < mContactInfos.size(); i1++) {
            if (section == mContactInfos.get(i1)
                                        .getSortLetter()
                                        .charAt(0))
            {
                return i1;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int i) {
        int section = mContactInfos.get(i).getSortLetter().charAt(0);
        return section;
    }
    class ViewHolder{
        public TextView title;
        public TextView name;
    }
    public void updateContactInfoList(List<ContactInfo> contactInfo){
        mContactInfos = contactInfo;
        notifyDataSetChanged();
    }
}

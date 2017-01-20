package com.hhgs.tcw.Apapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhgs.tcw.Activity.InfoActivity;
import com.hhgs.tcw.Activity.InfoListActivity;
import com.hhgs.tcw.R;
import com.like.LikeButton;

/**
 * Created by MFH on 2017/1/5 0005.
 */

public class InfoAdp extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ExpandableListView infoExpdbLv;

    public InfoAdp(Context context, ExpandableListView infoExpdbLv) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.infoExpdbLv = infoExpdbLv;
    }


    private String[] fatherList = new String[]{"土建工程材料", "装饰装修工程材料", "电气工程材料", "给排水工程材料", "消防、燃气工程材料", "采暖、通风空调材料", "建筑仿古工程材料", "园林绿化工程材料", "市政、城市轨道材料", "人工、工程设备租赁"};
    //子视图显示文字
    private String[][] sonList = new String[][]{
            {"黑色及有色金属", "水泥、砖瓦灰砂石及混凝土制品", "成型构件及加工件", "电极及劳保用品等其它材料"},
            {"橡胶、塑料及非金属材料", "五金制品", "木、竹材料及其制品", "玻璃及玻璃制品", "墙砖、地砖、地板、地毯类材料", "装饰石材及石材制品", "墙面、顶棚及屋面饰面材料", "龙骨、龙骨配件", "门窗及楼梯制品", "装饰线条、装饰件、栏杆、扶手及其他", "涂料及防腐、防水材料", "绝热 保温 耐火材料", "声、抗辐射及无损摊上材料"},
            {"灯具、光源", "开关、插座", "保险、绝缘及电热材料", "电缆及光纤光缆", "电气线路敷设材料", "弱电及信息类器材"},
            {"管材", "管件及管道用器材", "阀门", "法兰及其垫片", "洁具及燃气器具"},
            {"消防器材"},
            {"水暖及通风空调器材"},
            {"仿古建筑材料"},
            {"园林绿化"},
            {"道路桥梁专用材料"},
            {"人工", "周转材料及五金工具", "轨道交通专用材料"}
    };


    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return fatherList.length;
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return sonList[groupPosition].length;
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {
        return fatherList[groupPosition];
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sonList[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        FatherViewHolder viewholder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expand_father_item, parent, false);
            viewholder = new FatherViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (FatherViewHolder) convertView.getTag();
        }
        viewholder.nameTV.setText(fatherList[groupPosition]);
        return convertView;
    }

    //【重要】填充二级列表
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        SonViewHolder viewholder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expand_son_item, parent, false);
            viewholder = new SonViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (SonViewHolder) convertView.getTag();
        }
        viewholder.nameTV.setText(sonList[groupPosition][childPosition]);
        viewholder.sonLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, InfoListActivity.class);
//                intent.putExtra("name", sonList[groupPosition][childPosition]);
//                context.startActivity(intent);

                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("name", sonList[groupPosition][childPosition]);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
        int len = getGroupCount();
        for (int i = 0; i < len; i++) {
            if (i != groupPosition) {
                infoExpdbLv.collapseGroup(i);
            }
        }
//        if (oldGroupPosition != -1 && oldGroupPosition != groupPosition) {
//            infoExpdbLv.collapseGroup(oldGroupPosition);
//        }
//        oldGroupPosition = groupPosition;
    }

    public class FatherViewHolder {
        public View content_v;
        public LinearLayout fatherLin;
        public TextView nameTV;

        public FatherViewHolder(View v) {
            this.content_v = v;
            this.nameTV = (TextView) v.findViewById(R.id.expand_father_item_name);
            this.fatherLin = (LinearLayout) v.findViewById(R.id.expand_father_item_spread);
        }
    }

    public class SonViewHolder {
        public View content_v;
        public LinearLayout sonLin;
        public TextView nameTV;

        public SonViewHolder(View v) {
            this.content_v = v;
            this.nameTV = (TextView) v.findViewById(R.id.expand_son_item_name);
            this.sonLin = (LinearLayout) v.findViewById(R.id.expand_son_item_spread);
        }
    }
}
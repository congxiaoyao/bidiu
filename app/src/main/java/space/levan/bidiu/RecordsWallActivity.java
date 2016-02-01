package space.levan.bidiu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import space.levan.bidiu.tool.ToolBarActivity;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public class RecordsWallActivity extends ToolBarActivity {

    private RecyclerView recyclerView;

    private static int[] colors = null;
    private static Drawable[] colorfulDrawables = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_wall);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_img);
        Resources resources = getResources();
        colors = new int[]{
                resources.getColor(R.color.colorYellow),
                resources.getColor(R.color.colorBlue),
                resources.getColor(R.color.colorGreen),
                resources.getColor(R.color.colorRed),
                resources.getColor(R.color.colorPurple)};
        colorfulDrawables = new Drawable[]{
                getTintedOval(colors[0]),
                getTintedOval(colors[1]),
                getTintedOval(colors[2]),
                getTintedOval(colors[3]),
                getTintedOval(colors[4])
        };


        List<RecordBean> records = new ArrayList<>();
        for(int i=0;i<20;i++) {
            RecordBean recordBean = new RecordBean();
            recordBean.year = 2016;
            recordBean.month = 1;
            recordBean.day = 29;
            recordBean.impressions = "观后感观后感观后感观后感" +
                    "观后感观后感观后感观后感观后感观后感观后感观后感";
            recordBean.title = "动画标题"+i;
            recordBean.bitmap = bitmap;
            records.add(recordBean);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecordWallAdapter(this, records));

    }

    @Override
    public void setTitleInfo(TitleInfo info) {
        info.title = "记录墙";
    }

    /**
     * 获取指定颜色的椭圆Drawable
     * @param color
     * @return
     */
    public Drawable getTintedOval(int color) {
        Drawable drawable = DrawableCompat.wrap(getResources().getDrawable(R.drawable.oval));
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    private static class RecordBean {
        int year,month,day;
        String title , impressions;
        Bitmap bitmap;
    }

    private static class RecordHolder extends ViewHolder{

        TextView year , month , title , impressions;
        ImageView imageView;
        View shortLine , longLine , ovalView;

        public RecordHolder(View itemView) {
            super(itemView);
            initViews();
        }

        public void initViews() {
            year = (TextView) itemView.findViewById(R.id.tv_year);
            month = (TextView) itemView.findViewById(R.id.tv_month);
            title = (TextView) itemView.findViewById(R.id.tv_comics_title);
            impressions = (TextView) itemView.findViewById(R.id.tv_impressions);

            imageView = (ImageView) itemView.findViewById(R.id.iv_comics_pic);

            shortLine = itemView.findViewById(R.id.view_line1);
            longLine = itemView.findViewById(R.id.view_line2);
            ovalView= itemView.findViewById(R.id.view_oval);

            itemView.findViewById(R.id.view_clicker).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(itemView.getContext(), RecordDetailActivity.class);
//                    intent.putExtra(RecordDetailActivity.TITLE_NAME, title.getText().toString());
//                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    private static class RecordWallAdapter extends RecyclerView.Adapter<RecordHolder>{

        private LayoutInflater layoutInflater;
        private List<RecordBean> records;

        public RecordWallAdapter(Context context , List<RecordBean> records) {
            this.layoutInflater = LayoutInflater.from(context);
            this.records = records;
        }

        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(viewType, parent, false);
            if (viewType == R.layout.item_record_wall_1) {
                return new RecordHolder(itemView) {
                    @Override
                    public void initViews() {
                        return;
                    }
                };
            }
            return new RecordHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            if(position == 0) return;
            if(position == 1) holder.shortLine.setVisibility(View.INVISIBLE);
            else holder.shortLine.setVisibility(View.VISIBLE);
            position = position -1;
            int index = position%colors.length;
            int mainColor = colors[index];
            int lastColor = colors[index == 0 ? colors.length - 1 : index - 1];
            Drawable drawable = colorfulDrawables[index];
            RecordBean bean = records.get(position);
            holder.year.setText(bean.year+"");
            holder.year.setTextColor(mainColor);
            holder.month.setText(bean.month + "/" + bean.day);
            holder.month.setTextColor(mainColor);
            holder.shortLine.setBackgroundColor(lastColor);
            holder.ovalView.setBackgroundDrawable(drawable);
            holder.longLine.setBackgroundColor(mainColor);
            holder.title.setText(bean.title);
            holder.impressions.setText(bean.impressions);
            if(bean.bitmap != null)
                holder.imageView.setImageBitmap(bean.bitmap);
        }

        @Override
        public int getItemCount() {
            return records.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? R.layout.item_record_wall_1 : R.layout.item_record_wall_2;
        }

    }


    //标题栏返回按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent();
            intent.setClass(RecordsWallActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

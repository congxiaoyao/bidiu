package space.levan.bidiu.tool;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import space.levan.bidiu.R;

public class ToolBarActivity extends AppCompatActivity {

    private TitleInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 请在setContentView之后调用此方法完成对toolbar的初始化
     * @param toolbar
     */
    protected void initToolBar(final Toolbar toolbar) {
        info = new TitleInfo();
        setTitleInfo(info);
        toolbar.setBackgroundColor(info.toolBarColor);
        toolbar.setTitle("");

        final TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText(info.title);
        title.setTextColor(info.titleTextColor);
        title.post(new Runnable() {
            @Override
            public void run() {
                float x = title.getX();
                int w = title.getWidth(), tw = toolbar.getWidth();
                title.setTranslationX((tw - w) / 2 - x);
            }
        });

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(info.showBackButton) actionBar.setDisplayHomeAsUpEnabled(true);
        StatusBarCompat.compat(this, info.statusBarColor);
    }

    public void setTitleInfo(TitleInfo info) {
        info.handleBack = false;
    }

    protected static class TitleInfo{
        public int toolBarColor = Color.parseColor("#F6657C");
        public int statusBarColor = toolBarColor;
        public int titleTextColor = Color.WHITE;
        public String title = "标题";
        public boolean showBackButton = true;
        public boolean handleBack = true;
    }

}


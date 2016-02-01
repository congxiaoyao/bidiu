package space.levan.bidiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import space.levan.bidiu.tool.ToolBarActivity;

/**
 * Created by 339 on 2016/1/30.
 */
public class AssistantDebuggingActivity extends ToolBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_debugging);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);
    }
    @Override
    public void setTitleInfo(TitleInfo info) {
        info.title = "助手调试";
    }

    //标题栏返回按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent();
            intent.setClass(AssistantDebuggingActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
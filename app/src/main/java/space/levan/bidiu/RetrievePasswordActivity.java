package space.levan.bidiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import space.levan.bidiu.tool.ToolBarActivity;

/**
 * Created by 339 on 2016/1/26.
 */
public class RetrievePasswordActivity extends ToolBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);
        setContentView(R.layout.activity_retrieve_password);
        Button retrievePswBtn = (Button) findViewById(R.id.retrievePswBtn);
        retrievePswBtn.setOnClickListener(relistener);
    }
    @Override
    public void setTitleInfo (TitleInfo info) {
        info.title = "找回密码";
    }
    //标题栏返回按钮事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent();
            intent.setClass(RetrievePasswordActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
    //键盘返回按钮点击事件
    public boolean onKeyDown(int KeyCode,KeyEvent event) {
        if (KeyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(RetrievePasswordActivity.this,LoginActivity.class);
            startActivity(intent);
            RetrievePasswordActivity.this.finish();
        }
        return true;
    }
    //找回密码按钮点击事件
    public Button.OnClickListener relistener = new Button.OnClickListener() {
        public void onClick (View view) {
            EditText emailNumET = (EditText) findViewById(R.id.emailNumET);
            String inputString = emailNumET.getText().toString();
            if (inputString.matches("\\w+@\\w+\\.\\w+")) {
                //邮箱格式正确则发送找回密码邮件
            } else {
                Toast.makeText(getApplicationContext(), "您输入的邮箱格式有误，请重新输入", Toast.LENGTH_SHORT).show();
            }
        }
    };
}

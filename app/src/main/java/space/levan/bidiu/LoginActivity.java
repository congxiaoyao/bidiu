package space.levan.bidiu;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import space.levan.bidiu.tool.ToolBarActivity;


/**
 * Created by 339 on 2016/1/25.
 */
public class LoginActivity extends ToolBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(loglistener);
        registerBtn.setOnClickListener(reglistener);
    }
    @Override
    public void setTitleInfo(TitleInfo info) {
        info.title = "登录";
    }

    //标题栏返回按钮事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //键盘返回按钮事件
    public boolean onKeyDown(int KeyCode,KeyEvent event) {
        if (KeyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        return true;
    }
    //注册按钮点击事件
    private Button.OnClickListener reglistener = new Button.OnClickListener() {
        public void onClick(View view) {
            Intent intent  = new Intent();
            intent.setClass(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    };
    //登录按钮点击事件
    private Button.OnClickListener loglistener = new Button.OnClickListener() {
        public void onClick(View view) {
            EditText userNameET = (EditText) findViewById(R.id.userNameET);
            String inputString = userNameET.getText().toString();
            if (inputString.matches("\\w+@\\w+\\.\\w+")) {
                //邮箱格式正确则登录
            } else {
                Toast.makeText(getApplicationContext(),"您输入的邮箱格式有误，请重新输入",Toast.LENGTH_SHORT).show();
            }
        }
    };


}

package space.levan.bidiu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import space.levan.bidiu.tool.ToolBarActivity;

/**
 * Created by 339 on 2016/1/26.
 */
public class RegisterActivity extends ToolBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);
        TextView userAgrTV = (TextView) findViewById(R.id.userAgrTV);
        Button getSMSBtn = (Button) findViewById(R.id.getSMSBtn);
        getSMSBtn.setOnClickListener(SMSlistener);
        userAgrTV.setClickable(true);
        userAgrTV.setFocusable(true);
        userAgrTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dia = new AlertDialog.Builder(RegisterActivity.this);
                dia.setTitle("用户协议");
                dia.setMessage("YHXY");
                dia.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dia.create();
                AlertDialog dialog = dia.show();
                dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).
                        setTextColor(getResources().getColor(R.color.colorTheme));
            }
        });
    }
    
    @Override
    public void setTitleInfo(TitleInfo info) {
        info.title = "注册";
    }

    //标题栏返回按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent();
            intent.setClass(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //键盘返回按钮点击事件
    public boolean onKeyDown(int KeyCode,KeyEvent event) {
        if (KeyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            RegisterActivity.this.finish();
        }
        return true;
    }

    //获取验证码按钮点击事件
    public Button.OnClickListener SMSlistener = new Button.OnClickListener() {
        public void onClick(View view) {
            EditText regUserNameET = (EditText) findViewById(R.id.regUserNameET);
            String inputString = regUserNameET.getText().toString();
            if (inputString.matches("\\w+@\\w+\\.\\w+")) {
                //邮箱格式正确则发送验证码
            } else {
                Toast.makeText(getApplicationContext(), "您输入的邮箱格式有误，请重新输入", Toast.LENGTH_SHORT).show();
            }
        }
    };
}

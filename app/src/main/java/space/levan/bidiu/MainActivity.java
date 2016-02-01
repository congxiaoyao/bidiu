package space.levan.bidiu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import space.levan.bidiu.tool.SlidingMenu;


/**
 * Created by 339 on 2016/1/31.
 */
public class MainActivity extends Activity{

    //private SlidingMenu mMenu;
    //gitignore TEST

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        //侧滑栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //mMenu = (SlidingMenu) findViewById(R.id.id_menu);

        //侧滑栏选项
        RelativeLayout personalRL = (RelativeLayout) findViewById(R.id.personalRL);
        RelativeLayout assistorRL = (RelativeLayout) findViewById(R.id.assistorRL);
        RelativeLayout recordRL = (RelativeLayout) findViewById(R.id.recordRL);
        RelativeLayout animeRL = (RelativeLayout) findViewById(R.id.animeRL);
        RelativeLayout calendarRL = (RelativeLayout) findViewById(R.id.calendarRL);
        RelativeLayout exitRL = (RelativeLayout) findViewById(R.id.exitRL);

        //设置listener
        personalRL.setOnClickListener(perRLlistener);
        assistorRL.setOnClickListener(assRLlistener);
        recordRL.setOnClickListener(recRLlistener);
        animeRL.setOnClickListener(aniRLlistener);
        calendarRL.setOnClickListener(calRLlistener);
        exitRL.setOnClickListener(exiRLlistener);
    }

    /*public void toggleMenu (View view) {
        mMenu.toggle();

    }*/

    //个人档案
    private RelativeLayout.OnClickListener perRLlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,PersonalFileActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };

    //助手调试
    private RelativeLayout.OnClickListener assRLlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,AssistantDebuggingActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };

    //记录墙
    private RelativeLayout.OnClickListener recRLlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,RecordsWallActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };

    //当前追番
    private RelativeLayout.OnClickListener aniRLlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,CollectionActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };

    //当前日程
    private RelativeLayout.OnClickListener calRLlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,CalendarActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };

    //退出登录
    private RelativeLayout.OnClickListener exiRLlistener = new View.OnClickListener() {

        //先判断是否处于登录状态
        @Override
        public void onClick(View v) {

            AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
            dia.setMessage("确定要退出登录吗？");
            dia.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //此处插入退出登录
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });
            dia.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dia.create();
            AlertDialog dialog = dia.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).
                    setTextColor(getResources().getColor(R.color.colorTheme));
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).
                    setTextColor(getResources().getColor(R.color.colorTheme));

        }
    };
}

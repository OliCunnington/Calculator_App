package com.example.eniac.calculator_layout;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDefault(View v){
        TextView txtV = findViewById(R.id.display);
        String txt = (String) txtV.getText();
        Button btn = (Button) v;
        if(txt.isEmpty()){
            txtV.setText(btn.getText());
        } else {
            txtV.setText(txt+btn.getText());
        }
    }

    public void onClickDark(View v){
        TextView txtV = findViewById(R.id.display);
        String txt = (String) txtV.getText();
        int id = v.getId();
        switch (id){
            case R.id.c_btn: case R.id.ce_btn: txtV.setText(""); break;
            case R.id.minus_btn: case R.id.plus_btn: case R.id.div_btn: case R.id.multi_btn: {
                Button btn = (Button) v;
                txtV.setText(txt + btn.getText());
                break;
            }
            default:
                Toast.makeText(this,"Feature not available",Toast.LENGTH_SHORT).show();break;
        }
    }

    public void onClickSpecial(View v){
        TextView txtV = findViewById(R.id.display);
        Toast toast = Toast.makeText(this,txtV.getText().toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,5);
        toast.show();
        try{
            wait(1000);
        } catch (Exception e){}
        toast.setText("Calculating");
        toast.show();
    }

}

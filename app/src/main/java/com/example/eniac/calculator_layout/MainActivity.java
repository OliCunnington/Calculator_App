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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Double> ints = new ArrayList<>();
    private ArrayList<Character> operaters = new ArrayList<>();
    private double result;

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
            String str = btn.getText().toString();
            txtV.setText(txt+str);
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
                String str = btn.getText().toString();
                txtV.setText(txt + str);
                break;
            }
            default:
                Toast.makeText(this,"Feature not available",Toast.LENGTH_SHORT).show();break;
        }
    }

    public void onClickSpecial(View v){
        TextView txtV = findViewById(R.id.display);
        String str = txtV.getText().toString();
        txtV.setText("");
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,5);
        toast.show();
        if (Character.isDigit(str.charAt(str.length()-1))) {
            doCalculation(str);
        }
        else {
            toast = Toast.makeText(this, "Please enter a Number at the end of the calculation", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void doCalculation(String in){
        ints.clear();
        operaters.clear();
        char[] chars = in.toCharArray();
        StringBuilder strB = new StringBuilder();
        int i=0;
        for(;i<chars.length;i++){
            if(Character.isDigit(chars[i])) strB.append((Character)chars[i]);
            else if(chars[i]=='+'||chars[i]=='-'||chars[i]=='*'||chars[i]=='/'){
                operaters.add(chars[i]);
                ints.add(ints.size(), Double.parseDouble(strB.toString()));
                strB.setLength(0);
            }
        }
        ints.add(Double.parseDouble(strB.toString()));

        while (!operaters.isEmpty()) {
            if (operaters.contains('/')) {
                int j = operaters.lastIndexOf('/');
                divide(j);
            } else if (operaters.contains('*')) {
                int j = operaters.lastIndexOf('*');
                multiply(j);
            } else if (operaters.contains('-')) {
                int j = operaters.lastIndexOf('-');
                subtract(j);
            }else if (operaters.contains('+')) {
                int j = operaters.lastIndexOf('+');
                addition(j);
            }
        }
        result = ints.get(0);

        Toast.makeText(this,result+"",Toast.LENGTH_SHORT).show();

    }

    private void divide(int i){
        double d = ints.get(i)/ints.get(i+1);
        ints.remove(i);
        ints.remove(i);
        operaters.remove(i);
        ints.add(d);
    }

    private void multiply(int i){

        double d = ints.get(i)*ints.get(i+1);
        ints.remove(i);
        ints.remove(i);
        operaters.remove(i);
        ints.add(d);
    }

    private void subtract(int i){

        double d = ints.get(i)-ints.get(i+1);
        ints.remove(i);
        ints.remove(i);
        operaters.remove(i);
        ints.add(d);
    }

    private void addition(int i){
        double d = ints.get(i)+ints.get(i+1);
        ints.remove(i);
        ints.remove(i);
        operaters.remove(i);
        ints.add(d);
    }
}

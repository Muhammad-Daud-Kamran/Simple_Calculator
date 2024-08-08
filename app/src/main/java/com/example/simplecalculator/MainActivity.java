package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvsolution, tvresuilt;
    MaterialButton btn_C, btn_openbrac, btn_closebrac;
    MaterialButton btn_multiply, btn_plus, btn_minus, btn_equals, btnpoint, btnAC;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvresuilt = findViewById(R.id.tvresuilt);
        tvsolution = findViewById(R.id.tvsolution);

        Assignid(btn_C, R.id.btn_C);
        Assignid(btn_openbrac, R.id.btn_openbrac);
        Assignid(btn_closebrac, R.id.btn_closebrac);
        Assignid(btn_multiply, R.id.btn_multiply);
        Assignid(btn_plus, R.id.btn_plus);
        Assignid(btn_minus, R.id.btn_minus);
        Assignid(btn_equals, R.id.btn_equals);
        Assignid(btnpoint, R.id.btnpoint);
        Assignid(btnAC, R.id.btnAC);
        Assignid(btn0, R.id.btn0);
        Assignid(btn1, R.id.btn1);
        Assignid(btn2, R.id.btn2);
        Assignid(btn3, R.id.btn3);
        Assignid(btn4, R.id.btn4);
        Assignid(btn5, R.id.btn5);
        Assignid(btn6, R.id.btn6);
        Assignid(btn7, R.id.btn7);
        Assignid(btn8, R.id.btn8);
        Assignid(btn9, R.id.btn9);


    }

    void Assignid(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String Buttontext = button.getText().toString();
        String datatocalculate = tvsolution.getText().toString();
        if (Buttontext.equals("AC")) {
            tvsolution.setText("");
            tvresuilt.setText("0");
            return;
        }
        if (Buttontext.equals("=")) {
            tvsolution.setText(tvresuilt.getText());
            return;
        }
        if (Buttontext.equals("C")) {
            datatocalculate = datatocalculate.substring(0, datatocalculate.length() - 1);

        } else {
            datatocalculate = datatocalculate + Buttontext;
        }
        tvsolution.setText(datatocalculate);
        String Finalresuilt=getresuilt(datatocalculate);

        if(!Finalresuilt.equals("Err")){
            tvresuilt.setText(Finalresuilt);
        }
    }
    String getresuilt(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String Finalresuilt = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
           if (Finalresuilt.endsWith(".0")){
               Finalresuilt=Finalresuilt.replace(".0","");
           }
            return Finalresuilt;
        } catch (Exception e) {
           return "Err";
        }
    }
}
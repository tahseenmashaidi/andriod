package com.example.class_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.graphics.Color.rgb;

public class Fragment extends androidx.fragment.app.Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv1,tv2;
        tv1=(TextView) view.findViewById(R.id.text1);
        tv2=(TextView) view.findViewById(R.id.text2);
        tv1.setText("Age in "+getArguments().getString("unit"));
        tv2.setText( getArguments().getString("date"));
        String text_color=getArguments().getString("color");
        try {
            if (text_color.equals("NULL")) {
                //do nothing
            } else if (text_color.equals("Red")) {
                tv2.setTextColor(rgb(255, 0, 0));
            } else if (text_color.equals("Blue")) {
                tv2.setTextColor(rgb(33, 66, 131));
            } else if (text_color.equals("Yellow")) {
                tv2.setTextColor(rgb(248, 239, 2));
            }
        } catch(NullPointerException e)
        {
            System.out.print("NullPointerException Caught");
        }
    }
}

package com.example.gestionscolarite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowNoteActivity extends AppCompatActivity {

    Button button1,button2;
    TextView textView;
    DBHelper sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        sqLiteDatabase=new DBHelper(this);
        button1=(Button) findViewById(R.id.button71);
        button2=(Button) findViewById(R.id.button72);
        textView=(TextView) findViewById(R.id.textView72);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = sqLiteDatabase.showData2();
                String data = c.getColumnName(0) + "\t\t" + c.getColumnName(1) + "\t\t" + c.getColumnName(2) + "\t\t" + c.getColumnName(3) + "\t\t"  + "\n";
                while (c.moveToNext()) {
                    data = data + c.getString(0) + "\t\t\t\t\t\t\t\t" + c.getString(1) + "\t\t\t\t\t\t\t\t" + c.getString(2) + "\t\t\t\t\t\t\t\t" + c.getString(3) + "\t\t\t\t\t\t\t\t" + "\n";
                }
                textView.setText(data);
            }
        });
        final Intent intent = new Intent(this,HomeActivity.class);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);

            }
        });
    }
}

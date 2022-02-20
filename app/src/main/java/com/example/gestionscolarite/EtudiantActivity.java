package com.example.gestionscolarite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EtudiantActivity extends AppCompatActivity {

    Button button1,button2;
    TextView textView;
    DBHelper sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant);
        sqLiteDatabase= new DBHelper(this);
        button1=(Button) findViewById(R.id.button61);
        button2=(Button) findViewById(R.id.button62);
        textView=(TextView) findViewById(R.id.textView62);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = sqLiteDatabase.showData1();
                String data = c.getColumnName(0) + "\t\t\t\t\t\t" +
                        c.getColumnName(1)+ "\t\t\t\t\t\t\t\t" +
                        c.getColumnName(2)+  "\t\t\t\t\t" +
                        c.getColumnName(3)+  "\n";
                while (c.moveToNext()) {
                    data = data + c.getString(0) + "\t\t\t\t\t\t\t\t" +
                            c.getString(1)+ "\t\t\t\t\t\t\t\t\t\t\t" +
                            c.getString(2)+ "\t\t\t\t\t\t\t\t\t\t\t" + c.getString(3)+
                            "\t\t\t"+

                            "\n";
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

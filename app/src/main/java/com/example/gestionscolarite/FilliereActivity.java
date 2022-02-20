package com.example.gestionscolarite;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

public class FilliereActivity extends AppCompatActivity {

    DBHelper sqLiteDatabase;

    Button button,button2;

    EditText editText,editText2;
    int idfilliere;
    String nomfilliere;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filliere);
        sqLiteDatabase=new DBHelper(this);
        button= (Button) findViewById(R.id.button41);
        button2=(Button) findViewById(R.id.button42);
        editText=(EditText) findViewById(R.id.editText41);
        editText2=(EditText) findViewById(R.id.editText42);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nomfilliere=editText2.getText().toString().trim();

                boolean val = sqLiteDatabase.addFilliere(nomfilliere);
                if(val) {
                    editText.setText("");
                    editText2.setText("");

                }else {
                    Toast.makeText(FilliereActivity.this, "not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final Intent intent =new Intent(this,HomeActivity.class);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);

            }
        });

    }
}

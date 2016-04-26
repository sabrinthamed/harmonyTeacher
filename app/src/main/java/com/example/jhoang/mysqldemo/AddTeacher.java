package com.example.jhoang.mysqldemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddTeacher extends AppCompatActivity {
    EditText StudentIDET, NameET, UsernameET, PasswordET, InstrumentET, ClassET, FieldNumET;
 //   String username;
  //  String password;
  //  ListView lv;

  //  String[] names = {"Jason", "Alex", "Tim"};

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

     //   Intent extraIntent = getIntent();
     //   username = extraIntent.getStringExtra("username");
     //   password = extraIntent.getStringExtra("password");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //  StudentIDET = (EditText)findViewById(R.id.etStudentID);
        NameET = (EditText)findViewById(R.id.etNameTeacher);
        //UsernameET = (EditText)findViewById(R.id.etUsername);
        PasswordET = (EditText)findViewById(R.id.etPasswordTeacher);
        //InstrumentET = (EditText)findViewById(R.id.etInstrument);
        //ClassET = (EditText)findViewById(R.id.etCLass);
        //FieldNumET = (EditText)findViewById(R.id.etFieldNumber);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void OnAddTeacher(View view){
       // String StudentIDETstring = StudentIDET.getText().toString();
        String NameETstring = NameET.getText().toString();
       // String UsernameETstring = UsernameET.getText().toString();
        String PasswordETstring = PasswordET.getText().toString();
       // String InstrumentETstring = InstrumentET.getText().toString();
       // String ClassETstring = ClassET.getText().toString();
       // String FieldNumETstring = FieldNumET.getText().toString();
        // Toast.makeText(context, "send", Toast.LENGTH_LONG).show();
        String type = "add";
        AddTeacherBackground addTeacherBackground = new AddTeacherBackground(this);
        addTeacherBackground.execute(type, NameETstring, PasswordETstring);
    }

}

package com.example.jhoang.mysqldemo;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Music_MvtActivity extends AppCompatActivity {
    Music_MvtDatabaseHelper myDb;
    EditText editMvtNum, editNameOfMvt,editInstrument,editShowId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__mvt);
        myDb = new Music_MvtDatabaseHelper(this);

        editMvtNum = (EditText)findViewById(R.id.editText_MvtNumber);
        editNameOfMvt = (EditText)findViewById(R.id.editText_NameOfMvt);
        editInstrument = (EditText)findViewById(R.id.editText_Instrument);
        editShowId = (EditText)findViewById(R.id.editText_ShowId);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editMvtNum.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Music_MvtActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Music_MvtActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editMvtNum.getText().toString(),
                                editNameOfMvt.getText().toString(),
                                editInstrument.getText().toString(), editShowId.getText().toString()
                        );
                        if (isUpdate == true)
                            Toast.makeText(Music_MvtActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Music_MvtActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                editMvtNum.getText().toString(),
                                editNameOfMvt.getText().toString(),
                                editInstrument.getText().toString(),
                                editShowId.getText().toString()
                        );
                        if (isInserted == true)
                            Toast.makeText(Music_MvtActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Music_MvtActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Number of Mvt :" + res.getString(1) + "\n");
                            buffer.append("Name of Mvt :" + res.getString(2) + "\n");
                            buffer.append("Instrument :" + res.getString(3) + "\n");
                            buffer.append("Show ID :" + res.getString(4) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


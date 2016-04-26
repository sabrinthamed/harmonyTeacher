package com.example.jhoang.mysqldemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Music_MvtList extends Activity {

    EditText inputContent1, inputContent2;
    Button buttonAdd, buttonDeleteAll;
    private static Button btneditmusicmvt;
    private Music_MvtAdapter mySQLiteAdapter;
    ListView listContent;

    SimpleCursorAdapter cursorAdapter;
    Cursor cursor;
    String select;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__mvt_list);

        //inputContent1 = (EditText)findViewById(R.id.content1);
        //inputContent2 = (EditText)findViewById(R.id.content2);
        //buttonAdd = (Button)findViewById(R.id.add);
        //buttonDeleteAll = (Button)findViewById(R.id.deleteall);

        listContent = (ListView)findViewById(R.id.contentlist);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                select= null;
            } else {
                select= extras.getString("STRING_I_NEED");
            }
        } else {
            select= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        mySQLiteAdapter = new Music_MvtAdapter(this);
        mySQLiteAdapter.openToWrite();

        cursor = mySQLiteAdapter.getAllData(select);
        String[] from = new String[]{Music_MvtAdapter.KEY_ID, Music_MvtAdapter.KEY_CONTENT1, Music_MvtAdapter.KEY_CONTENT2, Music_MvtAdapter.KEY_CONTENT3, Music_MvtAdapter.KEY_CONTENT4};
        int[] to = new int[]{R.id.id, R.id.text1, R.id.text2, R.id.text3, R.id.text4};
        cursorAdapter =
                new SimpleCursorAdapter(this, R.layout.music_mvt_item, cursor, from, to);
        listContent.setAdapter(cursorAdapter);
        //listContent.setOnItemClickListener(listContentOnItemClickListener);
        listContent.setOnItemClickListener(listContentOnItemClickListener);
        OnClickButtonListener();
        //buttonAdd.setOnClickListener(buttonAddOnClickListener);
        //buttonDeleteAll.setOnClickListener(buttonDeleteAllOnClickListener);

    }

    private void OnClickButtonListener() {
        btneditmusicmvt = (Button)findViewById(R.id.button_editmusicmvt);
        btneditmusicmvt.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.jhoang.mysqldemo.Music_MvtActivity");
                        startActivity(intent);
                    }
                }
        );
    }

  /*  Button.OnClickListener buttonAddOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            String data1 = inputContent1.getText().toString();
            String data2 = inputContent2.getText().toString();
            mySQLiteAdapter.insert(data1, data2);
            updateList();
        }

    };*/

   /* Button.OnClickListener buttonDeleteAllOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            mySQLiteAdapter.deleteAll();
            updateList();
        }

    };*/

    private ListView.OnItemClickListener listContentOnItemClickListener
            = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub

            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            final int item_id = cursor.getInt(cursor.getColumnIndex(Music_MvtAdapter.KEY_ID));
            String item_content1 = cursor.getString(cursor.getColumnIndex(Music_MvtAdapter.KEY_CONTENT1));
            String item_content2 = cursor.getString(cursor.getColumnIndex(Music_MvtAdapter.KEY_CONTENT2));
            String item_content3 = cursor.getString(cursor.getColumnIndex(Music_MvtAdapter.KEY_CONTENT3));
            String item_content4 = cursor.getString(cursor.getColumnIndex(Music_MvtAdapter.KEY_CONTENT4));
            Intent intent = new Intent("com.example.jhoang.mysqldemo.Music_Sheet");
            intent.putExtra("STRING_I_NEED",item_content1);
            intent.putExtra("STRING_I_NEED1",item_content4);
            startActivity(intent);

        }
    };
            /*AlertDialog.Builder myDialog
                    = new AlertDialog.Builder(AndroidSQLite.this);

            myDialog.setTitle("Delete/Edit?");

            TextView dialogTxt_id = new TextView(AndroidSQLite.this);
            LayoutParams dialogTxt_idLayoutParams
                    = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);
            dialogTxt_id.setText("#" + String.valueOf(item_id));

            final EditText dialogC1_id = new EditText(AndroidSQLite.this);
            LayoutParams dialogC1_idLayoutParams
                    = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            dialogC1_id.setLayoutParams(dialogC1_idLayoutParams);
            dialogC1_id.setText(item_content1);

            final EditText dialogC2_id = new EditText(AndroidSQLite.this);
            LayoutParams dialogC2_idLayoutParams
                    = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            dialogC2_id.setLayoutParams(dialogC2_idLayoutParams);
            dialogC2_id.setText(item_content2);

            LinearLayout layout = new LinearLayout(AndroidSQLite.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(dialogTxt_id);
            layout.addView(dialogC1_id);
            layout.addView(dialogC2_id);
            myDialog.setView(layout);

            myDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                // do something when the button is clicked
                public void onClick(DialogInterface arg0, int arg1) {
                    mySQLiteAdapter.delete_byID(item_id);
                    updateList();
                }
            });

            myDialog.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                // do something when the button is clicked
                public void onClick(DialogInterface arg0, int arg1) {
                    String value1 = dialogC1_id.getText().toString();
                    String value2 = dialogC2_id.getText().toString();
                    mySQLiteAdapter.update_byID(item_id, value1, value2);
                    updateList();
                }
            });

            myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                // do something when the button is clicked
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            myDialog.show();


        }};*/

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mySQLiteAdapter.close();
    }



    private void updateList(){
        cursor.requery();
    }

}

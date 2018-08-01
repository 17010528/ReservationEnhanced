package sg.edu.rp.c346.reservation;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static sg.edu.rp.c346.reservation.R.id.editName;

public class MainActivity extends AppCompatActivity {

    Button btnReserve, btnReset;
    EditText etName, etMobileNumber, etPax, etPs1,etPs2;
    CheckBox cbSmoking;
    TextView tv1,tv2;
    int theYear,theMonth,theDay,theHour,theMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReserve = findViewById(R.id.buttonBook);
        btnReset = findViewById(R.id.buttonReset);

        etName = findViewById(R.id.editName);
        etMobileNumber = findViewById(R.id.mobileNumber);
        etPax = findViewById(R.id.numberPpl);
        etPs1 = findViewById(R.id.editTextPs1);
        etPs2 = findViewById(R.id.editTextPs2);

        cbSmoking = findViewById(R.id.smoking);

        tv1 = findViewById(R.id.textView2);
        tv2 = findViewById(R.id.textView3);

        Calendar rightNow = Calendar.getInstance();
        theYear = rightNow.get(Calendar.YEAR);
        theMonth = rightNow.get(Calendar.MONTH);
        theDay = rightNow.get(Calendar.DAY_OF_MONTH);
        theHour = rightNow.get(Calendar.HOUR_OF_DAY);
        theMinute = rightNow.get(Calendar.MINUTE);



        btnReset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                etMobileNumber.setText(null);
                etPax.setText(null);
                etName.setText("");
            }



        });
        etPs1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etPs1.setText("Date: "+dayOfMonth+"/"+ (month+1)+"/"+year);

                    }
                };

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,myDateListener,theYear,theMonth,theDay);
                myDateDialog.show();

            }
        });

        etPs2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etPs2.setText("Time: "+hourOfDay + ":"+minute);
                    }
                };

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener, theHour,theMinute,true);

                myTimeDialog.show();
            }
        });


        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getName = etName.getText().toString().trim();
                String getTel = etMobileNumber.getText().toString().trim();
                String getSize = etPax.getText().toString().trim();
                String getDate = etPs1.getText().toString().trim();
                String getTime = etPs2.getText().toString().trim();
                String smoke ="";

                    if (cbSmoking.isChecked()) {

                        smoke = "Yes";

                    } else {

                        smoke = "No";
                    }



                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Confirm Your Order");
                myBuilder.setMessage(" New Reservation \n"+" Name: "+getName+" \nSmoking: "+smoke+ "\nSize: "+getSize
                        +"\nDate:"+getDate+"\nTime:"+getTime);
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Confirm",null);
                myBuilder.setNeutralButton("Cancel",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();




                }



        });
    }
}

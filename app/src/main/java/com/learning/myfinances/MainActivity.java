package com.learning.myfinances;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    //declaring variables used in app
    Button btnSelect, btnSave, btnCancel;
    RadioButton rbtnSelected;
    RadioGroup rbtngAccTypeGroup;
    int selectedId;
    EditText editAccNum, editInitBalance, editCurrentBal, editPaymentAmnt, editInterestRate;
    String accNum, initBalance,currentBal, paymentAmt, interestRate;

    //declaring a dbhandler class that will save information to the database for us
    DBHandler dbHandler = new DBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping variables to widgets in xml
        editAccNum = (EditText) findViewById(R.id.editTextAccNum);
        editInitBalance  = (EditText) findViewById(R.id.editTextInitBal);
        editCurrentBal  = (EditText) findViewById(R.id.editTextCurrentBal);
        editPaymentAmnt = (EditText) findViewById(R.id.editTextPaymentAmount);
        editInterestRate  = (EditText) findViewById(R.id.editTextInterestRate);
        rbtngAccTypeGroup = (RadioGroup) findViewById(R.id.RadioGroupAccType);
        btnSelect = (Button) findViewById(R.id.buttonSelect);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnCancel = (Button) findViewById(R.id.buttonCancel);


        //Setting all the edit text fields to not available when the app starts and changing the
        //hint to not available as the user has not yet chosen a acc type
        editAccNum.setFocusable(false);
        editAccNum.setHint(R.string.not_available);
        editInitBalance.setFocusable(false);
        editInitBalance.setHint(R.string.not_available);
        editCurrentBal.setFocusable(false);
        editCurrentBal.setHint(R.string.not_available);
        editPaymentAmnt.setFocusable(false);
        editPaymentAmnt.setHint(R.string.not_available);
        editInterestRate.setFocusable(false);
        editInterestRate.setHint(R.string.not_available);



        //Setting an onclick listener for the select button, fires when select button is selected.
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //identifying the selected radio button.
                selectedId = rbtngAccTypeGroup.getCheckedRadioButtonId();
                rbtnSelected = (RadioButton) findViewById(selectedId);

                //using switch case to identify button selected.
                switch (rbtnSelected.getText().toString()){

                    //if CD
                    case "CD":

                        //Setting the relevent edit fields available for the user.

                        editAccNum.setFocusableInTouchMode(true);
                        editAccNum.setHint(R.string.enter_acc);

                        editInitBalance.setFocusableInTouchMode(true);
                        editInitBalance.setHint(R.string.enter_amount);

                        editCurrentBal.setFocusableInTouchMode(true);
                        editCurrentBal.setHint(R.string.enter_amount);

                        editInterestRate.setFocusableInTouchMode(true);
                        editInterestRate.setHint(R.string.enter_amount);

                        editPaymentAmnt.setFocusableInTouchMode(false);
                        editPaymentAmnt.setHint(R.string.not_available);

                        break;

                        //if Loan
                    case "Loan":

                        //Setting the relevent edit fields available for the user.
                        editAccNum.setFocusableInTouchMode(true);
                        editAccNum.setHint(R.string.enter_acc);

                        editInitBalance.setFocusableInTouchMode(true);
                        editInitBalance.setHint(R.string.enter_amount);

                        editCurrentBal.setFocusableInTouchMode(true);
                        editCurrentBal.setHint(R.string.enter_amount);

                        editPaymentAmnt.setFocusableInTouchMode(true);
                        editPaymentAmnt.setHint(R.string.enter_amount);

                        editInterestRate.setFocusableInTouchMode(true);
                        editInterestRate.setHint(R.string.enter_amount);

                        break;

                        //if Cheque acc
                    case "Cheque Account":

                        //Setting the relevent edit fields available for the user.
                        editAccNum.setFocusableInTouchMode(true);
                        editAccNum.setHint(R.string.enter_acc);

                        editCurrentBal.setFocusableInTouchMode(true);
                        editCurrentBal.setHint(R.string.enter_amount);

                        editInitBalance.setFocusableInTouchMode(false);
                        editInitBalance.setHint(R.string.not_available);

                        editPaymentAmnt.setFocusableInTouchMode(false);
                        editPaymentAmnt.setHint(R.string.not_available);

                        editInterestRate.setFocusableInTouchMode(false);
                        editInterestRate.setHint(R.string.not_available);

                        break;
                }

            }
        });

        //Setting on click listener that saves data when save button is clicked.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rbtnSelected.getText().toString()){

                    //if cd
                    case "CD":

                        //getting and storing the values of the needed fields
                        accNum = editAccNum.getText().toString();
                        initBalance= editInitBalance.getText().toString();
                        currentBal = editCurrentBal.getText().toString();
                        interestRate= editInterestRate.getText().toString();

                        //creating a CD object which will hold the values of the data entered.
                        CD ObjCD = new CD(accNum,initBalance,currentBal,interestRate, "0");

                        //clearing the fields and saving the data to the database.
                        clearFields("CD", initBalance, currentBal, interestRate, "0", accNum);

                        //showing the user a message to say the data is saved.
                        Toast.makeText(MainActivity.this, "Your information has been saved.", Toast.LENGTH_SHORT).show();

                        break;

                        //if Loan
                    case "Loan":

                        //getting and storing the values of the needed fields
                        accNum = editAccNum.getText().toString();
                        initBalance= editInitBalance.getText().toString();
                        currentBal = editCurrentBal.getText().toString();
                        interestRate= editInterestRate.getText().toString();
                        paymentAmt = editPaymentAmnt.getText().toString();

                        //creating a Loan object which will hold the values of the data entered.
                        Loan ObjLoan = new Loan(accNum,initBalance,currentBal,interestRate, paymentAmt);

                        //clearing the fields and saving the data to the database.
                        clearFields("Loan", initBalance, currentBal, interestRate, paymentAmt, accNum);

                        //showing the user a message to say the data is saved.
                        Toast.makeText(MainActivity.this, "Your information has been saved.", Toast.LENGTH_SHORT).show();

                        break;

                        //if Cheque account
                    case "Cheque Account":

                        //getting and storing the values of the needed fields
                        accNum = editAccNum.getText().toString();
                        initBalance= editInitBalance.getText().toString();
                        currentBal = editCurrentBal.getText().toString();
                        interestRate= editInterestRate.getText().toString();
                        paymentAmt = editPaymentAmnt.getText().toString();

                        //creating a Cheque acc object which will hold the values of the data entered.
                        ChequeAcc ObjChequeAcc = new ChequeAcc(accNum, "0",currentBal,"0", "0");

                        //clearing the fields and saving the data to the database.
                        clearFields("Cheque Account", "0", currentBal, "0", "0", accNum);

                        //showing the user a message to say the data is saved.
                        Toast.makeText(MainActivity.this, "Your information has been saved.", Toast.LENGTH_SHORT).show();

                        break;
                }

            }
        });

        //setting onclick listener for the cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking which radio button was clicked.
                switch (rbtnSelected.getText().toString()){

                    //if CD
                    case "CD":

                        //get the values of the edit text widgets
                        accNum = editAccNum.getText().toString();
                        initBalance= editInitBalance.getText().toString();
                        currentBal = editCurrentBal.getText().toString();
                        interestRate= editInterestRate.getText().toString();

                        //validate
                        if (accNum.equals("")) {
                            accNum = "0";
                        }
                        if (initBalance.equals("")) {
                            initBalance = "0";
                        }
                        if (currentBal.equals("")) {
                            currentBal = "0";
                        }
                        if (interestRate.equals("")) {
                            interestRate = "0";
                        }
                        if (paymentAmt.equals("")) {
                            paymentAmt = "0";
                        }

                        //clear fields and save data
                        clearFields("CD", initBalance, currentBal, interestRate, "0", accNum);

                        break;

                        //if loan
                    case "Loan":


                        //get the values of the edit text widgets
                        accNum = editAccNum.getText().toString();
                        initBalance= editInitBalance.getText().toString();
                        currentBal = editCurrentBal.getText().toString();
                        interestRate= editInterestRate.getText().toString();
                        paymentAmt = editPaymentAmnt.getText().toString();

                        //validate
                        if (accNum.equals("")) {
                            accNum = "0";
                        }
                        if (initBalance.equals("")) {
                            initBalance = "0";
                        }
                        if (currentBal.equals("")) {
                            currentBal = "0";
                        }
                        if (interestRate.equals("")) {
                            interestRate = "0";
                        }
                        if (paymentAmt.equals("")) {
                            paymentAmt = "0";
                        }

                        //clear fields and save data
                        clearFields("Loan", initBalance, currentBal, interestRate, paymentAmt, accNum);

                        break;

                        //if cheque
                    case "Cheque Account":

                        //get edit text data
                        accNum = editAccNum.getText().toString();
                        initBalance= editInitBalance.getText().toString();
                        currentBal = editCurrentBal.getText().toString();
                        interestRate= editInterestRate.getText().toString();
                        paymentAmt = editPaymentAmnt.getText().toString();

                        //validate
                        if (accNum.equals("")) {
                            accNum = "0";
                        }
                        if (initBalance.equals("")) {
                            initBalance = "0";
                        }
                        if (currentBal.equals("")) {
                            currentBal = "0";
                        }
                        if (interestRate.equals("")) {
                            interestRate = "0";
                        }
                        if (paymentAmt.equals("")) {
                            paymentAmt = "0";
                        }

                        //cancel and clear fields and save data
                        clearFields("Cheque Account", "0", currentBal, "0", "0", accNum);

                        break;
                }
            }
        });


    }

    //helper function to save informations to the database
    public void saveToDB( String accType, String initBalance, String currentBal,
                          String interestRate, String paymentAmt, String accNum){

        //using DB object created earlier and storing to db using obj method
        dbHandler.addToDB(accType, Double.parseDouble(initBalance),
                Double.parseDouble(currentBal), Double.parseDouble(interestRate),
                Double.parseDouble(paymentAmt),
                Integer.parseInt(accNum));
    }

    //helper function to clear the fields
    public void clearFields(String accType, String initBalance, String currentBal,
                            String interestRate, String paymentAmt, String accNum){

        //calling db helper function.
        saveToDB( accType, initBalance, currentBal, interestRate, paymentAmt, accNum);

        //clearing fields
        editAccNum.setText("");
        editCurrentBal.setText("");
        editInitBalance.setText("");
        editPaymentAmnt.setText("");
        editInterestRate.setText("");
    }
}
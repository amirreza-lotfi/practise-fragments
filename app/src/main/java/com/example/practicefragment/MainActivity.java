package com.example.practicefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity implements OptionListener {
    public final String DEFAULT_CALLBACK_VALUE = "default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGoToDialog = findViewById(R.id.btn_go_to_dialog_fragment);
        Button buttonShowBottomSheet = findViewById(R.id.btn_show_bottom_sheet);
        Button buttonGoToFragmentA = findViewById(R.id.btn_go_to_fragment_A);
        Button buttonCarry = findViewById(R.id.btn_carry_to_fragment_c);


        buttonGoToDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.show(getSupportFragmentManager(),null);
            }
        });
        buttonShowBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetFragment(MainActivity.this);
                bottomSheetDialogFragment.show(getSupportFragmentManager(),null);
            }
        });
        buttonGoToFragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(android.R.id.content,new FragmentA());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        buttonCarry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView callBackData = findViewById(R.id.callback_data);
                Bundle bundle = new Bundle();
                bundle.putString("data",callBackData.getText().toString());
                FragmentC fragmentC = new FragmentC();
                fragmentC.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content,fragmentC)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void okOption(String data) {
        TextView textView = findViewById(R.id.callback_data);
        textView.setText(data);
    }
    @Override
    public void cancelOption() {
        TextView textView = findViewById(R.id.callback_data);
        textView.setText(DEFAULT_CALLBACK_VALUE);
        Toast.makeText(this,"The operation canceled !!!",Toast.LENGTH_SHORT).show();
    }
}
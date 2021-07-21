package com.example.practicefragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class Dialog extends DialogFragment {
    public OptionListener options;
    public Dialog(OptionListener options) {
        this.options = options;
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.option,null,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        EditText dialogEditText = view.findViewById(R.id.et_callback);
        Button buttonOk = view.findViewById(R.id.btn_ok_dialog_fragment);
        Button buttonCancel = view.findViewById(R.id.btn_cancel_dialog_fragment);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogEditText !=null)
                    options.okOption(dialogEditText.getText().toString());
                dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.cancelOption();
                dismiss();
            }
        });
        return builder.create();
    }

}

package com.example.practicefragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    public OptionListener options;

    public BottomSheetFragment(OptionListener options) {
        this.options = options;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.option,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    }

}

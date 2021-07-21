package com.example.practicefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentA extends Fragment implements OptionListener{
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonBackToMainActivity = view.findViewById(R.id.btn_back_to_main_activity);
        Button buttonGoToDialog = view.findViewById(R.id.btn_go_to_dialog_fragment);
        Button buttonShowBottomSheet = view.findViewById(R.id.btn_show_bottom_sheet);
        Button buttonCarry = view.findViewById(R.id.btn_carry_to_fragment_c);

        buttonBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        buttonGoToDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(FragmentA.this);
                dialog.show(getChildFragmentManager(),null);
            }
        });
        buttonShowBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetFragment(FragmentA.this);
                bottomSheetDialogFragment.show(getChildFragmentManager(),null);
            }
        });
        buttonCarry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView callBackData = view.findViewById(R.id.callback_data);
                Bundle bundle = new Bundle();
                bundle.putString("data",callBackData.getText().toString());
                FragmentC fragmentC = new FragmentC();
                fragmentC.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content,fragmentC)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void cancelOption() {
        TextView textView = this.view.findViewById(R.id.callback_data);
        textView.setText("default");
        Toast.makeText(getContext(),"The operation canceled !!!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void okOption(String data) {
        TextView textView = this.view.findViewById(R.id.callback_data);
        textView.setText(data);
    }
}

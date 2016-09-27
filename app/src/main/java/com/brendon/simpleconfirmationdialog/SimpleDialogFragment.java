package com.brendon.simpleconfirmationdialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;



public class SimpleDialogFragment extends DialogFragment {


    interface SimpleDialogFragmentListener {

        void userClickedOk();
        void userClickedCancel();

    }

    SimpleDialogFragmentListener mDialogListener;

    private static final String MESSAGE_ARGS = "Dialog message";


    public static SimpleDialogFragment newInstance(String message) {

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        Bundle args = new Bundle();
        args.putString(MESSAGE_ARGS, message);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof SimpleDialogFragmentListener) {

            mDialogListener = (SimpleDialogFragmentListener) activity;

        } else {

            throw new RuntimeException(activity.toString() + "Must implement SimpleDialogListener");
        }

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        AlertDialog dialog = builder.setTitle("Ok-Cancel Dialog Fragment")
                .setMessage("Please press Ok or Cancel")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mDialogListener.userClickedOk();

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mDialogListener.userClickedCancel();

                    }
                })
                .create();

        return dialog;

    }
}


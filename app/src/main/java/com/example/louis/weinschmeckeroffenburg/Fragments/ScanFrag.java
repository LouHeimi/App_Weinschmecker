package com.example.louis.weinschmeckeroffenburg.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louis.weinschmeckeroffenburg.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFrag extends Fragment implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    public String wineID;

    public ScanFrag() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //new Code
        zXingScannerView = new ZXingScannerView(getActivity());

        //New Code Ende
       // return inflater.inflate(R.layout.fragment_scan, container, false);

        return zXingScannerView;

    }

    //Barcodescanner
    public void scan (View view){

        zXingScannerView = new ZXingScannerView(getContext().getApplicationContext());
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    //new
    @Override
    public void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
    //Ende

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();

    }

    @Override
    public void handleResult(Result rawResult) {

        wineID = rawResult.getText();
        SingleWineFragBarcode singleWineFragBarcode = new SingleWineFragBarcode();
        Bundle bundle= new Bundle();
        bundle.putString("wineID", wineID);
        singleWineFragBarcode.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, singleWineFragBarcode, singleWineFragBarcode.getTag()).commit();
        final String result = rawResult.getText();

    }

    /*@Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("TAG", rawResult.getText()); // Prints scan results

         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

        // If you would like to resume scanning, call this method below:
        zXingScannerView.resumeCameraPreview(this);
    }*/


}

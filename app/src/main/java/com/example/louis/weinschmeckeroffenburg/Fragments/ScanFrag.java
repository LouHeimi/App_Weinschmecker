package com.example.louis.weinschmeckeroffenburg.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.adapter.WineAdapter;
import com.example.louis.weinschmeckeroffenburg.R;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFrag extends Fragment implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;

    private ArrayList<Item> mWineList;
    private WineAdapter mWineAdapter;

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

    //Barcodescanner, Öffnet eine Kamera-View
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



        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
      //  Wine scannedWineObject = databaseHelper.getWine(rawResult.getText()); //checken ob ID da ist, sonst Fehlermeldung
        Bundle bundle= new Bundle();
        //bundle.putString("wineID", scannedWineObject.getID());

        SingleWineFragBarcode singleWineFragBarcode = new SingleWineFragBarcode();
        singleWineFragBarcode.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, singleWineFragBarcode, singleWineFragBarcode.getTag()).commit();

    }




}

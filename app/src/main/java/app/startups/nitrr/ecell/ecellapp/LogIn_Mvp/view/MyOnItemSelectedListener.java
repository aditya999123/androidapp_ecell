package app.startups.nitrr.ecell.ecellapp.LogIn_Mvp.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by Iket on 8/8/2016.
 */
public class MyOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(parent.getContext(), "Item is " +
                    parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {


    }
}

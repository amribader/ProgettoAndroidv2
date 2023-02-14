package com.example.simplenav;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.simplenav.CommucationController.GetTwok;
import com.example.simplenav.CommucationController.addTwokI;
import com.example.simplenav.CommucationController.communicationController;
import com.example.simplenav.DB.PictureDB.Sid;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Response;
import yuku.ambilwarna.AmbilWarnaDialog;

public class Another extends Fragment implements AdapterView.OnItemSelectedListener {

    //private final @NonNull CreateTwok twok = new CreateTwok();
    private final @NonNull GetTwok twok = new GetTwok();

    // two buttons to open color picker dialog and one to
    // set the color for GFG text
    private Button mSetColorButton, mPickColorButton;

    public static final String mypreference = "mypref";

    // view box to preview the selected color
    private View mColorPreview;

    // this is the default color of the preview box
    private int mDefaultColor;


    // two buttons to open color picker dialog and one to
    // set the color for GFG text
    private Button mSetColorButtonBG, mPickColorButtonBG;

    // view box to preview the selected color
    private View mColorPreviewBG;

    // this is the default color of the preview box
    private int mDefaultColorBG;



    //button Create twok and take my position
    private Button createTwok, takeMyLocation;

    //attributi per ottenre la posizione

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView country,city,address,longitude,latitude;
    Button getLocation;
    private  final  static int REQUEST_CODE=100;



    public Another() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //container.getContext();
        return inflater.inflate(R.layout.fragment_another, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinnerD = view.findViewById(R.id.spinnerD);
        Spinner spinnerF = view.findViewById(R.id.spinnerF);
        Spinner spinnerV = view.findViewById(R.id.spinnerV);
        Spinner spinnerH = view.findViewById(R.id.spinnerH);

        //TextView textView = view.findViewById(R.id.textViewText);
        TextView textView = view.findViewById(R.id.textViewText);
        System.err.println(view.findViewById(R.id.textViewText));

// Create an ArrayAdapter using the string array and a default spinner layout
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        //////inizio
        //TODO rivedere convertire enum to array
        //List<String> mass = Arrays.asList(CreateTwok.Dimension.values());
        ArrayAdapter<CharSequence> adapterD = ArrayAdapter.createFromResource(activity,
                R.array.Dimension_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerD.setAdapter(adapterD);
        spinnerD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "small":
                        textView.setTextSize(20);
                        twok.setFontsize(20);
                        break;
                    case "medium":
                        textView.setTextSize(32);
                        twok.setFontsize(32);
                        break;
                    case "large":
                        textView.setTextSize(48);
                        twok.setFontsize(48);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //////////fine
        //
        // //inizio secondo spinner
        //TODO rivedere convertire enum to array
        //List<String> mass = Arrays.asList(CreateTwok.Dimension.values());
        ArrayAdapter<CharSequence> adapterF = ArrayAdapter.createFromResource(activity,
                R.array.Font_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerF.setAdapter(adapterF);
        spinnerF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.err.println(adapterView.getItemAtPosition(i));
                //Typeface face=Typeface.createFromAsset(getAssets(), "font/"+adapterView.getItemAtPosition(i).toString());
                //textView.setTypeface(face);
                System.err.println("font/" + adapterView.getItemAtPosition(i).toString() + ".ttf");
                //Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "font/"+adapterView.getItemAtPosition(i).toString()+".ttf");
                System.err.println(getActivity());
                System.err.println(getActivity().getAssets());
                System.err.println(getActivity().getAssets().getLocales());

                Typeface typeface = getResources().getFont(R.font.dancing_script);

                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "anton_regular":
                        typeface = getResources().getFont(R.font.anton_regular);
                        textView.setTypeface(typeface);
                        twok.setFonttype(0);
                        break;
                    case "bhutuka_regular":
                        typeface = getResources().getFont(R.font.bhutuka_regular);
                        textView.setTypeface(typeface);
                        twok.setFonttype(1);
                        break;
                    case "dancing_script":
                        typeface = getResources().getFont(R.font.dancing_script);
                        textView.setTypeface(typeface);
                        twok.setFonttype(2);
                        break;
                }


                textView.setTypeface(typeface);
                //@font/anton_regular

//                Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/anton_regular");
//                textView.setTypeface(typeface);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //////////fine

        //////inizio terzo spinerr Vertical
        //TODO rivedere convertire enum to array
        //List<String> mass = Arrays.asList(CreateTwok.Dimension.values());
        ArrayAdapter<CharSequence> adapterV = ArrayAdapter.createFromResource(activity,
                R.array.Vertical_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerV.setAdapter(adapterV);
        spinnerV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.err.println(adapterView.getItemAtPosition(i));
                //center_vertical	10	top 30  bottom 50
                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "bottom":
                        System.err.println("BOTTOM TAB");
                        textView.setGravity(Gravity.BOTTOM);
                        twok.setValign(2);
                        break;
                    case "top":
                        textView.setGravity(Gravity.TOP);
                        twok.setValign(0);
                        break;
                    case "center":
                        textView.setGravity(Gravity.CENTER_VERTICAL);
                        twok.setValign(1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //////////fine

        //////inizio terzo spinerr Vertical
        //TODO rivedere convertire enum to array
        //List<String> mass = Arrays.asList(CreateTwok.Dimension.values());
        ArrayAdapter<CharSequence> adapterH = ArrayAdapter.createFromResource(activity,
                R.array.Horizontal_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerH.setAdapter(adapterH);


        spinnerH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)

                // LEFT 3   RIGTH 5  CENTER HORIZONTAL 1

                System.err.println("->" + textView.getGravity());

                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "center":
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        twok.setHalign(1);
                        break;
                    case "left":
                        textView.setGravity(Gravity.LEFT);
                        twok.setHalign(0);
                        break;
                    case "right":
                        textView.setGravity(Gravity.RIGHT);
                        twok.setHalign(2);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //spinnerH.setOnItemSelectedListener(this);
        //////////fine


        //PARTE SELEZIONE COLORI


        // register two of the buttons with their
        // appropriate IDs
        mPickColorButton = view.findViewById(R.id.pick_color_button);
        mSetColorButton = view.findViewById(R.id.set_color_button);

        // and also register the view which shows the
        // preview of the color chosen by the user
        mColorPreview = view.findViewById(R.id.preview_selected_color);

        // set the default color to 0 as it is black
        mDefaultColor = 0;

        // button open the AmbilWanra color picker dialog.
        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // to make code look cleaner the color
                        // picker dialog functionality are
                        // handled in openColorPickerDialogue()
                        // function
                        openColorPickerDialogue(0);
                    }
                });

        mSetColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // as the mDefaultColor is the global
                        // variable its value will be changed as
                        // soon as ok button is clicked from the
                        // color picker dialog.
                        textView.setTextColor(mDefaultColor);
                        String s = String.valueOf(mDefaultColor).substring(1);
                        String hexColor = Integer.toHexString(mDefaultColor).substring(2);

                        System.err.println("fontcolor="+hexColor);

                        //controllo se il colore è null allora ne metto uno di defoult
                        //controllo se il colore è null allora ne metto uno di defoult FONTCOLOR
                        System.err.println("fontcolor controllo se il valore è valido oppure vale null");
                        if (hexColor==null){// || hexColor.equals("#000000")){
                            System.err.println("fontcolor nullo settato quindi valore di defoult");
                            hexColor = "ffffff";
                        }



                        twok.setFontcol(hexColor);
                        //twok.setFontcol(s);
                    }
                });
/////////////////
        //SECONDO

        // register two of the buttons with their
        // appropriate IDs
        mPickColorButtonBG = view.findViewById(R.id.pick_color_buttonBG);
        mSetColorButtonBG = view.findViewById(R.id.set_color_buttonBG);

        // and also register the view which shows the
        // preview of the color chosen by the user
        mColorPreviewBG = view.findViewById(R.id.preview_selected_colorBackground);

        // set the default color to 0 as it is black
        mDefaultColorBG = 0;

        // button open the AmbilWanra color picker dialog.
        mPickColorButtonBG.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // to make code look cleaner the color
                        // picker dialog functionality are
                        // handled in openColorPickerDialogue()
                        // function
                        openColorPickerDialogue(1);
                    }
                });

        mSetColorButtonBG.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // as the mDefaultColor is the global
                        // variable its value will be changed as
                        // soon as ok button is clicked from the
                        // color picker dialog.



                        System.err.println("Colore->" + mDefaultColorBG);
                        textView.setBackgroundColor(mDefaultColorBG);
                        System.err.println("Color->"+textView.getBackground().toString());
                        System.err.println("Color->"+textView.getBackground());
                        String s = String.valueOf(mDefaultColorBG).substring(1);
                        String hexColor = Integer.toHexString(mDefaultColorBG).substring(2);
                        //textView.setText("#"+hexColor);
                        System.err.println("HEXCOLOR"+"#"+hexColor);


                        //controllo se il colore è null allora ne metto uno di defoult BGCOLOR
                        System.err.println("bgcolor controllo se il valore è valido oppure vale null");
                        if (hexColor==null){// || hexColor.equals("#000000")){
                            System.err.println("bgcolor nullo settato quindi valore di defoult");
                            hexColor = "ffffff";
                        }

                        twok.setBgcol(hexColor);
                        //twok.setBgcol(s);
                        //todo gestire i vari errori viola blu rosso
                    }
                });


        takeMyLocation = view.findViewById(R.id.takeMyLocation);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(getActivity());

        takeMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clickMyLocation","clickMyLocation");
                getLastLocation();
                Toast.makeText(activity, "Location Ottenuta", Toast.LENGTH_SHORT).show();
            }
        });



        createTwok = view.findViewById(R.id.createTwok);

        createTwok.setOnClickListener(v -> {
            twok.setText(textView.getText().toString());
//            System.err.println(textView.getText());
//            System.err.println(textView.getCurrentTextColor());
//            System.err.println(textView.getBackground());
//            System.err.println(textView.getFontFeatureSettings());
//            System.err.println(textView.getTextSize());


            SharedPreferences sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);

            System.err.println("prova SID"+sharedpreferences.contains("sid"));
            System.err.println("prova SID"+sharedpreferences.getString("sid",""));

            Sid sid = new Sid();
            sid.setSid(sharedpreferences.getString("sid",""));

            System.err.println(twok);
            twok.setSid(sid.getSid());
//            twok.setSid("qaKOeIk1DhEvBLOruWaR");

            //controllare che i valori inseiti siano corretti in particolare fontcolor e bgcolor
            System.err.println("fontcolor controllo se il valore è valido oppure vale null");
            if (twok.getBgcol()==null){
                System.err.println("BGCOLOR è NULL");
                twok.setBgcol("ffffff");
            }
            if(twok.getFontcol()==null){// || hexColor.equals("#000000")){
                System.err.println("FONTCOLOR è NULL");
                twok.setFontcol("ffffff");
            }




            //todo sid hardcoded
            //todo sito con cose non insegnate in uni
            //todo ho riciclato le mie pagine va bene??
            communicationController.addTwok(twok, new addTwokI() {
                @Override
                public void addTwok(Response<Void> response) {
                    //operaizone eseguita con successo

                    System.err.println("CreaTwok response"+response);
                    if (response.code()==200){
                        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "Twok creato con successo", Toast.LENGTH_SHORT).show();
                    }else if (response.code()==400){
                        Toast.makeText(getActivity(), "CLIENT ERROR"+response.message(), Toast.LENGTH_SHORT).show();
                    }else if (response.code()==500){
                        Toast.makeText(getActivity(), "SERVER ERROR"+response.message(), Toast.LENGTH_SHORT).show();
                    }

                    //agiungere codice navigate to bacheca
                }
            });

/*
// ...

// Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "https://develop.ewlab.di.unimi.it/mc/twittok/addTwok";
//
//// Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                    response -> Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show(),
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("TESTTEST", "Ops: " + error.toString());
//                        }
//                    }) {
//                //error -> Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("sid", "qaKOeIk1DhEvBLOruWaR");
//                    params.put("text", twok.getText());
//                    params.put("bgcol", "000000");
//                    params.put("fontcol", "00aeef");
//                    params.put("fonttype", "44");
//                    params.put("halign", "1");
//                    params.put("valign", "1");
//                    params.put("lan", "");
//                    params.put("lon", "");
//                    return params;
//                }
//            };
//            queue = Volley.newRequestQueue(getActivity());
//
//// Add the request to the RequestQueue.
//            queue.add(stringRequest);
//
//            System.err.println(queue);
//            System.err.println(stringRequest.toString());


            ////SECONDO MODO

            //todo risolvere con il terzo metodo

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("sid", "qaKOeIk1DhEvBLOruWaR");
                jsonObject.put("text", twok.getText());
                jsonObject.put("bgcol", "000000");
                jsonObject.put("fontcol", "00aeef");
                jsonObject.put("fontsize", 44);
                jsonObject.put("fonttype", 2);
                jsonObject.put("halign", 1);
                jsonObject.put("valign", 1);
                jsonObject.put("lan", "");
                jsonObject.put("lon", "");
                jsonArray.put(jsonObject);
                Log.d("jsonString", jsonObject.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("Response", response.toString());

                            System.err.println("Response"+ response.toString());
                            //("Response", response.toString());
                            try {
                                JSONArray array = response.getJSONArray("data");
                                //parseData(array);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error", error.toString());
                            System.err.println("Error"+ error.toString());
                            String json = null;
                            NetworkResponse networkResponse = error.networkResponse;
                            if (networkResponse != null && networkResponse.data != null) {
                                switch (networkResponse.statusCode) {
                                    case 400:
                                        json = new String(networkResponse.data);
                                        System.err.println(json);
                                        break;
                                }//additional cases
                            }
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();
                    //headers.put("Authorization",finalToken);
                    return headers;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(jsonObjectRequest);
*/

        });
    }




    // the dialog functionality is handled separately
    // using openColorPickerDialog this is triggered as
    // soon as the user clicks on the Pick Color button And
    // the AmbilWarnaDialog has 2 methods to be overridden
    // those are onCancel and onOk which handle the "Cancel"
    // and "OK" button of color picker dialog
    public void openColorPickerDialogue(int i) {

        // the AmbilWarnaDialog callback needs 3 parameters
        // one is the context, second is default color,
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(getActivity(), mDefaultColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // leave this function body as
                        // blank, as the dialog
                        // automatically closes when
                        // clicked on cancel button
                    }
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        // change the mDefaultColor to
                        // change the GFG text color as
                        // it is returned when the OK
                        // button is clicked from the
                        // color picker dialog

                        //0 = font 1= bg
                        if(i==0){//font
                            mDefaultColor = color;

                            // now change the picked color
                            // preview box to mDefaultColor
                            mColorPreview.setBackgroundColor(mDefaultColor);
                        }else if(i==1){//bg
                            mDefaultColorBG = color;

                            // now change the picked color
                            // preview box to mDefaultColor
                            mColorPreviewBG.setBackgroundColor(mDefaultColorBG);
                        }
                    }
                });
        colorPickerDialogue.show();
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        System.err.println(this);
        System.err.println(adapterView.getId());
        System.err.println(view.getId());
        System.err.println(view.findViewById(R.id.textViewText));

        if (view.getId() == R.id.spinnerH) {
            System.err.println("pulsante premuto corretto");
        } else {
            System.err.println("pulsante sbaglaito");
        }

        //adapterView.
        TextView textView = view.findViewById(R.id.textViewText);
        //System.err.println(textView.getText());
        System.err.println("textView" + textView);
        Log.d("ciaomondo", "ciaomondo");
        System.err.println(adapterView.getItemAtPosition(i));
        //textView.setGravity(1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback

    }


    //////////////////////////////////////////////////////////////



    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            System.err.println("location"+location);
                            if (location !=null){
                                System.err.println("location dentro if");
                                Geocoder geocoder=new Geocoder(getActivity(), Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//                                    latitude.setText("Lagitude :" +addresses.get(0).getLatitude());
//                                    longitude.setText("Longitude :"+addresses.get(0).getLongitude());
//                                    address.setText("Address :"+addresses.get(0).getAddressLine(0));
//                                    city.setText("City :"+addresses.get(0).getLocality());
//                                    country.setText("Country :"+addresses.get(0).getCountryName());

                                    Log.d("myPosition", String.valueOf(addresses.get(0).getLatitude()));
                                    Log.d("myPosition", String.valueOf(addresses.get(0).getLongitude()));
                                    Log.d("myPosition", String.valueOf(addresses.get(0).getAddressLine(0)));
                                    Log.d("myPosition", String.valueOf(addresses.get(0).getLocality()));
                                    Log.d("myPosition", String.valueOf(addresses.get(0).getCountryName()));

                                    System.out.println("address"+addresses.get(0));
                                    System.err.println("Another Location");

                                    Toast.makeText(getActivity(), "location ottenuta", Toast.LENGTH_SHORT).show();
                                    //twok.setLat(45);
                                    //twok.setLon(11);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity(), "locotion error", Toast.LENGTH_SHORT).show();
                                    System.err.println("catch??");
                                }



                            }else{
                                //todo funziona solamente se il gps è attivato da un po altyriensi entri qui
                                //alert attiva il gps per questa funzionalità
                                AlertDialog.Builder myAlert = new AlertDialog.Builder(getActivity());
                                myAlert.setTitle("Errore");
                                myAlert.setMessage("GPS non attivo");
                                myAlert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getActivity(), "ok click", Toast.LENGTH_SHORT).show();

                                        //todo inserire il codice che attiva il gps dello smarphone
                                    }
                                });
                                myAlert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getActivity(), "no click", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }
                    });


        }else
        {

            askPermission();

        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
            else {
                Toast.makeText(getActivity(), "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
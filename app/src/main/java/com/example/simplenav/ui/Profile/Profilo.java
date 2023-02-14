package com.example.simplenav.ui.Profile;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplenav.CommucationController.GetTwok;
import com.example.simplenav.CommucationController.OnSetProfileListener;
import com.example.simplenav.CommucationController.RetrofitClient;
import com.example.simplenav.CommucationController.communicationController;
import com.example.simplenav.CommucationController.getProfile;
import com.example.simplenav.CommucationController.getProfileO;
import com.example.simplenav.CommucationController.setProfileI;
import com.example.simplenav.DB.PictureDB.Sid;
import com.example.simplenav.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.util.Base64;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profilo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profilo extends Fragment implements EasyPermissions.PermissionCallbacks{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    //sid
    public static final String mypreference = "mypref";



    Sid sid = new Sid();



    public Profilo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profilo.
     */
    // TODO: Rename and change types and number of parameters
    public static Profilo newInstance(String param1, String param2) {
        Profilo fragment = new Profilo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //Todo fare il controllo sulla dimensione dell'immagine
    //todo mettere degli alertt per indire la creazioen con successo
    //initialize variable
    Button btPick,btnChangePicture,bt_pickName;
    RecyclerView recyclerView;

    ArrayList<Uri> arrayList = new ArrayList<>();

    String imageSelcted = null;

    TextView textViewName;

    ImageView OldImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profilo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);


        //assign variable
        btPick = view.findViewById(R.id.bt_pick);
        recyclerView = view.findViewById(R.id.recyclerviewProfilo);
        btnChangePicture = view.findViewById(R.id.btnChangePicture);

        bt_pickName = view.findViewById(R.id.bt_pickName);
        bt_pickName.setEnabled(false);
        textViewName = view.findViewById(R.id.textViewName);

        OldImage = view.findViewById(R.id.OldImage);

        //GESTISCO IL CASO DI GET TWOK CIOè NEL MOMENTO IN CUI ENTRO SULLA PAGINE DEVO ANDARE A RECUPERARE
        // LE INFORMAZIONI RIGUARDANTE L'ACCOUNT


        System.err.println("prova SID"+sharedpreferences.contains("sid"));
        System.err.println("prova SID"+sharedpreferences.getString("sid",""));
        sid.setSid(sharedpreferences.getString("sid",""));


        communicationController.getProfile(body -> {
            Log.d("Prodilo","chiamata avvenuta con susccesso");
            System.err.println("body get profile" + body);
            textViewName.setText(body.getName());

            //gestire il caso in cui immagine sia null

            if(body.getPicture()==null){
                //immagine non formattata correttamente null
                OldImage.setBaseline(R.drawable.ic_baseline_account_circle_24);

            }else{
                //se immagine è presente
                byte[] decodedString = Base64.decode(body.getPicture(),Base64.DEFAULT);
                Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
                OldImage.setImageBitmap(decodeByte);
            }

            //mettere un toas profilo impostato correttamente

        },sid);

//
//        getProfile(new getProfile() {
//            @Override
//            public void onGetProfile(getProfileO body) {
//                System.err.println("body get profile metodo 2"+body);
//            }
//        });
//
//        GetTwok getTwok = new GetTwok();
//        Call<getProfileO> call = RetrofitClient.getInstance().getMyApi().getProfile(getTwok);
//        call.enqueue(new Callback<getProfileO>() {
//            @Override
//            public void onResponse(Call<getProfileO> call, Response<getProfileO> response) {
//                System.err.println("Response getProfile metodo 3");
//                System.err.println("geProle3"+response);
////                onSetProfileListener.onGetProfile(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<getProfileO> call, Throwable t) {
//                System.err.println("Failure 3getProfile");
//            }
//        });


        //set listerner
        btPick.setOnClickListener(v ->{
            //definisco i permessi
            String[] strings = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};

            //Check condition
            if (EasyPermissions.hasPermissions(getActivity(),strings)){//qui this
                //when permission are already granted
                //create method
                imagePicker();
            }else{
                //when permission non granted
                //request permission
                EasyPermissions.requestPermissions(
                        this,//qui this
                        "App needs access to your camera & storage",
                        100,
                        strings
                );
            }
        });


        btnChangePicture.setOnClickListener(v ->{
            setProfileI profile = new setProfileI();
            profile.setSid(sid.getSid());
            profile.setPicture(imageSelcted);
            profile.setName(textViewName.getText().toString());
            Call<Void> call2 = RetrofitClient.getInstance().getMyApi().setProfile(profile);
            call2.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    System.err.println("Response");
                    System.err.println(response);
                    System.err.println(response.body());



                    int duration = Toast.LENGTH_SHORT;
                    CharSequence text = "Hello toast!";

                    //todo gestire i casi di errore
                    //mostrsre un alrt che dics che l'operaxoine è andata a buon fine
                    int code = response.code();
                    if (code >= 200 && code < 300) {
                        text = "operazione eseguita con successo"+code;
                    } else if (code == 401) {
                        text = "errore client profilo!"+code;
                    } else if (code >= 400 && code < 500) {
                        text = "errore client profilo!"+code;
                    } else if (code >= 500 && code < 600) {
                        text = "errore server profilo!"+code;
                    } else {
                        text = "ERROR IMPOSSIBLE PROFILO";
                    }
                    Toast toast = Toast.makeText(getActivity(), text, duration);
                    toast.show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.err.println("Failure");
                    t.printStackTrace();
                }
            });
//            setProfileI profile = new setProfileI();
//            System.err.println("Change image");
//            communicationController.setProfile(new OnSetProfileListener() {
//                @Override
//                public void onSetProfile(Void body) {
//                    System.err.println("body"+body);
//                }
//            },profile);
        });

        //uses-permission android:name="android.permission.CAMERA"/
        //uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //handle the request result
        System.err.println("onRquestPermissionResult deprecato");
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);//this
    }

    //TODO CREARE UNA GUIDA SUI FILE NECESSARI ED I PASSI PER CREARE UNA LISTA

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.err.println("onActivityResult deprecato");
        super.onActivityResult(requestCode, resultCode, data);
        //check condition
        if (resultCode == RESULT_OK && data != null){
            System.err.println("dentro if 1");
            //System.err.println("URI->"+uri);
            System.err.println(data.getData());
            //when activity contain data
            //check condition
            if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO){
                Uri uri = data.getData();
                System.err.println("dentro if 2");
                arrayList = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                System.err.println("array"+arrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//this
                //set adapter
                recyclerView.setAdapter(new ProfileAdp(arrayList));

                try {
                    System.err.println("URI->"+uri);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),arrayList.get(0));

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream);

                    byte[] bytes = stream.toByteArray();

                    String s = Base64.encodeToString(bytes,Base64.DEFAULT);
                    //System.err.println("String"+s);

                    //controllo sulla lunghezza maZssima
                    if(s.length()>=137000){
                        Toast.makeText(getContext(), "immagine formato non valido (dim)", Toast.LENGTH_SHORT).show();
                        //profile.setPictur(R.drawable.ic_baseline_account_circle_24);
                    }

                    imageSelcted = s;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private void imagePicker() {
        //open Picker
        FilePickerBuilder.getInstance()
                .setActivityTitle("Select Images")
                .setSpan(FilePickerConst.SPAN_TYPE.FOLDER_SPAN,3)
                .setSpan(FilePickerConst.SPAN_TYPE.DETAIL_SPAN,4)
                .setMaxCount(4)
                .setSelectedFiles(arrayList)
                .setActivityTheme(R.style.CustomTheme)
                .pickPhoto(this);//qui this

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //check condition
        if (requestCode == 100 && perms.size() == 2){
            imagePicker();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }else{
            Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }




    //prova

    public void getProfile(getProfile onSetProfileListener){
        GetTwok getTwok = new GetTwok();
        Call<getProfileO> call = RetrofitClient.getInstance().getMyApi().getProfile(getTwok);
        call.enqueue(new Callback<getProfileO>() {
            @Override
            public void onResponse(Call<getProfileO> call, Response<getProfileO> response) {
                System.err.println("Response getProfile");
                System.err.println("geProle"+response);
                onSetProfileListener.onGetProfile(response.body());
            }

            @Override
            public void onFailure(Call<getProfileO> call, Throwable t) {
                System.err.println("Failure getProfile");
            }
        });

    }
}
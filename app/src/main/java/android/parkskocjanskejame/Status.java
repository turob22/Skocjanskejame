package android.parkskocjanskejame;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.Functions;
import android.parkskocjanskejame.utils.GPSTracker;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Status extends AppCompatActivity {
    GPSTracker gpsTracker;
    double latitude, longitude;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    Activity statusActivity = null;
    AlertDialog alert;

    public Integer[] images =
            {R.drawable.znacka1, R.drawable.znacka2,
                    R.drawable.znacka3, R.drawable.znacka4,
                    R.drawable.znacka5, R.drawable.znacka6,
                    R.drawable.znacka7, R.drawable.znacka8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);

        statusActivity = this;
        ImageView znacka1 = (ImageView) findViewById(R.id.znacka1);
        ImageView znacka2 = (ImageView) findViewById(R.id.znacka2);
        ImageView znacka3 = (ImageView) findViewById(R.id.znacka3);
        ImageView znacka4 = (ImageView) findViewById(R.id.znacka4);
        ImageView znacka5 = (ImageView) findViewById(R.id.znacka5);
        ImageView znacka6 = (ImageView) findViewById(R.id.znacka6);
        ImageView znacka7 = (ImageView) findViewById(R.id.znacka7);
        ImageView znacka8 = (ImageView) findViewById(R.id.znacka8);

        ImageView znacka9 = (ImageView) findViewById(R.id.znacka9);
        ImageView znacka10 = (ImageView) findViewById(R.id.znacka10);
        ImageView znacka11 = (ImageView) findViewById(R.id.znacka11);
        ImageView znacka12 = (ImageView) findViewById(R.id.znacka12);


        final ImageView[] imageViews = {znacka1, znacka2, znacka3, znacka4, znacka5, znacka6, znacka7, znacka8, znacka9, znacka10, znacka11, znacka12};

//ODKLENJENE 3D VSEBINE
Constants.alpha[8]=true;
Constants.alpha[9]=true;
Constants.alpha[10]=true;
Constants.alpha[11]=true;
        for (int i = 0; i < Constants.alpha.length; i++) {

            if (Constants.alpha[i] == true) {
                imageViews[i].setAlpha(1f);
            }
        }

        statusPopup(znacka1, 0);
        statusPopup(znacka2, 1);
        statusPopup(znacka3, 2);
        statusPopup(znacka4, 3);
        statusPopup(znacka5, 4);
        statusPopup(znacka6, 5);
        statusPopup(znacka7, 6);

        statusPopup(znacka9, 8);
        statusPopup(znacka10, 9);
        statusPopup(znacka11, 10);
        statusPopup(znacka12, 11);

        TextView statusText2 = (TextView) findViewById(R.id.statusText2);
        statusText2.setText(Integer.toString(Constants.status));
        statusText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NFCScan.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView reward1 = (ImageView) findViewById(R.id.znacka9);
        reward1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reward.class);
                startActivity(intent);
            }
        });

        ImageView help = (ImageView) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.helpPopup(Status.this);
            }
        });

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
                // If any permission above not allowed by user, this condition will
                // execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        gpsTracker = new GPSTracker(Status.this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else {
            gpsTracker.showSettingsAlert();
        }
    }

    public void statusPopup(ImageView image, final int id) {
        if (Constants.alpha[id] == true) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(Status.this);
                    LayoutInflater layoutInflater = (LayoutInflater) Status.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = layoutInflater.inflate(R.layout.statuspopup, null);*/

                    TextView naziv = null;
                    if(id<8) {
                        final Dialog alertDialog = new Dialog(statusActivity, R.style.AppTheme);
                        alertDialog.setContentView(R.layout.statuspopup);
                        alertDialog.show();

                        ImageView imageView = (ImageView) alertDialog.findViewById(R.id.statuspopupImage);
                        imageView.setImageResource(images[id]);


                        Button helpButton = (Button) alertDialog.findViewById(R.id.helpButton);

                        /*TextView t2 = (TextView) alertDialog.findViewById(R.id.helppopupText2);
                        t2.setMovementMethod(LinkMovementMethod.getInstance());*/

                        helpButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });
                        naziv = (TextView) alertDialog.findViewById(R.id.naziv);
                    }

                    Intent intent;
                    switch (id) {
                        case 0:
                            naziv.setText(R.string.cestitamo1b);
                            break;
                        case 1:
                            naziv.setText(R.string.cestitamo3b);
                            break;
                        case 2:
                            naziv.setText(R.string.cestitamo4b);
                            break;
                        case 3:
                            naziv.setText(R.string.cestitamo7b);
                            break;
                        case 4:
                            naziv.setText(R.string.cestitamo10b);
                            break;
                        case 5:
                            naziv.setText(R.string.cestitamo16b);
                            break;
                        case 6:
                            naziv.setText(R.string.cestitamo19b);
                            break;
                        case 7:
                            naziv.setText(R.string.cestitamo26b);
                            break;
                        case 8:
                            intent = new Intent(statusActivity, MyWebView.class);
                            intent.putExtra("url", getString(R.string.sova));
                            startActivity(intent);
                            break;
                        case 9:
                            intent = new Intent(statusActivity, MyWebView.class);
                            intent.putExtra("url", getString(R.string.zolna));
                            startActivity(intent);
                            break;
                        case 10:
                            intent = new Intent(statusActivity, MyWebView.class);
                            intent.putExtra("url", getString(R.string.lastovka));
                            startActivity(intent);
                            break;
                        case 11:
                            intent = new Intent(statusActivity, MyWebView.class);
                            intent.putExtra("url", getString(R.string.sokol));
                            startActivity(intent);
                            break;


                    }
                    /*ImageView close = (ImageView) alertDialog.findViewById(R.id.helpButton);
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert.cancel();
                        }
                    });*/
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
    }
}

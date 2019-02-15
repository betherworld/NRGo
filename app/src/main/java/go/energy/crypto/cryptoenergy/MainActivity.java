package go.energy.crypto.cryptoenergy;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import org.w3c.dom.Text;

import java.math.BigInteger;

import go.energy.crypto.cryptoenergy.contracts.ItemFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private boolean firstTime=true;
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(Menu.NONE, R.layout.menu, 1, "EnergyGo");
        item.setIcon(R.drawable.icon);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        updateLevel();
    }

    private void updateLevel(){
        if (firstTime){
            DatabaseHelper.createItem(DatabaseHelper.USER_ADDRESS, 3, 4, 0,1,0,0);
            firstTime=false;
        }
        Element element = DatabaseHelper.getItem(DatabaseHelper.USER_ADDRESS);

        Random rand = new Random();

        TextView l = (TextView) findViewById(R.id.cur_level_int);
        int level, progress0, progress1, progress2, progress3, progress4, progress5, progressFr;
        if ( element != null ) {
            level = element.getLevel();
            progress0 = element.percentageToNextLevel();
            progress1 = element.getWaterProgress();
            progress4 = element.getElectricityProgress();
            progressFr = element.getFireProgress();
            progress2 = element.getLeafProgress();
            progress3 = element.getAirProgress();
            progress5 = element.getSpecialProgress();
        } else {
            level = rand.nextInt(100);
            progress0 = rand.nextInt(100);
            progress1 = rand.nextInt(100);
            progress4 = rand.nextInt(100);
            progressFr = rand.nextInt(100);
            progress2 = rand.nextInt(100);
            progress3 = rand.nextInt(100);
            progress5 = rand.nextInt(100);
            Log.w("BLOCKCHAIN", "Failed to connect");
        }
        l.setText(""+level);
        System.out.println("Level is "+level);

        NumberProgressBar progressBar = (NumberProgressBar)findViewById(R.id.number_progress_bar);
        progressBar.setProgress(progress0);

        CustomArcProgress progressFront = (CustomArcProgress) findViewById(R.id.circle_progress_bar_fire);
        progressFront.setProgress(progressFr);

        CustomArcProgress progressBar1 = (CustomArcProgress) findViewById(R.id.circle_progress_bar_drop);

        progressBar1.setProgress(progress1);
        System.out.println("Water progress: " + progress1);

        CustomArcProgress progressBar2 = (CustomArcProgress) findViewById(R.id.circle_progress_bar_leaf);
        progressBar2.setProgress(progress2);

        CustomArcProgress progressBar3 = (CustomArcProgress) findViewById(R.id.circle_progress_bar_air);
        progressBar3.setProgress(progress3);

        CustomArcProgress progressBar4 = (CustomArcProgress) findViewById(R.id.circle_progress_bar_electricity);
        progressBar4.setProgress(progress4);

        CustomArcProgress progressBar5 = (CustomArcProgress) findViewById(R.id.circle_progress_bar_special);
        progressBar5.setProgress(progress5);
        progressBar5.setProgress(element.getSpecialProgress());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateLevel();
        Random rand = new Random();

        Resources res = getResources();
        final String[] elem_id = res.getStringArray(R.array.elemental_ids);

        TypedArray ar = res.obtainTypedArray(R.array.elemental_pictures);
        int len = ar.length();
        final HashMap<String,ArrayList<Integer>> pictureIds = new HashMap<String,ArrayList<Integer>>();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();
        final String[] elem_desc = res.getStringArray(R.array.elemental_description);
        final HashMap<String,String> elem_desc_hash = new HashMap<String,String>();
        for ( int i = 0; i < resIds.length; i++ ) {
            TypedArray tr = res.obtainTypedArray(resIds[i]);
            ArrayList<Integer> resId2s = new ArrayList<Integer>();
            for (int j = 0; j < tr.length(); j++)
                resId2s.add( ar.getResourceId(j, 0) );
            tr.recycle();
            pictureIds.put(elem_id[i],resId2s);
            elem_desc_hash.put(elem_id[i],elem_desc[i]);
        }

        Button changeText = (Button)findViewById(R.id.quests);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView tw = (TextView)findViewById(R.id.drop);
                //tw.setText("Java");
                Intent intent = new Intent(MainActivity.this, QuestActivity.class);
//                DatabaseHelper.createItem(DatabaseHelper.USER_ADDRESS, 3, 4);
                System.out.println("Hello");
                startActivity(intent);
            }
        });

        Button btnResources = (Button)findViewById(R.id.resources);
        btnResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResourcesActivity.class);
                startActivity(intent);
            }
        });

        Button logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, LoginActivity.class);
                MainActivity.this.deleteFile(LoginActivity.loginFile);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        ImageView imgDrop = (ImageView) findViewById(R.id.img_drop);
        imgDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                ArrayList<Integer> ids = pictureIds.get("Water");
                int size = ids.size() - 1;
                int n = rand.nextInt(size)+1;
                intent.putExtra("Desc",elem_desc_hash.get("Water"));
                intent.putExtra("Image",ids.get(n));
                startActivity(intent);
            }
        });

        ImageView imgFire = (ImageView) findViewById(R.id.img_fire);
        imgFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                ArrayList<Integer> ids = pictureIds.get("Fire");
                int size = ids.size() - 1;
                int n = rand.nextInt(size)+1;
                intent.putExtra("Desc",elem_desc_hash.get("Fire"));
                intent.putExtra("Image",ids.get(n));
                startActivity(intent);
            }
        });

        ImageView imgLeaf = (ImageView) findViewById(R.id.img_leaf);
        imgLeaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                ArrayList<Integer> ids = pictureIds.get("Earth");
                int size = ids.size() - 1;
                int n = rand.nextInt(size)+1;
                intent.putExtra("Desc",elem_desc_hash.get("Earth"));
                intent.putExtra("Image",ids.get(n));
                startActivity(intent);
            }
        });

        ImageView imgElectricity = (ImageView) findViewById(R.id.img_electricity);
        imgElectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                ArrayList<Integer> ids = pictureIds.get("Electricity");
                int size = ids.size() - 1;
                int n = rand.nextInt(size)+1;
                intent.putExtra("Desc",elem_desc_hash.get("Electricity"));
                intent.putExtra("Image",ids.get(n));
                startActivity(intent);
            }
        });

        ImageView imgAir = (ImageView) findViewById(R.id.img_air);
        imgAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                ArrayList<Integer> ids = pictureIds.get("Air");
                int size = ids.size() - 1;
                int n = rand.nextInt(size)+1;
                intent.putExtra("Desc",elem_desc_hash.get("Air"));
                intent.putExtra("Image",ids.get(n));
                startActivity(intent);
            }
        });

        ImageView imgSpecial = (ImageView) findViewById(R.id.img_special);
        imgSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                ArrayList<Integer> ids = pictureIds.get("Special");
                int size = ids.size() - 1;
                int n = rand.nextInt(size)+1;
                intent.putExtra("Desc",elem_desc_hash.get("Special"));
                intent.putExtra("Image",ids.get(n));
                startActivity(intent);
            }
        });
    }
}

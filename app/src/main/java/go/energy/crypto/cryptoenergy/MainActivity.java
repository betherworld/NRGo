package go.energy.crypto.cryptoenergy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import org.w3c.dom.Text;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberProgressBar progressBar = (NumberProgressBar)findViewById(R.id.number_progress_bar);
        progressBar.setProgress(10);

        ProgressBar progressFront = (ProgressBar) findViewById(R.id.circle_progress_bar_fire_front);
        progressFront.setProgress(33);

        ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.circle_progress_bar_drop_front);
        progressBar1.setProgress(33);

        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.circle_progress_bar_leaf_front);
        progressBar2.setProgress(33);

        ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.circle_progress_bar_air_front);
        progressBar3.setProgress(33);

        ProgressBar progressBar4 = (ProgressBar) findViewById(R.id.circle_progress_bar_electricity_front);
        progressBar4.setProgress(33);

        ProgressBar progressBar5 = (ProgressBar) findViewById(R.id.circle_progress_bar_special_front);
        progressBar5.setProgress(33);

        Button changeText = (Button)findViewById(R.id.quests);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView tw = (TextView)findViewById(R.id.drop);
                //tw.setText("Java");
                Intent intent = new Intent(MainActivity.this, QuestActivity.class);
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
                startActivity(intent);
            }
        });

        ImageView imgFire = (ImageView) findViewById(R.id.img_fire);
        imgFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgLeaf = (ImageView) findViewById(R.id.img_leaf);
        imgLeaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgElectricity = (ImageView) findViewById(R.id.img_electricity);
        imgElectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgAir = (ImageView) findViewById(R.id.img_air);
        imgAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgSpecial = (ImageView) findViewById(R.id.img_special);
        imgSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                startActivity(intent);
            }
        });
    }
}

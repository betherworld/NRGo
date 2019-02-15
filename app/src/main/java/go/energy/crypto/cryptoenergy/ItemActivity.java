package go.energy.crypto.cryptoenergy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigInteger;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_listview);
        Button b = (Button) findViewById(R.id.btn_item_reward_proof);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ItemActivity.this, VerificationActivity.class);
//                startActivity(intent);
                Intent intent = getIntent();
                Button b = (Button)v;
                b.setText(getString(R.string.collect));
                Resources res = getResources();
                ElementEnum elementEnum = (ElementEnum) intent.getSerializableExtra("ElementEnum");
                int reward = intent.getIntExtra("Reward",0);
                if (reward!=0){
                    // Todo increment coins
                    Element element = DatabaseHelper.getItem(DatabaseHelper.USER_ADDRESS);
                    element.update(elementEnum, reward);
                    DatabaseHelper.createItem(DatabaseHelper.USER_ADDRESS, element.getDnaWater(), element.getDnaElectricity(), element.getDnaFire(),element.getDnaLeaf(),element.getDnaAir(),element.getDnaSpecial());

//                    DatabaseHelper.updateItem(elementEnum, DatabaseHelper.USER_ADDRESS, reward);
                    System.out.println("Elementis "+element);
                }
//                Element el = new Element(name, newCoins);
//                databaseHelper.updatePoints(el);
            }
        });
        Intent intent = getIntent();
//        intent.getIn
        String text = intent.getStringExtra("Quest");
        int icon_id = intent.getIntExtra("Image",-1);
        ImageView im = (ImageView) findViewById(R.id.imageView);
        im.setImageResource(icon_id);
        TextView tv = (TextView) findViewById(R.id.quest_description);
        tv.setText(text);

        //int activity = intent.getIntExtra("ActivityType", -1);

        //if(activity > 0) {
        int reward = intent.getIntExtra("Reward", 0);
        TextView tv_reward = (TextView) findViewById(R.id.quest_reward);
        tv_reward.setText("" + reward + " X");

        int reward_img_id = intent.getIntExtra("Type", -1);
        ;
        ImageView reward_view = (ImageView) findViewById(R.id.reward_view);
        reward_view.setImageResource(reward_img_id);
        //}

    }
}

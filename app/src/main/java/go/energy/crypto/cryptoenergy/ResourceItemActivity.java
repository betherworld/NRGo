package go.energy.crypto.cryptoenergy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResourceItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_listview);
        Button b = (Button) findViewById(R.id.btn_item_reward_proof);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourceItemActivity.this, VerificationActivity.class);
                startActivity(intent);
                Button b = (Button)v;
                b.setText(getString(R.string.collect));
            }
        });
        Intent intent = getIntent();
        String text = intent.getStringExtra("Resource");
        int icon_id = intent.getIntExtra("Image",-1);
        ImageView im = (ImageView) findViewById(R.id.imageView);
        im.setImageResource(icon_id);
        TextView tv = (TextView) findViewById(R.id.quest_description);
        tv.setText(text);

    }
}

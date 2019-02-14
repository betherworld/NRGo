package go.energy.crypto.cryptoenergy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_listview);
        Button b = (Button) findViewById(R.id.btn_item_reward_proof);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemActivity.this, VerificationActivity.class);
                startActivity(intent);
                Button b = (Button)v;
                b.setText(getString(R.string.collect));
            }
        });
    }
}

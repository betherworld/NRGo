package go.energy.crypto.cryptoenergy;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ItemActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private final AppCompatActivity activity = ItemActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_listview);
        Button b = (Button) findViewById(R.id.btn_item_reward_proof);

        databaseHelper = new DatabaseHelper(activity, null, this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemActivity.this, VerificationActivity.class);
                startActivity(intent);
                Button b = (Button)v;
                b.setText(getString(R.string.collect));

                Resources res = getResources();
                String [] names = res.getStringArray(R.array.quests_rewards_types);
                String name = names[0];
                int [] rewards = res.getIntArray(R.array.quests_rewards);
                int reward = 1;
                //Element el = new Element();
                //databaseHelper.updatePoints();
                /*Cursor cursor = db.query(TABLE_USER,
                        columns,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);
                int cursorCount = cursor.getCount();*/
            }
        });
    }
}

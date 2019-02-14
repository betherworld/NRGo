package go.energy.crypto.cryptoenergy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class QuestActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        Resources res = getResources();
        TypedArray ar = res.obtainTypedArray(R.array.quest_pics);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();
        String[] quests = res.getStringArray(R.array.quests);
        Quest quest_data[] = new Quest[quests.length];
        for ( int i = 0; i < quest_data.length; i++ ) {
            quest_data[i] = new Quest(resIds[i], quests[i]);
        }
        QuestAdapter adapter = new QuestAdapter(this,
                R.layout.listview_item_row, quest_data);


        listView = (ListView)findViewById(R.id.simpleListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long a){
                Quest item = (Quest)adapter.getItemAtPosition(position);
                Intent intent = new Intent(QuestActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });
    }
}

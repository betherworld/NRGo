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
import android.widget.ImageView;
import android.widget.ListView;

import java.util.HashMap;

public class QuestActivity extends AppCompatActivity {
    private HashMap<Integer, ElementEnum> questType = new HashMap<>();
    private ListView listView;

    private ElementEnum parseQuestType(String str){ // eg str == go.energy.crypto.cryptoenergy:drawable/energie
        String[] tmp = str.split("/");
        String name=tmp[tmp.length-1];
        switch (name){
            case "energie": return ElementEnum.ELECTRICITY;
            case "leaf": return ElementEnum.LEAF;
            case "drop": return ElementEnum.WATER;
            case "cloud": return ElementEnum.AIR;
            case "fire": return ElementEnum.FIRE;
        }
        return null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        Resources res = getResources();
        TypedArray ar = res.obtainTypedArray(R.array.quests_pics_1);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();

        TypedArray ar_types = res.obtainTypedArray(R.array.quests_rewards_types);
        int len_types = ar_types.length();
        int [] quest_reward_types = new int[len_types];
        for (int i = 0; i < len_types; i++) {
            quest_reward_types[i] = ar_types.getResourceId(i, 0);
            ElementEnum elementEnum = parseQuestType(ar_types.getResources().getResourceName(quest_reward_types[i]));
            questType.put(quest_reward_types[i], elementEnum);
        }
        final String [] quest_content = res.getStringArray(R.array.quests_discriptions_1);
        final int [] quest_rewards = res.getIntArray(R.array.quests_rewards);
        final String[] quests = res.getStringArray(R.array.quests_1);
        final Quest quest_data[] = new Quest[quests.length];
        for ( int i = 0; i < quest_data.length; i++ ) {
            quest_data[i] = new Quest(resIds[i], quests[i], quest_content[i], quest_rewards[i], quest_reward_types[i]);
        }
        QuestAdapter adapter = new QuestAdapter(this,
                R.layout.listview_item_row, quest_data);


        listView = (ListView)findViewById(R.id.simpleListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long a){
                Quest item = (Quest)adapter.getItemAtPosition(position);
                Intent myIntent = new Intent(QuestActivity.this, ItemActivity.class);
                myIntent.putExtra("ActivityType", 0);
                myIntent.putExtra("Quest", item.content);
                myIntent.putExtra("Image",item.icon);
                myIntent.putExtra("Reward", item.pointsOfQuest);
                myIntent.putExtra("Type", item.typeId);
                myIntent.putExtra("ElementEnum", questType.get(item.typeId));
                startActivity(myIntent);
            }
        });
    }
}

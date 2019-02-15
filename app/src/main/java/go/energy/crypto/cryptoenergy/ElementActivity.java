package go.energy.crypto.cryptoenergy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_info);
        Intent intent = getIntent();
        String text = intent.getStringExtra("Desc");
        int icon_id = intent.getIntExtra("Image",-1);
        ImageView im = (ImageView) findViewById(R.id.imageView);
        im.setImageResource(icon_id);
        TextView tv = (TextView) findViewById(R.id.element_description);
        tv.setText(text);
    }
}

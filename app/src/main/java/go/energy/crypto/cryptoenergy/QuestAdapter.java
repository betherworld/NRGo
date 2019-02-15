package go.energy.crypto.cryptoenergy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestAdapter extends ArrayAdapter<Quest> {

    Context context;
    int layoutResourceId;
    Quest data[] = null;

    public QuestAdapter(Context context, int layoutResourceId, Quest[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        QuestHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new QuestHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.imgElementType = (ImageView)row.findViewById(R.id.imgElementType);

            row.setTag(holder);
        }
        else
        {
            holder = (QuestHolder)row.getTag();
        }

        Quest quest = data[position];
        holder.txtTitle.setText(quest.title);
        holder.imgIcon.setImageResource(quest.icon);
        if(quest.typeId > 0)
            holder.imgElementType.setImageResource(quest.typeId);

        return row;
    }

    static class QuestHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        ImageView imgElementType;
    }
}
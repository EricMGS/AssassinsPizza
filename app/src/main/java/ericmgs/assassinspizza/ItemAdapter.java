package ericmgs.assassinspizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView sabor = (TextView) convertView.findViewById(R.id.sabor);
        TextView tamanho = (TextView) convertView.findViewById(R.id.tamanho);

        if (item.inteira == true)
            sabor.setText(item.sabor1);
        else
            sabor.setText(item.sabor1 + " / " + item.sabor2);
        tamanho.setText(item.tamanho);
        return convertView;
    }
}

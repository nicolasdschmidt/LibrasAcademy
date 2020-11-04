package br.unicamp.cotuca.librasacademy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.unicamp.cotuca.librasacademy.dbo.Licao;

public class LicaoAdapter extends BaseAdapter {
    private Context context;
    private List<Licao> licoes;

    public LicaoAdapter(Context context, List<Licao> licoes) {
        this.context = context;
        this.licoes = licoes;
    }

    @Override
    public int getCount() {
        return licoes.size();
    }

    @Override
    public Object getItem(int position) {
        return licoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listView = inflater.inflate(R.layout.item_row, null);
        TextView title = (TextView) listView.findViewById(R.id.text_title);
        TextView description = (TextView) listView.findViewById(R.id.text_description);
        TextView autor = (TextView) listView.findViewById(R.id.text_author);

        title.setText(licoes.get(position).getNome());
        description.setText(licoes.get(position).getDescricao());
        autor.setText("");

        return listView;
    }
}
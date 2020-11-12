package br.unicamp.cotuca.librasacademy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import br.unicamp.cotuca.librasacademy.HttpManager;
import br.unicamp.cotuca.librasacademy.R;
import br.unicamp.cotuca.librasacademy.VolleyCallback;
import br.unicamp.cotuca.librasacademy.WordActivity;

public class DictionaryFragment extends Fragment {
    ArrayList<String> alphabet = new ArrayList(Arrays.asList(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));
    private Context context;
    private ListView itens;
    private ImageButton search;
    private EditText txtSearch;

    private String server;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        server = getResources().getString(R.string.server);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itens = (ListView) view.findViewById(R.id.list_dictionary);
        search = (ImageButton) view.findViewById(R.id.buttonSearch);
        txtSearch = (EditText) view.findViewById(R.id.edtTextSerach);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( context, android.R.layout.simple_list_item_1, alphabet);

        itens.setAdapter(adapter);
        itens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment words_fragment = new Dictionary2Fraagment();

                Bundle letter = new Bundle();
                letter.putString("letter", alphabet.get(i));

                words_fragment.setArguments(letter);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, words_fragment).commit();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent word_data = new Intent(getActivity(), WordActivity.class);

                Bundle mWord = new Bundle();
                mWord.putString("word", txtSearch.getText().toString());

                word_data.putExtras(mWord);
                startActivity(word_data);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_dictionary, container, false);
    }
}

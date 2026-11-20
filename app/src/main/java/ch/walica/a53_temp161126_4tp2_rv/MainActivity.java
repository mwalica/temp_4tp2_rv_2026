package ch.walica.a53_temp161126_4tp2_rv;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ch.walica.a53_temp161126_4tp2_rv.model.Person;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private List<Person> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);

        people.add(new Person("Jan"));
        people.add(new Person("Adam"));
        people.add(new Person("Gustaw"));
        people.add(new Person("Karol"));


        //okrślam layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //utworzenie adapter
        PersonAdapter adapter = new PersonAdapter(people);
        //podłączenie adaptera
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(view -> {
            View editView = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_text_layout, null);
            EditText etName = editView.findViewById(R.id.etName);

            new MaterialAlertDialogBuilder(MainActivity.this)
                    .setTitle("Dodaj osobę")
                    .setView(editView)
                    .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String name = etName.getText().toString().trim();
                            if(!name.isEmpty()) {
                                Person person = new Person(name);
                                people.add(person);
                                adapter.notifyDataSetChanged();
                            }

                        }
                    })
                    .setNegativeButton("Analuj", null)
                    .show();
        });
    }
}
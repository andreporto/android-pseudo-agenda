package academy.porto.estudoalura_agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import academy.porto.estudoalura_agenda.R;
import academy.porto.estudoalura_agenda.dao.AlunoDao;
import academy.porto.estudoalura_agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDao dao = new AlunoDao();
    private ArrayAdapter<Aluno> adapterAlunos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITULO_APPBAR);

        final ListView listaAlunos = findViewById(R.id.lista);

        configurarFabClickListener();

        adicionarDadosIniciais();

        configurarAdapter(listaAlunos);

        configurarItemClickListener(listaAlunos);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.lista_alunos_activity_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = adapterAlunos.getItem(menuInfo.position);
            removeAluno(aluno);
        }
        return super.onContextItemSelected(item);
    }


    private void adicionarDadosIniciais() {
        dao.salvar(new Aluno("fulano", "fulano@fdsfdf.com", "342423423"));
        dao.salvar(new Aluno("cicrano", "cicrano@fdsfdf.com", "342423423"));

    }

    private void configurarFabClickListener() {
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getNovaNavegacao());
            }
        });
    }

    private Intent getNovaNavegacao() {
        return new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaAlunos();

    }

    private void atualizaListaAlunos() {
        adapterAlunos.clear();
        adapterAlunos.addAll(dao.todos());
    }


    private void removeAluno(Aluno aluno) {
        dao.remover(aluno);
        adapterAlunos.remove(aluno);
    }

    private void configurarItemClickListener(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno escolhido = (Aluno) parent.getItemAtPosition(position);
                Intent vaiParaEdicao = getNovaNavegacao();
                vaiParaEdicao.putExtra("aluno", escolhido);
                startActivity(vaiParaEdicao);
            }
        });
    }

    private void configurarAdapter(ListView listaAlunos) {
        adapterAlunos = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        listaAlunos.setAdapter(adapterAlunos);
        registerForContextMenu(listaAlunos);
    }
}

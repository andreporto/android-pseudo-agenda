package academy.porto.estudoalura_agenda.ui.activity;


import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import academy.porto.estudoalura_agenda.R;
import academy.porto.estudoalura_agenda.dao.AlunoDao;
import academy.porto.estudoalura_agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";

    private Aluno parametros = null;

    private EditText nome;
    private EditText telefone;
    private EditText email;
    private AlunoDao alunoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);

        inicializaVariaveis();

        if (getIntent().hasExtra("aluno")) {
            recuperaParametros();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listar_alunos_activity_menu_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.optionMenuSalvar) {
            salvar(getValores());
        }
        return super.onOptionsItemSelected(item);
    }

    private void recuperaParametros() {

        if (getIntent().getExtras().getParcelable("aluno") != null) {

            parametros = (Aluno) getIntent().getExtras().getParcelable("aluno");

            if (parametros != null) {
                atribuirValoresParaCampos();
            }
        }

    }

    private void atribuirValoresParaCampos() {
        nome.setText(parametros.getNome());
        telefone.setText(parametros.getTelefone());
        email.setText(parametros.getEmail());
    }

    private Aluno getValores() {
        Aluno aluno = new Aluno(nome.getText().toString(), email.getText().toString(), telefone.getText().toString());
        if (parametros != null) {
            aluno.setId(parametros.getId());
        }
        return aluno;
    }

    private void salvar(Aluno aluno) {
        if (aluno.temIdValido()) {
            alunoDao.editar(aluno);
        } else {
            alunoDao.salvar(aluno);
        }
        finish();
    }

    private void inicializaVariaveis() {
        alunoDao = new AlunoDao();

        configurarReferenciasCampos();
    }

    private void configurarReferenciasCampos() {
        nome = findViewById(R.id.activity_formulario_aluno_nome);
        telefone = findViewById(R.id.activity_formulario_aluno_telefone);
        email = findViewById(R.id.activity_formulario_aluno_email);
    }

}

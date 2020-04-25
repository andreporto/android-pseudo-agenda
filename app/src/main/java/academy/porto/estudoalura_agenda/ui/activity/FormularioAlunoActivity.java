package academy.porto.estudoalura_agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import academy.porto.estudoalura_agenda.R;
import academy.porto.estudoalura_agenda.dao.AlunoDao;
import academy.porto.estudoalura_agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText email;
    private AlunoDao alunoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle("Novo aluno");

        inicializaVariaveis();

        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Aluno aluno = new Aluno(nome.getText().toString(), email.getText().toString(), telefone.getText().toString());
                salvar(aluno);
            }
        });


    }

    private void salvar(Aluno aluno) {
        alunoDao.salvar(aluno);
        finish();
    }

    private void inicializaVariaveis() {
        alunoDao = new AlunoDao();

        nome = findViewById(R.id.activity_formulario_aluno_nome);
        telefone = findViewById(R.id.activity_formulario_aluno_telefone);
        email = findViewById(R.id.activity_formulario_aluno_email);
    }

}

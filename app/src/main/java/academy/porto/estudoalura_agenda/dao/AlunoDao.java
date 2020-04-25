package academy.porto.estudoalura_agenda.dao;

import java.util.ArrayList;
import java.util.List;

import academy.porto.estudoalura_agenda.model.Aluno;

public class AlunoDao {

    private final static List<Aluno> alunos = new ArrayList<Aluno>();

    public void salvar(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<Aluno>(alunos);
    }
}

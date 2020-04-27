package academy.porto.estudoalura_agenda.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import academy.porto.estudoalura_agenda.model.Aluno;

public class AlunoDao {

    private static int contadorDeId = 1;

    private final static List<Aluno> alunos = new ArrayList<Aluno>();

    public void salvar(Aluno aluno) {
        aluno.setId(contadorDeId);
        this.alunos.add(aluno);
        incrementarId();
    }

    private void incrementarId() {
        contadorDeId++;
    }

    public void editar(Aluno aluno) {
        Aluno alunoEncontrado = null;
        alunoEncontrado = buscarAlunoInformadoParaEdicao(aluno);
        if (alunoEncontrado != null) {
            int indiceDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(indiceDoAluno, aluno);
        }
    }

    private Aluno buscarAlunoInformadoParaEdicao(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno elemento: alunos) {
            if (elemento.getId() == aluno.getId()) {
                alunoEncontrado = elemento;
            }
        }
        return alunoEncontrado;
    }

    public List<Aluno> todos() {
        return new ArrayList<Aluno>(alunos);
    }

    public void remover(Aluno aluno) {
        final Aluno alunoParaRemover = buscarAlunoInformadoParaEdicao(aluno);
        if (alunoParaRemover != null) {
            alunos.remove(alunoParaRemover);
        }
    }
}

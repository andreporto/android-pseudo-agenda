package academy.porto.estudoalura_agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

// Embora Parcel seja mais verboso, é muito mais performático que Serializable
public class Aluno implements Parcelable {
    private int id = 0;
    private  String nome;
    private  String email;
    private  String telefone;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Aluno(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    private Aluno(Parcel p){
        id = p.readInt();
        nome = p.readString();
        email = p.readString();
        telefone = p.readString();
    }

    public static final Creator<Aluno>
            CREATOR = new Creator<Aluno>() {

        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }



    @NonNull
    @Override
    public String toString() {
        return nome + " -> " + email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(telefone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}

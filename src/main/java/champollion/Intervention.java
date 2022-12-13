package champollion;
import java.util.Date;

public class Intervention {
    private Date debut;
    private int duree;
    private boolean annulee;
    private int heureDebut;
    private Salle lieu;
    private TypeIntervention type;
    private Enseignant enseignant;
    private UE ue;

    public Intervention(Date debut, int heureDebut, int duree, Salle lieu,TypeIntervention type, Enseignant enseignant,UE ue){
        this.debut=debut;
        this.duree=duree;
        this.heureDebut=heureDebut;
        this.lieu=lieu;
        this.type=type;
        this.enseignant=enseignant;
        this.ue=ue;
    }

    public Date getDebut(){
        return debut;
    }

    public void setDebut(Date debut){
        this.debut=debut;
    }

    public int getDuree(){
        return duree;
    }

    public void setDuree(int duree){
        this.duree=duree;
    }

    public boolean getAnnule(){
        return annulee;
    }

    public void setAnnulee(boolean annulee){
        this.annulee=annulee;
    }

    public int getHeureDebut(){
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut){
        this.heureDebut=heureDebut;
    }

    public Salle getLieu(){
        return lieu;
    }

    public void setLieu(Salle lieu){
        this.lieu=lieu;
    }

    public TypeIntervention getType() {
        return type;
    }

    public void setType(TypeIntervention type){
        this.type=type;
    }

    public Enseignant getEnseignant(){
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant){
        this.enseignant=enseignant;
    }

    public UE getUE(){
        return ue;
    }

    public void setUE(UE ue){
        this.ue=ue;
    }
}

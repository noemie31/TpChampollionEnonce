package champollion;
import java.util.ArrayList;

public class Enseignant extends Personne {

    ArrayList<ServicePrevu> servicesPrevus = new ArrayList<ServicePrevu>();
    ArrayList<Intervention> listeinterventions = new ArrayList<Intervention>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        float heuresprevues=0;
        for (ServicePrevu sp : servicesPrevus){
            heuresprevues= (float)(heuresprevues+sp.getVolumeCM()*1.5);
            heuresprevues=heuresprevues+sp.getVolumeTD();
            heuresprevues= (float)(heuresprevues+sp.getVolumeTP()*0.5);
        }
        return Math.round(heuresprevues);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        float heuresprevuesue=0;
        for (ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(ue)){
                heuresprevuesue= (float)(heuresprevuesue+sp.getVolumeCM()*1.5);
                heuresprevuesue=heuresprevuesue+sp.getVolumeTD();
                heuresprevuesue= (float)(heuresprevuesue+sp.getVolumeTP()*0.5);
            }
        }
        return Math.round(heuresprevuesue);
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        boolean present=false;
        for (ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(ue)){
                present=true;
                sp.setVolumeCM(sp.getVolumeCM()+volumeCM);
                sp.setVolumeTD(sp.getVolumeTD()+volumeTD);
                sp.setVolumeTP(sp.getVolumeTP()+volumeTP);
            }
        }
        if (present==false){
            servicesPrevus.add(new ServicePrevu(ue,volumeCM,volumeTD,volumeTP));
        }

    }

    public void ajouterIntervention(Intervention intervention){
        boolean valide=false;
        for (ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(intervention.getUE())){
                valide=true;
            }
        }
        if(valide==false){
            throw new IllegalArgumentException("L'enseignement dont on veut créer l'intervention n'existe pas");
        }
        listeinterventions.add(intervention);
    }

    public int resteAPlanifier(UE ue, TypeIntervention type){
        boolean valide=false;
        for (ServicePrevu sp : servicesPrevus){
            if(sp.getEnseignement().equals(ue)){
                valide=true;
            }
        }
        if(valide==false){
            throw new IllegalArgumentException("Dans cette UE, l'enseigment n'existe pas et donc l'intervention de cet enseignement non plus");
        }
        int nbheureue=0;
        int nbheureplanifiee=0;
        for (ServicePrevu servp: servicesPrevus){
            if(servp.getEnseignement().equals(ue)){
                if(type==TypeIntervention.CM){
                    nbheureue=servp.getVolumeCM();
                }
                if(type==TypeIntervention.TD){
                    nbheureue=servp.getVolumeTD();
                }
                if(type==TypeIntervention.TP){
                    nbheureue=servp.getVolumeTP();
                }
            }
        }
        for (Intervention i : listeinterventions){
            if(i.getUE().equals(ue) && i.getType()==type){
                nbheureplanifiee=nbheureplanifiee+i.getDuree();
            }
        }
        return nbheureue - nbheureplanifiee;
    }

    public boolean enSousService(){
        boolean ess=false;
        if(this.heuresPrevues()<192){
            ess=true;
        }
        return ess;
    }
    public ArrayList<Intervention> getInterventions(){
        return listeinterventions;
    }

    public ArrayList<ServicePrevu> getServicesPrevus(){
        return servicesPrevus;
    }
}

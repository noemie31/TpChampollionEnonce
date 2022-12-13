package champollion;

public class ServicePrevu {
    private UE enseignement;
	private int volumeCM;
    private int volumeTD;
    private int volumeTP;

    public ServicePrevu(UE enseignement, int volumeCM,int volumeTD,int volumeTP){
        this.enseignement=enseignement;
        this.volumeCM=volumeCM;
        this.volumeTD=volumeTD;
        this.volumeTP=volumeTP;
    }

    public int getVolumeCM(){
        return volumeCM;
    }

    public void setVolumeCM(int volumeCM){
        this.volumeCM=volumeCM;
    }

    public int getVolumeTD(){
        return volumeTD;
    }

    public void setVolumeTD(int volumeTD){
        this.volumeTD=volumeTD;
    }

    public int getVolumeTP(){
        return volumeTP;
    }

    public void setVolumeTP(int volumeTP){
        this.volumeTP=volumeTP;
    }

    public UE getEnseignement(){
        return enseignement;
    }

    public void setEnseignement(UE enseignement){
        this.enseignement=enseignement;
    }
}

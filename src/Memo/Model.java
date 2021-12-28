package src.Memo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class Model {
    // contients les images .
    // dans le controlleur => associer a chaques images une id.
    String[] images;
    String file;
    Carte[][] cartes; // Erreur carte ici est un 'button'.
    int tailleJeuColonnes;
    int tailleJeuLignes;
    Random rand;
    int vie;
    boolean etat;
    double[] bestScores;

    Chrono chrono;
    private boolean activePiegeCard = false;


    public Model(){
        file = "./scores4x4.txt";
        tailleJeuColonnes = 4;
        tailleJeuLignes = 4 ;
        this.images=new String[tailleJeuColonnes*tailleJeuLignes];
        rand = new Random();
        setImagesManually();
        this.vie=this.getTailleJeuColonnes()*3;
        cartes=new Carte[tailleJeuLignes][tailleJeuColonnes];
        setCartes();
        bestScores = new double[3];
        setScores();
    }

    public void restart(){
        if(chrono != null) this.getChrono().stop();
        this.vie=this.getTailleJeuColonnes()*3;
        // utilisation : 3x3 -> 4 images utilisé .
        // 4 images -> 8 copies dans la liste ; +1 image piégè -> taille de la liste 9
        // utilisation : 5x5 -> 12 images ... la dernière piege.
        this.images=new String[tailleJeuColonnes*tailleJeuLignes+1];
        setImagesManually();
        cartes=new Carte[tailleJeuLignes][tailleJeuColonnes];
        setCartes();
        etat=false;
        file = "./scores"+tailleJeuColonnes+"x"+tailleJeuLignes+".txt";

        bestScores = new double[3];
        setScores();

    }

    public void startTimer(JLabel temps){
        chrono = new Chrono(temps);
        chrono.start();
    }

    public void setCartes(){
        ImageIcon icon;
        for(int j = 0 ; j<cartes.length;j++){
            for(int i = 0; i <cartes[0].length;i++){
                if(cartes[j][i] == null) cartes[j][i]= new Carte(j,i);
                cartes[j][i].setImageString(getImagesRandomly());
                icon = new ImageIcon(cartes[j][i].getImageString());
                icon = new ImageIcon(icon.getImage().getScaledInstance(60,60, BufferedImage.SCALE_SMOOTH));
                cartes[j][i].setIconCarte(icon);
                cartes[j][i].setIcon(Carte.cache);
                cartes[j][i].setEnabled(true);
            }
        }

    }

    public void desactiveAllButtons() {
        for (int j = 0; j < cartes.length; j++) {
            for (int i = 0; i < cartes[0].length; i++) {
                cartes[j][i].setIcon(cartes[j][i].getIconCarte());
                cartes[j][i].setEnabled(false);
            }
        }
    }

    public void setImagesManually(){
        for(int i = 0 ;i<tailleJeuColonnes*tailleJeuLignes;i++){
            images[i]="./Images/"+i%((tailleJeuColonnes*tailleJeuLignes)/2)+".png";
            if(i==tailleJeuColonnes*tailleJeuLignes-1 && activePiegeCard) images[i]="./Images/"+"trap"+".png";
        }

    }

    public String getImagesRandomly(){
        int nbMystere;
        do{
            nbMystere = rand.nextInt((tailleJeuColonnes*tailleJeuLignes));
        }while(images[nbMystere]=="");
        String imageARetourne = images[nbMystere];
        images[nbMystere]="";

        return imageARetourne;
    }

    public boolean isGagne(){
        for(int j = 0 ; j<cartes.length;j++){
            for(int i = 0; i <cartes[0].length;i++){
                if(cartes[j][i].isEnabled() && !cartes[j][i].getImageString().equals("./Images/"+"trap"+".png")) return false;
            }
        }
        return true;
    }

    public void setScores(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            bestScores[0] = Double.parseDouble(br.readLine());
            bestScores[1] = Double.parseDouble(br.readLine());
            bestScores[2] = Double.parseDouble(br.readLine());

        }catch(Exception e){ e.printStackTrace(); }
    }

    public void writeScores(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(""+bestScores[0]);
            bw.newLine();
            bw.write(""+bestScores[1]);
            bw.newLine();
            bw.write(""+bestScores[2]);
            bw.newLine();
            bw.close();
        }catch(Exception e){ e.printStackTrace(); }

    }

    public void updateScore(double scoresAct){  
        for(int i = 0 ; i<bestScores.length;i++){
            if(bestScores[i]>scoresAct || bestScores[i] == 0.0 ){
                bestScores[i]=scoresAct;
                break;
            }
        }
        writeScores();
    }

    public void settingScores(JLabel Temps) {
        String temps = Temps.getText().replace(',','.');
        setScores();
        updateScore(Double.parseDouble(temps));
    }


    // getter and setter

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getTailleJeuColonnes() {
        return tailleJeuColonnes;
    }

    public void setTailleJeuColonnes(int tailleJeuColonnes) {
        this.tailleJeuColonnes = tailleJeuColonnes;
    }

    public int getTailleJeuLignes() {
        return tailleJeuLignes;
    }

    public void setTailleJeuLignes(int tailleJeuLignes) {
        this.tailleJeuLignes = tailleJeuLignes;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }


    public Carte[][] getCartes() {
        return cartes;
    }

    public void setCartes(Carte[][] cartes) {
        this.cartes = cartes;
    }


    public double[] getBestScores() {
        return bestScores;
    }

    public void setBestScores(double[] bestScores) {
        this.bestScores = bestScores;
    }

    public Chrono getChrono() {
        return chrono;
    }

    public void setChrono(Chrono chrono) {
        this.chrono = chrono;
    }

    public boolean isActivePiegeCard() {
        return activePiegeCard;
    }

    public void setActivePiegeCard(boolean activePiegeCard) {
        this.activePiegeCard = activePiegeCard;
    }
}

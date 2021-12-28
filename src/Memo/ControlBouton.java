package src.Memo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Objects;

public class ControlBouton implements ActionListener {
    Fenetre fen;
    Model model ;
    Carte[][] cartes;
    Carte carte;Carte carte2;

    public ControlBouton(Fenetre f , Model m){
        fen=f;
        model = m;
        fen.setControlButton(this);
        cartes = fen.getCartes();
        carte=null;carte2=null;

        for (Carte[] carte : cartes) {
            for (int j = 0; j < cartes[0].length; j++) {
                carte[j].addActionListener(this);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(!model.isEtat()){
            fen.setControlButton(this);
            model.setEtat(true);
            model.startTimer(fen.getTemps());
        }

        while(carte != null){

            if(carte2!=null && carte.isRetourne() && carte2.isRetourne()){
                carte.setIcon(Carte.cache);carte2.setIcon(Carte.cache);
                carte2=null;carte=null;
                break;
            }

            if(!carte.isRetourne() && !(actionEvent.getSource().equals(carte)))
                carte2=carte;
            break;
        }
        carte =(Carte) actionEvent.getSource();
        carte.setIcon(carte.getIconCarte());
        carte.setRetourne(false);

        if(carte.getImageString().equals("./Images/"+"trap"+".png")){
            model.getChrono().stop();
            model.setVie(0);
            model.desactiveAllButtons();
            fen.showGameOver();
            model.setEtat(false);
            return;
        }

        if (carte2 != null) {
            // problème : on ne peut comparer deux icones ... donc j'ai crée une variable pour ce cas la , dans la classe carte ...
            if (carte.getImageString().equals(carte2.getImageString())) {
                carte.setEnabled(false);
                carte2.setEnabled(false);
                carte = null;carte2=null;
            } else {
                model.setVie(model.getVie() - 1);
                fen.getEssais().setText(String.valueOf(model.getVie()));
                carte.setRetourne(true);carte2.setRetourne(true);
            }
        }
        isGameOver();
    }

    public void isGameOver(){
        if(model.getVie()==0 || model.isGagne()){
            model.getChrono().stop();
            if(model.isGagne()){
                model.desactiveAllButtons();
                model.settingScores(fen.getTemps());
                fen.showBestScores();
            }else{
                model.desactiveAllButtons();
                fen.showGameOver();
            }
            model.setEtat(false);

        }

    }
}

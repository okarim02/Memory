package src.Memo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Carte extends JButton {
    private int ligne , colonne;
    private ImageIcon iconCarte;
    private boolean retourne;
    // Problème de dimension : l'image est plus grand que le bouton .
    public static ImageIcon cache=new ImageIcon(new ImageIcon("./Images/mystery.png").getImage().getScaledInstance(60,60, BufferedImage.SCALE_SMOOTH));
    private String imageString;

    public Carte(int ligne , int colonne){
        this.ligne=colonne;
        this.colonne=ligne;
        this.retourne=true;
        setIconCarte(cache);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return ligne == carte.ligne && colonne == carte.colonne && retourne == carte.retourne && Objects.equals(iconCarte, carte.iconCarte) && Objects.equals(imageString, carte.imageString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne, iconCarte, retourne, imageString);
    }

    // getter and setter

    public ImageIcon getIconCarte() {
        return iconCarte;
    }

    public void setIconCarte(ImageIcon iconCarte) {
        this.iconCarte = iconCarte;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public boolean isRetourne() {
        return retourne;
    }

    public void setRetourne(boolean retourne) {
        this.retourne = retourne;
    }

    public void setImageString(String iS) {
        this.imageString = iS;
    }

    public String getImageString() {
        return imageString;
    }
}

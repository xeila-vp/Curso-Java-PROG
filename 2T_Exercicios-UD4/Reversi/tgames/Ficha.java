package tgames;

import java.util.Objects;

public class Ficha {
    public static final String RED="🔴";
    public static final String ORANGE="🟠";
    public static final String  YELLOW="🟡";
    public static final String  GREEN="🟢";
    public static final String  BLUE="🔵";
    public static final String  PURPLE="🟣";
    public static final String  BROWN="🟤";
    public static final String  BLACK="⚫";
    public static final String  WHITE="⚪";
    public static final String  CIRCLE="⭕";
    public static final String  CROSS="❌";
    
    private String color;      // Representación da ficha

    /**
    * Construtor: Recibe a representación para a ficha
    */
    public Ficha(String color) {
        this.color=color;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        return other.color.equals(color);
    }
    
    
            
    @Override
    public String toString() {
        return color;
    }
}

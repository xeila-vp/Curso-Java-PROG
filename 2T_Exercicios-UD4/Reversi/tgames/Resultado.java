package tgames;

public class Resultado {
   private String texto;
   
   public Resultado(String texto) {
      this.texto=texto;
   }
   
   @Override
   public String toString() {
      return texto;
   }
}
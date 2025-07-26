package AA;

import java.util.ArrayList;
import java.util.List;

public class AA {

    public static void main(String[] args) {
        List<String[]> dataset = new ArrayList<>();

        dataset.add("Small,Red,Circule,Positive".split(","));
        dataset.add("Large,Red,Circule,Positive".split(","));

        List<Hipo> esapacioHipotesis = generarEspacioVersiones(dataset);

        List<Hipo> solucion = new ArrayList<Hipo>();
        for (Hipo h : esapacioHipotesis) {
            solucion.add(h);
            for (String[] ejemplo : dataset) {
                valor_objetivo = ejemplo[3];
                if (!esConsistente(h, ejemplo)) {
                    solucion.remove(h);
                    break;
                }
            }
        }

        System.out.println(solucion.toString());
    }

    private static List generarEspacioVersiones(List dataset) {

        return new ArrayList();
    }

    private static boolean esConsistente(Hipo h, String[] ejemplo, String positivo) {
        if (espositivo(ejemplo)) {
            return Cumple(h, ejemplo);
        }
        if (esnegativo(ejemplo)) {
            return !Cumple(h, ejemplo);
        }
        return false;
    }

    private static boolean espositivo(String[] ejemplo) {
         if(ejemplo[ejemplo.length-1].equals("positivo")) return true;
        return false;
    }

    private static boolean Cumple(Hipo h, String[] ejemplo) {
      for(int i=0;i< ejemplo.length;i++){
          String atributo = ejemplo[i];
          if(!cumpleAtributo(atributo,h.getColumna(i))){
              return false;
          }
      }
      return true;
    }

    private static boolean esnegativo(String[] ejemplo) {
       return !espositivo(ejemplo);
    }

    private static boolean cumpleAtributo(String atributo, String columna) {
        return (columna.equalsIgnoreCase(atributo) || columna.equalsIgnoreCase("?"));
    }
}

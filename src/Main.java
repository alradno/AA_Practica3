import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int[] ds = {10, 1, 7, 7};
        int[] cs = {8, 4, 2, 1};
        int[] ds2 = {10, 4, 3, 6, 9, 4, 4};
        int[] cs2 = {64, 32, 16, 8, 4, 2, 1}; //8+4+3+8+4+4 = 31 //solOptima: 10 + 4 + 6 + 9 + 4 + 4 = 37 (reinicio en 3)

        int resultado = procesar(ds, cs);
        int resultado2 = procesar(ds2, cs2);
        int resultado3 = procesar2(ds, cs);
        int resultado4 = procesar2(ds2, cs2);
        int resultado5 = procesar3(ds, cs);
        int resultado6 = procesar3(ds2, cs2);

        System.out.println("Procesar1: "+resultado);
        System.out.println("Procesar2: "+resultado3);
        System.out.println("Procesar3: "+resultado5);
        System.out.println();
        System.out.println("Procesar1: " +resultado2);
        System.out.println("Procesar2: " +resultado4);
        System.out.println("Procesar3: " +resultado6);

        //3 3 1 2
        //3 3 2 0
    }

    public static int procesar3 (int[] ds, int[] cs){
        int [] combinaciones = new int[ds.length];
        //Llama a mettodo auxiliar
        return aux(ds, cs, 0, combinaciones, 0);
    }

    static int contador = 0;

    public static int comparar(int[] ds, int [] solucion, int [] cs){

        int resultado = 0;
        int indice = 0;

        for(int i=0; i<cs.length; i++){
            if(solucion[i] == 1 ){
                resultado += Math.min(ds[i], cs[indice]);
                indice++;
            }else{
                indice = 0;
            }
        }
        return resultado;
    }


    public static int aux(int[] ds, int[] cs, int etapa, int [] combinaciones, int solSubArbol){

        for(int i=0; i<=1; i++){
            //Array que guarda de forma temporal la solucion del subarbol y se actualiza en cada iteracion
            combinaciones[etapa] = i;
            //Si es una hoja
            if(etapa == ds.length-1){
                int resultado = 0;
                int indice = 0;
                //Se comprueba si la combinacion del subArbol actual es mejor que la solucion optima hallada hasta ahora
                for(int j=0; j<cs.length; j++){
                    if(combinaciones[j] == 1 ){
                        resultado += Math.min(ds[j], cs[indice]);
                        indice++;
                    }else{
                        indice = 0;
                    }
                }
                if(resultado > solSubArbol){
                    solSubArbol = resultado;
                }

            }else{
                //Si no es una hoja, recorre de forma recursiva el subarbol hasta la profundidad de ds.length-1
                solSubArbol = aux(ds, cs, etapa+1, combinaciones, solSubArbol);
            }
        }
        //Marca que la combinación ha sido evaluada
        combinaciones[etapa] = -1;
        //Devuelve el resultado del subarbol
        return solSubArbol;
    }

    public static int procesar (int[] ds, int[] cs){

        int resultado = 0;
        int indice = 0;

        for(int i = 0; i < ds.length; i++){
            //Si no reinicia
            if(cs[indice] > ds[i]){
                resultado += ds[i];
                indice++;
            }
            //Si reinicia
            else{
                indice = 0;
            }
        }
        return resultado;
    }

    public static int procesar2 (int[] ds, int[] cs){

        int resultado = 0;
        int indice = 0;
        int mediads = 0;
        int mediacs = 0;

        //Media del array ds
        for(int i = 0; i < ds.length; i++){
            mediads += ds[i];
        }
        mediads = mediads / ds.length;

        //Media cs
        for(int i = 0; i < cs.length; i++){
            mediacs += cs[i];
        }
        mediacs = mediacs / cs.length;

        for(int i = 0; i < ds.length; i++){
            //No reinicia
            if(cs[indice] > ds[i]){
                resultado += ds[i];
                indice++;
                //Si es el ultimo dia no se reinicia(coge lo que puede)
            } else if (i == ds.length - 1) {
                resultado += Math.min(cs[indice], ds[i]);
            }
            else{
                //Si es un día que merece la pena no reiniciar
                if(ds[i] > mediads && cs[indice] > mediacs){
                    resultado += Math.min(ds[i], cs[indice]);
                    indice++;
                }
                //Reinicia Servidor (la recompensa es muy baja si no se hace)
                else{
                    indice = 0;
                }
            }
        }
        return resultado;
    }

}
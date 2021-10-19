/**
 * Clase Raidboss
 * Clase hija de Combatiente, tiene las propiedades del combatiente y sus metodos, junto a la habilidad de clonarse.
 * 
 * @version 1.0, 16/10/2021
 * finalizacion 18/10/2021
 * 
 * @author Diego E. Lemus L. - 21469
 */

 public class Raidboss extends Combatiente{

    //-----PROPIEDADES-----
    protected String nombre;

    /** 
     * Metodo constructor del Raidboss
     */
    public Raidboss(String tipo){
        super(tipo);
        this.nombre = "Jefe Final";
        this.habilidadClonar = true;
        habilidad = "terremoto";
        this.msgIncio = "SOY EL JEFE, PREPARATE PARA SER DESTRUIDO";
        this.msgDerrota = "NOOOOOO, ME HAS DERROTADO";
    }

    /** 
     * Metodo para que el Raidboss ejecute sus habilidades, clonar, liberar, variar, terremoto.
     * @param obj
     * @param hab
     * @return String
     */
    public String ejecutarHabilidades(Combatiente obj, String hab){
        String msg = "";

        //Habilidad clonar
        if(habilidadClonar == true && hab.equals("clonar")){
            msg = this.tipo + " ha realizado " + hab  + " con " + obj;
        //Habilidad liberar
        }else if(hab.equals("liberar")){
            msg = this.tipo + " ha liberado a sus clones";
        //Habilidad variar
        }else if(hab.equals("variar")){
            msg = this.tipo + " ha realizado una variacion en su habilidad";
        //Habilidad terremoto
        }else if(hab.equals("terremoto")){
            msg = this.tipo + " ha realizado " + hab  + " con " + obj;
        }else{
            msg = this.tipo + " fallo al realizar " + this.habilidad  + " contra " + obj;
        }
        return msg;
    }

    /** 
     * Metodo para obtener el saludo del Raidboss
     * @return String
     */
    public String getMsgSaludo(){
        return this.msgIncio;
    }

    /** 
     * Metodo para obtener la derrota del Raidboss
     * @return String
     */
    public String getMsgDerrota(){
        return this.msgDerrota;
    }

    /** 
     * Metodo para obtener la vida del Raidboss
     * @return String
     */
    public String mostrarVida(){
        String msg = "";
        msg = this.nombre + " tiene " + this.vida + " de vida";
        return msg;
    }
}

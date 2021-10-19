/**
 * Clase Mascota
 * Clase hija de Acompanante, tiene las propiedades del acompanante y sus metodos.
 * 
 * @version 1.0, 16/10/2021
 * finalizacion 18/10/2021
 * 
 * @author Diego E. Lemus L. - 21469
 */

 public class Mascota extends Acompanante{
    
    /** 
     * Metodo constructor de la mascota
     */
    public Mascota(String tipo){
        super(tipo);
        this.habilidadEsp = "Ataque Mascota";
    }

    /** 
     * Metodo para que la mascota realice su ataque
     * @param esp
     * @param obj
     * @return String
     */
    public String atacar(String esp, Combatiente obj){
        String msg = "";

        if(habilidadEsp.equals("Ataque Mascota")){
            ataque(obj, 5);
            msg = this.tipo + " ha realizado " + this.habilidad  + " contra " + obj;
        } else{
            msg = this.tipo + " fallo al realizar " + this.habilidad  + " contra " + obj;
        }

        return msg;
    }

    /** 
     * Metodo para obtener a la mascota
     * @return boolean
     */
    public boolean getMascota(){
        return mascota;
    }
}

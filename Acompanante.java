/**
 * Clase Acompanante
 * Encargada de simular al acompanante, junto a sus caracteristicas.
 * 
 * @version 1.0, 16/10/2021
 * finalizacion 18/10/2021
 * 
 * @author Diego E. Lemus L. - 21469
 */

 public abstract class Acompanante extends Combatiente {
    //-----PROPIEDADES-----
    protected String habilidadEsp;

    /** 
     * Metodo constructor del acompanante
     */
    public Acompanante(String tipo){
        super(tipo);
    }

    /** 
     * Metodo para otogarle una habilidad al acompanate
     * @param esp
     */
    public void setHabilidadEsp(String esp){
        this.habilidadEsp = esp;
    }

    /** 
     * Metodo abstracto para definir el ataque de los acompanantes
     * @param esp
     */
    public abstract String atacar(String esp, Combatiente obj);
}

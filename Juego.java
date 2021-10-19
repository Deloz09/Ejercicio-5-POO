import java.util.ArrayList;
import java.util.Random;

/**
 * Clase Juego
 * Encargada de simular el juego, llevar a cabo las jugadas y manejar los combatientes.
 * 
 * @version 1.0, 27/09/2021
 * finalizacion 28/09/2021
 * 
 * @author Diego E. Lemus L. - 21469
 */

public class Juego {

    //-----PROPIEDADES-----
    private Random rand = new Random();
    private ArrayList<Combatiente> combatientes = new ArrayList<Combatiente>();
    private ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
    private int cantEnemigos = 3;
    private int jugadores;
    private ArrayList<String> historial = new ArrayList<String>();
    private ArrayList<Acompanante> acompanantes = new ArrayList<Acompanante>();
    private boolean gameover = false;

    //-----METODOS-----
    /** 
     * Metodo constructor del juego
     */
    public Juego(){
        for(int i=0;i<3;i++){
            historial.add("");
        }
        for (int i = 0; i < 5; i++){
            combatientes.add(null);
        }
    }

    public void generarJugadores(int id, Combatiente combatiente){
        combatientes.set(id, combatiente);
        jugadores++;
    }
    
    /** 
     * Metodo para determinar una accion
     * @param a
     */
    public void accion(String a){
        historial.add(a);
    }
    
    /** 
     * Metodo para generar enemigos
     * @return String
     */
    public String generarEnemigos(){
        String msg = "";
        //Numero random para la distribucion de enemigos
        int num =  rand.nextInt(3)+1;

        //caso 1
        if(num==3){
            Enemigo duende = new Enemigo("Duende");
            enemigos.add(duende);
            combatientes.set(generarLugar(), duende);
            msg += duende.getMsgInicio();
            Enemigo duende2 = new Enemigo("Duende");
            combatientes.set(generarLugar(), duende2);
            enemigos.add(duende2);
            msg += duende2.getMsgInicio();
            Enemigo duende3 = new Enemigo("Duende");
            combatientes.set(generarLugar(), duende3);
            enemigos.add(duende3);
            msg += duende3.getMsgInicio();
        }
        //caso 2
        if(num==2){
            Enemigo duende = new Enemigo("Duende");
            combatientes.set(generarLugar(), duende);
            enemigos.add(duende);
            msg += duende.getMsgInicio();
            Enemigo duende2 = new Enemigo("Duende");
            combatientes.set(generarLugar(), duende2);
            enemigos.add(duende2);
            msg += duende2.getMsgInicio();
            Enemigo hechicero = new Enemigo("Hechicero");
            combatientes.set(generarLugar(), hechicero);
            enemigos.add(hechicero);
            msg += hechicero.getMsgInicio();
        }
        //caso 3
        if(num==1){
            Enemigo duende = new Enemigo("Duende");
            combatientes.set(generarLugar(), duende);
            enemigos.add(duende);
            msg += duende.getMsgInicio();
            Enemigo hechicero = new Enemigo("Hechicero");
            combatientes.set(generarLugar(), hechicero);
            enemigos.add(hechicero);
            msg += hechicero.getMsgInicio();
            Enemigo hechicero2 = new Enemigo("Hechicero");
            combatientes.set(generarLugar(), hechicero2);
            enemigos.add(hechicero2);
            msg += hechicero2.getMsgInicio();
        }

        Combatiente raidboss = new Raidboss("raidboss");
        combatientes.set(generarLugar(), raidboss);
        msg += "\n" + raidboss.getMsgInicio();

        return msg;
    }

    /** 
     * Metodo para generar jefe enemigo
     * @return String
     */
    public String generarJefeEnemigo(){
        String msg = "";
        int num =  rand.nextInt(2)+1;
        
        if(num==1){
            //Jefe Duende
            Enemigo jefe = new Jefe("Duende");
            enemigos.add(jefe);
            msg = "Se ha generado un nuevo jefe "+jefe.getTipo();
        }else if(num==2){
            //Jefe Hechicero
            Enemigo jefe = new Jefe("Hechicero");
            enemigos.add(jefe);
            msg = "Se ha generado un nuevo jefe "+jefe.getTipo();
        }
        return msg;
    }

    /** 
     * Metodo para mostrar el historial de la partida
     * @return String
     * @throws Exception
     */
    public String mostrarHistorial() throws Exception{
        String historialS = "";

        try{
            int historialT = this.historial.size();
            //Se agregan las ultimas 3 acciones del juego
            if(historialT>1){
                for (int i = historialT-1; i >= historialT-3; i--)
                    if (this.historial.get(i) != null)
                        historialS += this.historial.get(i) + "\n";
            }else{
                //En caso de no haberse realizado ninguna accion
                historialS = "No se ha realizado ninguna accion todavia.";
            }
        }catch(Exception e){
            String s = "Error en el historial " + e.getMessage();
            throw new Exception(s);
        }
        return historialS;
    }

    /** 
     * Metodo para atacar
     * @param obj
     * @param id
     * @throws Exception
     */
    public void atacar(Combatiente obj, int id) throws Exception{
        try{
            if(combatientes.get(id) != null){
                //ataque de combatiente
                String ataque = combatientes.get(id).ataque(obj);
                accion(ataque);
            }
        }catch(Exception e){
            String s = "Error en accion: atacar "+e.getMessage();
            throw new Exception(s);
        }
    }

    /** 
     * Metodo para usar la habilidad del combatiente
     * @param hab
     * @param obj
     * @param id
     * @throws Exception
     */
    public void usarHabilidad(String hab, Combatiente obj, int id) throws Exception{
        try{
            if(combatientes.get(id) != null){
                //uso de habilidad
                String habilidad = combatientes.get(id).habilidad(hab, obj);
                accion(habilidad);
            }
        }catch(Exception e){
            String s = "Error en accion: habilidad "+e.getMessage();
            throw new Exception(s);
        }
    }
 
    /** 
     * Metodo para determinar derrota del combatiente
     * @return String
     */
    public String derrota(){
        String derrotas = "";

        //Se verifica las vidas de todos los combatientes
        for (int i = 0; i < combatientes.size(); i++){
            if(combatientes.get(i) != null){
                if (combatientes.get(i).getVida() <= 0){
                    derrotas += combatientes.get(i).getMsgDerrota();
                    String derrota = combatientes.get(i) + " ha sido derrotado";
                    accion(derrota);
                    if(combatientes.get(i).getTipo().equals("Explorador") || combatientes.get(i).getTipo().equals("Guerrero"))
                        gameover = true;
                    else
                        cantEnemigos--;
                    combatientes.remove(combatientes.get(i));
                }
            }
        }
        //Si solo queda 1 enemigo se genera el jefe
        if(cantEnemigos==1){
            generarJefeEnemigo();
        }

        return derrotas;
    }
    
    /** 
     * Metodo para obtener el objetivo del ataque
     * @param obj
     * @return Combatiente
     * @throws Exception
     */
    public Combatiente getObjetivo(int id) throws Exception{
        Combatiente objetivo = null;
        try{
            //Se obtiene el objetivo en base al id (orden)
            objetivo = combatientes.get(id);
        } catch (Exception e){
            String s = "Error en accion: obtener objetivo "+e.getMessage();
            throw new Exception(s);
        }
        return objetivo;
    }

    /** 
     * Metodo para mostrar los resultados de la partida
     * @return String
     */
    public String resultado(){
        String resultado = "";
        //En caso de que el jugador gane
        if(cantEnemigos == 0){
            resultado = combatientes.get(0).getMsgVictoria();
        }else if(jugadores == 0 && gameover == true){
            for (int i = 1; i < combatientes.size(); i++){
                if(enemigos.get(i) != null){
                    resultado += "\n" + combatientes.get(i).getMsgVictoria();
                }
            }
        }
        return resultado;
    }

    /** 
     * Metodo para pasar el turno
     */
    public void pasarTurno(){
        String msg = "Se ha elegido pasar turno";
        accion(msg);
    }

    /** 
     * Metodo para determinar si el jugador gano o perdio la partida
     * @return boolean
     */
    public boolean getGame(){
        return gameover;
    }


    //----------------------------------------------COSAS AGREGADAS----------------------------------------------

    /** 
     * Metodo para generar a la Mascota del cazador
     * @return String
     */
    public String mascota(){
        Acompanante mascota = new Mascota("mascota");
        acompanantes.add(mascota);
        String msgMascota = mascota.getMsgInicio();
        return msgMascota;
    }

    /** 
     * Metodo para que el acompanante enemigo ataque
     * @return void
     */
    public void atacarAcompanante(int vida, int jefe, Combatiente obj){
        for (int i = 0; i < combatientes.size(); i++){
            if(combatientes.get(i) != null){
                String tipo = combatientes.get(i).getTipo();
                if(tipo.equals("mascota") || tipo.equals("duende") || tipo.equals("hehcicero")){
                    if(jefe == combatientes.get(i).jefe){
                        String movMascota = combatientes.get(i).habilidad(combatientes.get(i).habilidad, obj);
                        historial.add(movMascota);
                    }
                }
            }
        }
    }

    /** 
     * Metodo para que la mascota ataque
     * @return void
     */
    public void atacarMascota(int vida, String movMascota, Combatiente objetivo){
        for (int i = 0; i < acompanantes.size(); i++){
            if(acompanantes.get(i) != null){
                if(acompanantes.get(i).getTipo().equals("mascota")){
                    String movMas = acompanantes.get(i).habilidad(movMascota, objetivo);
                    historial.add(movMas);
                }
            }
        }
    }

    /** 
     * Metodo para clonar mascota
     * @return String
     */
    public String clonar(int idOriginal, String habilidadEsp, Combatiente obj) throws Exception{
        String msgClon = "";

        try{
            String tipoOriginal = obj.getTipo();
            if(tipoOriginal.equals("mascota")){
                Acompanante acompananteClon = new Mascota(tipoOriginal);
                combatientes.set(generarLugar(), acompananteClon);
                acompananteClon.setHabilidadEsp(habilidadEsp);
                msgClon = acompananteClon.getMsgInicio();
            }
        }catch (Exception e){
            String s = "Error en accion: clonar "+e.getMessage();
            throw new Exception(s);
        }

        return msgClon;
    }

    /** 
     * Metodo para liberar clones
     * @return String
     */
    public String liberar(int jefe){
        String msg = "";
        for (int i = 0; i < combatientes.size(); i++){
            if(combatientes.get(i) != null){
                String tipoJefe = combatientes.get(i).getTipo();
                if(tipoJefe.equals("mascota") || tipoJefe.equals("duende") || tipoJefe.equals("hechicero")){
                    if(jefe == combatientes.get(i).jefe){
                        combatientes.remove(combatientes.get(i));
                        msg = "Clones liberados";
                    }
                }
            }
        }
        return msg;
    }

    /** 
     * Metodo para generar lugares en arrays
     * @return int
     */
    private int generarLugar(){
        boolean lugarEncontrado = false;
        int lugar = 0;
        
        for(int i = 0; lugarEncontrado == false && i < combatientes.size(); i++)
            if (combatientes.get(i) == null){
                lugar = i;
                lugarEncontrado = true;
            }
        return lugar;
    }
}

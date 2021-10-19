/**
 * Clase Controlador
 * Encargada de ejecutar el programa, llevar a cabo la seleccion de opciones, comunicarse con las
 * demás clases y sus respectivos procesos.
 * 
 * @version 1.0, 27/09/2021
 * finalizacion 28/09/2021
 * 
 * @author Diego E. Lemus L. - 21469
 */

public class Controlador {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        
        //-----PROPIEDADES-----
        Juego juego = new Juego();
        Vista vista = new Vista();
        Combatiente jugador = null;
        int op = 0;

        try{
            //Muestra la bienvenida
            vista.bienvenida();

            int cant = vista.pedirCantJugador();

            for(int i=0; i<cant;i++){
                //Pide el tipo de Jugador
                String tipo = vista.pedirTipoJugador();
                //Genera el jugador
                jugador = new Jugador(tipo);
                juego.generarJugadores(i, jugador);
                //Muestra el mensaje de inicio del jugador
                String msgInicio = jugador.getMsgInicio();
                vista.msgIncio(msgInicio);
            }
            
            //Genera los enemigos
            String msgInicioEnemigos = juego.generarEnemigos();
            //Muestra el mensaje de inicio de los enemigos
            vista.msgIncio(msgInicioEnemigos);
            int turnos = 0;
            
            while(op!=4){
                turnos = 0;
                //Muestra el menu
                op = vista.mostrarMenu();
                //Muestra el historial
                String historial = juego.mostrarHistorial();
                vista.historial(historial);

                switch(op){

                    //OPCION 1: Atacar
                    case 1:
                        //Pide el objetivo del ataque
                        int id = vista.pedirObjetivo();
                        
                        Combatiente objetivo = juego.getObjetivo(id);
                        juego.atacar(objetivo, id);

                        if (juego.getObjetivo(id).getTipo().equals("cazador")){
                            juego.atacarMascota(juego.getObjetivo(id).getAtaque(), "mordizco", objetivo);
                        }
                        if (juego.getObjetivo(id).getTipo().equals("raidboss")){
                            int jefe = id;
                            juego.atacarAcompanante(juego.getObjetivo(id).getAtaque(), jefe, objetivo);
                        }

                        //Verifica si hubo una derrota
                        String derrota = juego.derrota();
                        vista.msgMuerte(derrota);
                        //Muestra los resultados del turno
                        String resultado = juego.resultado();
                        vista.mostrarResultado(resultado);

                        //Se confirma si el juego sigue
                        if(juego.getGame()==true){
                            op = 4;  
                        }

                        turnos++;
                    break;

                    //OPCION 2: Usar habilidad
                    case 2:
                        //Pide la habilidad a utilizar
                        String habilidad = vista.pedirHabilidad();

                        //Si es acompanante es mascota
                        if(habilidad.equals("mascota")){
                            int lugar = turnos;
                            if(juego.getObjetivo(lugar).getMascota()){
                                String msgMascota = juego.mascota();
                                vista.msgIncio(msgMascota);
                            //Si es acompanante es clon de Raidboss
                            }else if(juego.getObjetivo(lugar).getTipo().equals("raidboss")){
                                if(habilidad.equals("clonar")){
                                    lugar = vista.pedirObjetivo();
                                    Combatiente clon = juego.getObjetivo(lugar);
                                    int jefe = lugar;
                                    String msgClon = juego.clonar(jefe, juego.getObjetivo(lugar).getHabilidad(), clon);
                                    //se asegura que se creo el clon
                                    if(msgClon != ""){
                                        juego.getObjetivo(lugar).setHabilidadClonar(true);
                                    }else{
                                        juego.getObjetivo(lugar).setHabilidadClonar(false);
                                    }
                                    vista.msgIncio(msgClon);
                                //habilidad de liberar clones
                                } else if(habilidad.equals("liberar")){
                                    lugar = turnos;
                                    int jefe = lugar;
                                    juego.liberar(jefe);
                                    juego.getObjetivo(jefe).setHabilidadNueva(true);
                                }
                            }
                        }
                        int id2 = vista.pedirObjetivo();
                        //Pide el objetivo del ataque
                        Combatiente objetivo2 = juego.getObjetivo(id2);
                        juego.usarHabilidad(habilidad, objetivo2, id2);
                        //Verifica si hubo una derrota
                        String derrota2 = juego.derrota();
                        vista.msgMuerte(derrota2);
                        //Muestra los resultados del turno
                        String resultado2 = juego.resultado();
                        vista.mostrarResultado(resultado2);

                        //Se confirma si el juego sigue
                        if(juego.getGame()==true){
                            op = 4;  
                        }

                        turnos++;
                    break;
                    
                    //OPCION 3: Pasar turno
                    case 3:
                        juego.pasarTurno();
                        turnos++;
                    break;

                    //OPCION 4: Salir
                    case 4:
                        op = 4;
                    break;

                    default://En caso de valor de opcion invalido
                    vista.avisoError();       
                }
            }
            vista.turnos(turnos);
            //Mensaje de despedida
            vista.despedida();
        }catch(Exception e){
            String s = "Ocurrio un error en la ejecucion del juego" + e.getMessage();
            throw new Exception(s);
        }
    }
}

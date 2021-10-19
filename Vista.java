import java.util.Scanner;

/**
 * Clase Vista
 * Encargada de recibir y mostrarle la información al usuario.
 * 
 * @version 1.0, 27/09/2021
 * finalizacion 18/10/2021
 * 
 * @author Diego E. Lemus L. - 21469
 */

 public class Vista {
    //-----PROPIEDADES-----
    Scanner scan = new Scanner(System.in);

    //-----METODOS-----
    /** 
     * Método que muestra el menú, además lee y devuelve la opción del usuario.
     * @return int op, seleccionada por el usuario (1-4)
     */
    public int mostrarMenu() throws Exception{
        int op = 0;

        try{
            System.out.println("\n\n\n--------------------Menu--------------------");
            System.out.println("Opcion 1: Atacar");
            System.out.println("Opcion 2: Usar habilidad");
            System.out.println("Opcion 3: Pasar turno");
            System.out.println("OPcion 4: Salir\n");
            op = Integer.parseInt(scan.nextLine());
        }catch(Exception e){
            String s = "Ocurrio un error en el ingreso de opcion "+ e.toString();
            throw new Exception(s);
        }
        return op;
    }

    /** 
     * Método que imprime la bienvenida.
     */
    public void bienvenida(){
        System.out.println("\nBienvenido al RPG Time Knight\n\n");
    }

    /** 
     * Método que indica que el ingreso de opcion no es válido.
     */
    public void ingresoIncorrecto(){
        System.out.println("\n%% Opcion invalida, intentelo otra vez. %%");
    }

    /** 
     * Método que imprime la despedida.
     */
    public void despedida(){
        System.out.println("\n\nHa salido del juego, que tenga un feliz día. :)\n");
    }

    /** 
     * Aviso cuando ocurrio un error en la ejecucion del programa
     */
    public void avisoError(){
        System.out.println("Ha ocurrido un error al ejecutar el programa.");
    }

    /** 
     * Metodo que pide el tipo de combatiente del jugador
     * @return String
     * @throws Exception
     */
    public String pedirTipoJugador() throws Exception{
        String tipo = "";
        
        try{
            System.out.println("Jugador, ingrese el nombre del rol que desea elegir:");
            System.out.println("- Guerrero: vida = 50 y ataque = 5");
            System.out.println("- Explorador: vida = 30 y ataque = 3");
            tipo = scan.nextLine();
        }catch(Exception e){
            String s = "Ocurrio un error en el ingreso de tipo "+ e.toString();
            throw new Exception(s);
        }
        return tipo;
    }

    /** 
     * Metodo que pide el tipo de combatiente del jugador
     * @return String
     * @throws Exception
     */
    public int pedirCantJugador() throws Exception{
        int cant = 0;
        
        try{
            System.out.println("Ingrese la cantidad de jugadores que desea generar:");
            cant = Integer.parseInt(scan.nextLine());
        }catch(Exception e){
            String s = "Ocurrio un error en el ingreso de la cantidad "+ e.toString();
            throw new Exception(s);
        }
        return cant;
    }

    /** 
     * Metodo que pide el objetivo de un ataque de un combatiente
     * @return int
     * @throws Exception
     */
    public int pedirObjetivo() throws Exception{
        int id = 0;

        try{
            System.out.println("Ingrese el id del objetivo al que quiere atacar:");
            id = Integer.parseInt(scan.nextLine());
        }catch(Exception e){
            String s = "Ocurrio un error al ingresar el objetivo "+ e.toString();
            throw new Exception(s);
        }
        return id;
    }

    /** 
     * Metodo que pide la habilidad especial a usar por un combatiente
     * @return String
     * @throws Exception
     */
    public String pedirHabilidad() throws Exception{
        String habilidad = "";

        try{
            System.out.println("Ingrese el nombre de la habilidad/item que desea usar:");
            System.out.println("Jugador:");
            System.out.println("- Botiquin");
            System.out.println("- Pocion de fuerza");
            System.out.println("- Carga Espada Ancestral");
            System.out.println("- Hoja Oculta");
            System.out.println("- Ballesta Portable");
            System.out.println();
            System.out.println("Enemigo:");
            System.out.println("Duende - Horda");
            System.out.println("Hechicero - Pocion Curacion");
            habilidad = scan.nextLine();
        }catch(Exception e){
            String s = "Ocurrio un error al ingresar la habilidad "+ e.toString();
            throw new Exception(s);
        }
        return habilidad;
    }

    /** 
     * Metodo que imprime el mensaje de inicio
     * @param msg
     */
    public void msgIncio(String msg){
        System.out.println(msg);
    }

    /** 
     * Metodo que imprime el mensaje de muerte
     * @param msg
     */
    public void msgMuerte(String msg){
        System.out.println(msg);
    }

    /** 
     * Metodo que imprime el resultado del juego
     * @param msg
     */
    public void mostrarResultado(String msg){
        System.out.println(msg);
    }
 
    /** 
     * Metodo que imprime el mensaje de victoria
     * @param msg
     */
    public void msgVictoria(String msg){
        System.out.println(msg);
    }
 
    /** 
     * Metodo que imprime el mensaje de derrota
     * @param msg
     */
    public void msgDerrota(String msg){
        System.out.println(msg);
    }
 
    /** 
     * Metodo que imprime el historial del juego
     * @param historial
     */
    public void historial(String historial){
        System.out.println(historial);
    }

    /** Metodo que imprime la cantidad de turnos del juego
     * @param t
     * @return String
     */
    public String turnos(int t){
        String turnos = "La cantidad de turnos que duro esta partida fue: "+t;
        return turnos;
    }
}

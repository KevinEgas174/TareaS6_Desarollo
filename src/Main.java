import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tarea[] tareas = new Tarea[6];

        System.out.println("=== REGISTRO DE 6 TAREAS DEL SPRINT ===");
        for (int i = 0; i < tareas.length; i++) {
            System.out.println("\n--- Tarea " + (i + 1) + " ---");
            String codigo = leerTextoNoVacio(sc, "Ingrese codigo: ");
            String titulo = leerTextoNoVacio(sc, "Ingrese titulo: ");
            int horas = leerEnteroPositivo(sc, "Ingrese horas estimadas: ");
            boolean completada = leerBooleano(sc, "Esta completada? (si/no): ");
            tareas[i] = new Tarea(codigo, titulo, horas, completada);
        }

        AnalizadorTareas analizador = new AnalizadorTareas(tareas);

        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Tareas completadas: " + analizador.contarCompletadas());
        System.out.println("Horas totales estimadas: " + analizador.calcularHorasTotales());
        System.out.printf("Porcentaje de avance: %.2f%%\n", analizador.calcularPorcentajeCompletadas());

        Tarea mayor = analizador.obtenerTareaMayorDuracion();
        if (mayor != null) {
            System.out.println("Tarea de mayor duracion: " + mayor);
        } else {
            System.out.println("No hay tareas registradas.");
        }

        System.out.println("\n--- Busqueda de Tarea (Existente) ---");
        String codBuscar1 = leerTextoNoVacio(sc, "Ingrese codigo a buscar: ");
        Tarea encontrada1 = analizador.buscarPorCodigo(codBuscar1);
        System.out.println(encontrada1 != null ? "Encontrada: " + encontrada1 : "Tarea no encontrada.");

        System.out.println("\n--- Busqueda de Tarea (Prueba 2) ---");
        String codBuscar2 = leerTextoNoVacio(sc, "Ingrese otro codigo a buscar: ");
        Tarea encontrada2 = analizador.buscarPorCodigo(codBuscar2);
        System.out.println(encontrada2 != null ? "Encontrada: " + encontrada2 : "Tarea no encontrada.");

        sc.close();
    }

    private static String leerTextoNoVacio(Scanner sc, String mensaje) {
        String entrada = "";
        while (entrada.isEmpty()) {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Error: El texto no puede estar vacio.");
            }
        }
        return entrada;
    }

    private static int leerEnteroPositivo(Scanner sc, String mensaje) {
        int numero = -1;
        while (numero <= 0) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(linea);
                if (numero <= 0) {
                    System.out.println("Error: Debe ingresar un numero entero mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada invalida. Ingrese un numero entero.");
            }
        }
        return numero;
    }

    private static boolean leerBooleano(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("si") || resp.equals("true") || resp.equals("1")) {
                return true;
            }
            if (resp.equals("no") || resp.equals("false") || resp.equals("0")) {
                return false;
            }
            System.out.println("Error: Responda 'si' o 'no'.");
        }
    }
}
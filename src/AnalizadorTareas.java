public class AnalizadorTareas {
    private Tarea[] tareas;

    public AnalizadorTareas(Tarea[] tareas) {
        this.tareas = tareas;
    }

    public int contarCompletadas() {
        int contador = 0;
        for (int i = 0; i < tareas.length; i++) {
            if (tareas[i] != null && tareas[i].isCompletada()) {
                contador++;
            }
        }
        return contador;
    }

    public int calcularHorasTotales() {
        int total = 0;
        for (int i = 0; i < tareas.length; i++) {
            if (tareas[i] != null) {
                total += tareas[i].getHorasEstimadas();
            }
        }
        return total;
    }

    public Tarea buscarPorCodigo(String codigo) {
        for (int i = 0; i < tareas.length; i++) {
            if (tareas[i] != null && tareas[i].getCodigo().equalsIgnoreCase(codigo)) {
                return tareas[i];
            }
        }
        return null;
    }

    public Tarea obtenerTareaMayorDuracion() {
        Tarea mayor = null;
        for (int i = 0; i < tareas.length; i++) {
            if (tareas[i] != null) {
                if (mayor == null || tareas[i].getHorasEstimadas() > mayor.getHorasEstimadas()) {
                    mayor = tareas[i];
                }
            }
        }
        return mayor;
    }

    public double calcularPorcentajeCompletadas() {
        int validas = 0;
        for (int i = 0; i < tareas.length; i++) {
            if (tareas[i] != null) {
                validas++;
            }
        }
        if (validas == 0) {
            return 0.0;
        }
        return (contarCompletadas() * 100.0) / validas;
    }
}
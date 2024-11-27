/*Ejercicio 1: Gestión de Alumnos y Notas*/
import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    @Override
    public String toString() {
        return "Cátedra: " + catedra + ", Nota: " + notaExamen;
    }
}

class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        this.notas.add(nota);
    }

    public double calcularPromedio() {
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return notas.isEmpty() ? 0 : suma / notas.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alumno: ").append(nombreCompleto).append(", Legajo: ").append(legajo).append("\n");
        sb.append("Notas:\n");
        for (Nota nota : notas) {
            sb.append("  ").append(nota).append("\n");
        }
        sb.append("Promedio: ").append(calcularPromedio()).append("\n");
        return sb.toString();
    }
}

public class CargaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.println("Ingrese la cantidad de alumnos:");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Ingrese el nombre completo del alumno:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el legajo del alumno:");
            long legajo = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer

            Alumno alumno = new Alumno(nombre, legajo);

            System.out.println("Ingrese la cantidad de notas:");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese la cátedra:");
                String catedra = scanner.nextLine();
                System.out.println("Ingrese la nota:");
                double nota = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer

                alumno.agregarNota(new Nota(catedra, nota));
            }

            alumnos.add(alumno);
        }

        System.out.println("\n--- Información de los Alumnos ---");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }

        scanner.close();
    }
}

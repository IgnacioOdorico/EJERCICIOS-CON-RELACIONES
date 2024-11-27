/*Ejercicio 2: Menú de Restaurante*/
import java.util.ArrayList;
import java.util.Scanner;

class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadMedida;

    public Ingrediente(String nombre, double cantidad, String unidadMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return nombre + " " + cantidad + " " + unidadMedida;
    }
}

class Plato {
    private String nombreCompleto;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plato: ").append(nombreCompleto).append("\n");
        sb.append("Precio: $").append(precio).append("\n");
        if (!esBebida) {
            sb.append("Ingredientes:\n");
            for (Ingrediente ingrediente : ingredientes) {
                sb.append("  ").append(ingrediente).append("\n");
            }
        }
        return sb.toString();
    }
}

public class MenuRestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platosMenu = new ArrayList<>();

        System.out.println("Ingrese la cantidad de platos:");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.println("Ingrese el nombre del plato:");
            String nombrePlato = scanner.nextLine();
            System.out.println("Ingrese el precio del plato:");
            double precioPlato = scanner.nextDouble();
            System.out.println("¿Es bebida? (true/false):");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine(); // Limpiar buffer

            Plato plato = new Plato(nombrePlato, precioPlato, esBebida);

            if (!esBebida) {
                System.out.println("Ingrese la cantidad de ingredientes:");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.println("Ingrese el nombre del ingrediente:");
                    String nombreIngrediente = scanner.nextLine();
                    System.out.println("Ingrese la cantidad:");
                    double cantidad = scanner.nextDouble();
                    System.out.println("Ingrese la unidad de medida:");
                    scanner.nextLine(); // Limpiar buffer
                    String unidadMedida = scanner.nextLine();

                    plato.agregarIngrediente(new Ingrediente(nombreIngrediente, cantidad, unidadMedida));
                }
            }

            platosMenu.add(plato);
        }

        System.out.println("\n--- Menú del Restaurante ---");
        for (Plato plato : platosMenu) {
            System.out.println(plato);
        }

        scanner.close();
    }
}

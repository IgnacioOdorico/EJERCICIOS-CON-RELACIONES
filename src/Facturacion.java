/*Ejercicio 3: Facturación*/
import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuentoItem;
    private double subtotal;

    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoItem = (cantidad > 5) ? precioUnitario * 0.1 : 0;
        this.subtotal = (precioUnitario - descuentoItem) * cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %.2f %.2f %.2f",
                codigoArticulo, nombreArticulo, cantidad, precioUnitario, descuentoItem, subtotal);
    }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private String cliente;
    private double totalCalculadoFactura;
    private ArrayList<DetalleFactura> detallesFactura;

    public Factura() {
        this.detallesFactura = new ArrayList<>();
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        this.detallesFactura.add(detalle);
    }

    public void calcularMontoTotal() {
        totalCalculadoFactura = 0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubtotal();
        }
    }

    public void imprimirFactura() {
        System.out.println("Fecha: " + fechaFactura);
        System.out.println("Número: " + numeroFactura);
        System.out.println("Cliente: " + cliente);
        System.out.println("Código Artículo Nombre Artículo Cantidad Precio Unitario Descuento Subtotal");
        for (DetalleFactura detalle : detallesFactura) {
            System.out.println(detalle);
        }
        System.out.println("Total: " + totalCalculadoFactura);
    }
}

public class Facturacion {
    private static final String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        // Solicitar datos de la factura
        System.out.println("Ingrese la fecha de la factura:");
        factura.setFechaFactura(scanner.nextLine());

        System.out.println("Ingrese el número de la factura (entero positivo):");
        long numeroFactura;
        while (true) {
            numeroFactura = scanner.nextLong();
            if (numeroFactura > 0) break;
            System.out.println("El número debe ser mayor a cero. Intente nuevamente.");
        }
        factura.setNumeroFactura(numeroFactura);

        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese el nombre del cliente:");
        String cliente;
        while (true) {
            cliente = scanner.nextLine();
            if (!cliente.isEmpty()) break;
            System.out.println("El cliente no puede estar vacío. Intente nuevamente.");
        }
        factura.setCliente(cliente);

        // Cargar detalles de la factura
        while (true) {
            System.out.println("Ingrese el código del artículo a facturar:");
            String codigoArticulo = scanner.nextLine();
            String[] articulo = buscarArticulo(codigoArticulo);

            if (articulo == null) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
                continue;
            }

            System.out.println("Ingrese la cantidad a facturar:");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            double precioUnitario = Double.parseDouble(articulo[2]);
            DetalleFactura detalle = new DetalleFactura(articulo[0], articulo[1], cantidad, precioUnitario);
            factura.agregarDetalle(detalle);

            System.out.println("¿Desea agregar otro artículo? (S/N)");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("N")) break;
        }

        // Calcular el total y mostrar la factura
        factura.calcularMontoTotal();
        factura.imprimirFactura();

        scanner.close();
    }

    private static String[] buscarArticulo(String codigo) {
        for (String[] articulo : articulos) {
            if (articulo[0].equals(codigo)) {
                return articulo;
            }
        }
        return null;
    }
}

package clases;

public class CartItem {
    private ParcelPaquete product;
    private int quantity;
    private  int cantidadProductos;

    public int getCantidadProductos() {
        return cantidadProductos;
    }
    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ParcelPaquete getProduct() {
        return product;
    }

    public void setProduct(ParcelPaquete product) {
        this.product = product;
    }


}

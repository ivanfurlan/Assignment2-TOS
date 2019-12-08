////////////////////////////////////////////////////////////////////
// IVAN FURLAN 1161622
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    private ItemType tipo;
    private String nome;
    private double prezzo;

    public MenuItem(ItemType tipo, String nome, double prezzo) {
        this.tipo = tipo;
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public ItemType getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

}

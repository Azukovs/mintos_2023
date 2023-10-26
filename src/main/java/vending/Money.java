package vending;

import static java.lang.Double.parseDouble;

public class Money {
    private int euros;
    private int cents;

    public Money() {
        this.euros = 0;
        this.cents = 0;
    }

    public Money(int euros, int cents) {
        this.euros = euros;
        this.cents = cents;
    }

    public Money(double value) {
        this.euros = (int) value;
        this.cents = (int) (value * 100) % 100;
    }

    public int getEuros() {
        return euros;
    }

    public void setEuros(int euros) {
        this.euros = euros;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }

    public void add(Money increaseBy) {
        int newCents = this.getCents() + increaseBy.getCents();
        int newEuros = this.getEuros() + increaseBy.getEuros();
        if (newCents >= 100) {
            newEuros++;
            newCents -= 100;
        }
        this.setCents(newCents);
        this.setEuros(newEuros);
    }

    public void add(String increaseBy) {
        add(new Money(parseDouble(increaseBy)));
    }

    public void remove(Money decreaseBy) {
        int newCents = this.getCents() - decreaseBy.getCents();
        int newEuros = this.getEuros() - decreaseBy.getEuros();
        if (newCents < 0) {
            newEuros--;
            newCents += 100;
        }
        if (newEuros < 0) {
            newCents = 0;
            newEuros = 0;
        }
        this.setCents(newCents);
        this.setEuros(newEuros);
    }

    public Money getDifference(Money offered) {
        int currentCents = this.euros * 100 + this.cents;
        int centDifference = currentCents - (offered.euros * 100 + offered.cents);
        double euroDifference = (double) centDifference / 100;
        return new Money((int) euroDifference, (int) (euroDifference * 100) % 100);
    }

    public double asDouble() {
        return (double) (this.euros * 100 + this.cents) / 100;
    }
}
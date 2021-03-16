import java.util.Objects;

public class Complex {
    private final double re;   // partea reala
    private final double im;   // partea imaginara
    
    // constructorul care creaza un obiect (numar) complex cu parte reala si iaginara
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    // metoda care returneaza string-ul corespunzator unui numar complex
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    // modulul
    public double abs() {
        return Math.hypot(re, im);
    }
    
    public double phase() {
        return Math.atan2(im, re);
    }
    
    // suma dintre numarul curent (this) si un alt numar complex b
    public Complex suma(Complex b) {
        Complex a = this; // numarul complec curent - this === a
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // diferenta dintre numarul curent (this) si un alt numar complex b
    public Complex diferenta(Complex b) {
        Complex a = this; // numarul complec curent - this === a
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // produsul dintre numarul curent (this) si un alt numar complex b
    public Complex inmultire(Complex b) {
        Complex a = this; // numarul complec curent - this === a
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // inmultirea numarului complex curent cu un numar (alpha)
    public Complex inmultireCuAlpha(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // conjugata numarului complex curent
    public Complex conjugata() {
        return new Complex(re, -im);
    }

    // reciproca numarului complex curent
    public Complex reciproca() {
        double inmultireCuAlpha = re*re + im*im;
        return new Complex(re / inmultireCuAlpha, -im / inmultireCuAlpha);
    }

    // partea reala / imaginara
    public double re() { return re; }
    public double im() { return im; }

    // impartirea dintre numarul curent (this) si un alt numar complex b
    public Complex impartire(Complex b) {
        Complex a = this; // numarul complec curent - this === a
        return a.inmultire(b.reciproca());
    }

    // returneaza valoarea exponentiala a numarului complex curent
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // returneaza valoarea sinusului numarului complex curent
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    // returneaza valoarea cosinusului numarului complex curent
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    // returneaza valoarea tangentei numarului complex curent
    public Complex tan() {
        return sin().impartire(cos());
    }
    
    // versiunea 2 pentru suma (nu folosim obiectCurent.suma(alDoileaNumar))
    public static Complex suma(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Complex that = (Complex) x;
        return (this.re == that.re) && (this.im == that.im);
    }

    public int hashCode() {
        return Objects.hash(re, im);
    }

    // exemplu pentru demo
    public static void main(String[] args) {
        Complex a = new Complex(12.0, 3.0);
        Complex b = new Complex(-8.0, 4.2);

        System.out.println("a            = " + a);
        System.out.println("b            = " + b);
        System.out.println("Re(a)        = " + a.re());
        System.out.println("Im(a)        = " + a.im());
        System.out.println("b + a        = " + b.suma(a));
        System.out.println("a - b        = " + a.diferenta(b));
        System.out.println("a * b        = " + a.inmultire(b));
        System.out.println("b * a        = " + b.inmultire(a));
        System.out.println("a / b        = " + a.impartire(b));
        System.out.println("(a / b) * b  = " + a.impartire(b).inmultire(b));
        System.out.println("conj(a)      = " + a.conjugata());
        System.out.println("|a|          = " + a.abs());
        System.out.println("tan(a)       = " + a.tan());
    }
}
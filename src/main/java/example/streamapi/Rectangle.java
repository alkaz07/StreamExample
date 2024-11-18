package example.streamapi;

public class Rectangle {
    private double w, h;

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Rectangle(double w, double h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "w=" + w +
                ", h=" + h +
                '}';
    }

    public double getPerimeter(){
        return 2*(w+h);
    }
}

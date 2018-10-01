package com.company;
import java.lang.Math;
import java.lang.*;
public class Main {


    public interface Hello{
        void sayHi();
    }

    public static abstract class Shape {
        public abstract double area();
        public abstract double perimeter();
    }

    public static class Rectangle extends Shape {
        private double width, length;

        public Rectangle() {
            this(1, 1);
        }

        public Rectangle(double width, double length) {
            this.width = width;
            this.length = length;
        }

        public double area() {
            return width * length;
        }

        public double perimeter() {
            return 2 * (width + length);
        }

    }

    public static class Circle extends Shape {
        private double radius;
        final double p = Math.PI;

        public Circle() {
            this(1);
        }

        public Circle(double radius) {
            this.radius = radius;
        }

        public double area() {
            return p * Math.pow(radius, 2);
        }

        public double perimeter() {
            return 2 * p * radius;
        }
    }
    public static void main(String[] args){
        double width=5,length=7;
        Shape rectangle = new Rectangle(width,length);
        System.out.println("Rectangle width: "+ width + " and length:" + length + "\n Result area: " +rectangle.area() + "\n Result perimeter: " +  rectangle.perimeter()+ "\n");
        Hillo object= new Hillo();
        object.sayHi();
    }

}

class Rectangle extends Main.Shape {
    private double width, length;

    public Rectangle() {
        this(1, 1);
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public double area() {
        return width * length;
    }

    public double perimeter() {
        return 2 * (width + length);
    }

}

class Circle extends Main.Shape {
    private double radius;
    final double p = Math.PI;

    public Circle() {
        this(1);
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return p * Math.pow(radius, 2);
    }

    public double perimeter() {
        return 2 * p * radius;
    }
}



class Hillo implements Main.Hello{
    public  void sayHi(){
        System.out.println("Hello, i am here !");
    }
}



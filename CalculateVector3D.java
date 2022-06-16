import java.util.*;

public class CalculateVector3D{
    Random rand = new Random();
    static CalculateVector3D cv = new CalculateVector3D();
    static CreateVector vect = cv.generateVector();
    static CreateVector vect1 = cv.generateVector();

    public CreateVector generateVector(){
          return new CreateVector(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10));
    }
    
    public double calculateLengthVector(){
       return  Math.sqrt(vect.x*vect.x + vect.y*vect.y + vect.z*vect.z);
    }
    
    public double peremnozhitScalyarno(){
        return (vect.x*vect1.x + vect.y*vect1.y + vect.z*vect.z);
    }
    
    public CreateVector peremnozhitVectorno(){
        return new CreateVector((vect.y*(vect1.z) - vect.z*(vect1.y)), (vect.z*(vect1.x) - vect.x*(vect1.z)), (vect.x*(vect1.y) - vect.y*vect1.x));
    }
    
    public double calculateDegrees(){
        return 57.32*Math.acos(peremnozhitScalyarno() / (calculateLengthVector()*calculateLengthVector()));
    }
    
    
    public CreateVector summVectors(){
       return new CreateVector(vect.x + vect1.x, vect.y + vect1.y, vect.z + vect1.z);
    }
    
    public CreateVector raznostVectors(){
        return new CreateVector(vect.x - vect1.x, vect.y - vect1.y, vect.z - vect1.z);
    }
    
    public static void main(String args[]){
        System.out.println(cv.calculateLengthVector());
        System.out.println(cv.peremnozhitScalyarno());
        System.out.println(cv.peremnozhitVectorno());
        System.out.println(cv.calculateDegrees());
        System.out.println(cv.summVectors());
        System.out.println(cv.raznostVectors());
    }    
    
    public class CreateVector{
        private double x;
        private double y;
        private double z;
        
        public CreateVector(double x, double y, double z){
            this.x = x;
            this.y = y;
            this.z = z;    
    }
        public String toString(){
            return  "Vector { X = " + x + "," + " Y = " + y + "," + " Z = " + z + "}";
                }        
    }
}
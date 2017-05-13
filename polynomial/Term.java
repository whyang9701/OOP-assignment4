public class Term{
    private double coefficient = 0;
    private double power = 0;
    public Term(double coefficient,double power){
        this.coefficient = coefficient;
        this.power = power;
    }
    public double getCoefficient(){
        return this.coefficient;
    }
    public double getPower(){
        return this.power;
    }
    public String toString(){
        return "("+coefficient+","+power+")";
    }
}
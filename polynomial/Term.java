public class Term{
    public double coefficient = 0;
    public double power = 0;
    public Term(double coefficient,double power){
        this.coefficient = coefficient;
        this.power = power;
    }
    public String toString(){
        return "("+coefficient+","+power+")";
    }

    public Term clone(){
        return new Term(coefficient,power);
    }
}
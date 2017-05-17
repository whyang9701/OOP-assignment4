public class Main{
    public static void main(String args[]){
        Polynomial p1 = new Polynomial(new Term(6,6));
        p1.add(new Polynomial(new Term(5,5)));
        p1.add(new Polynomial(new Term(4,4)));
        p1.add(new Polynomial(new Term(3,3)));
        p1.add(new Polynomial(new Term(2,2)));
        Polynomial p2 = new Polynomial(new Term(1,1));
        p2.add(new Polynomial(new Term(4,4)));
        p2.add(new Polynomial(new Term(3,3)));
        p2.add(new Polynomial(new Term(2,2)));
        p2.add(new Polynomial(new Term(5,5)));
        p1.print();
        p2.print();
        p1.multiply(p2);
        p1.print();
    }
    
    

}
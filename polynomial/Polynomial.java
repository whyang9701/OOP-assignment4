import java.util.LinkedList;

public class Polynomial{
    private LinkedList<Term> polynomial_terms ;
    public Polynomial(Term term){
        polynomial_terms = new LinkedList<>();
        polynomial_terms.add(term);
    }
    public Polynomial add(Polynomial out_polynomial){
        LinkedList<Term> out_polynomial_terms = out_polynomial.polynomial_terms;
        return new Polynomial(new Term(0,0));
    }
    public Polynomial multiply(Polynomial polynomial){
        return new Polynomial(new Term(0,0));
    }
    public void print(){
        String result = "";
        for(Term term : polynomial_terms){
            result += term.toString();
        }
        System.out.println(result);
    }
}
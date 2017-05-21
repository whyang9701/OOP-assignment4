import java.util.LinkedList;


public class Polynomial{
    private LinkedList<Term> polynomial_terms ;
    private Polynomial(){
        polynomial_terms = new LinkedList<>();
    }
    public Polynomial(Term term){
        polynomial_terms = new LinkedList<>();
        polynomial_terms.add(term);
    }
    public void add(Polynomial out_polynomial){
        polynomial_terms.addAll(out_polynomial.polynomial_terms);
        for(int i = 0 ; i<polynomial_terms.size() ; i++){
            for(int j = 0 ; j<polynomial_terms.size() ; j++){
                if(polynomial_terms.get(i).power > polynomial_terms.get(j).power){
                    termsExchange(i, j);
                }
            }
        }
        
        for(int i = 0 ; i<polynomial_terms.size()-1 ; i++){
            if(polynomial_terms.get(i).power == polynomial_terms.get(i+1).power){
                polynomial_terms.get(i).coefficient += polynomial_terms.get(i+1).coefficient;
                polynomial_terms.remove(i+1);
                i--;
            }
        }
        
    }
    private void termsExchange(int i , int j){
        Term term1 = (Term)polynomial_terms.get(i).clone();
        Term term2 = (Term)polynomial_terms.get(j).clone();
        polynomial_terms.get(i).coefficient = term2.coefficient;
        polynomial_terms.get(i).power = term2.power;
        polynomial_terms.get(j).coefficient = term1.coefficient;
        polynomial_terms.get(j).power = term1.power;
    }
    public void multiply(Polynomial polynomial){
        Polynomial temPolynomial = new Polynomial();
        for(int i = 0 ; i<polynomial_terms.size() ; i++){
            for(int j = 0 ; j<polynomial.polynomial_terms.size() ; j++){
                Term term1 = polynomial_terms.get(i);
                Term term2 = polynomial.polynomial_terms.get(j);
                temPolynomial.add(new Polynomial(new Term(term1.coefficient*term2.coefficient,term1.power+term2.power)));
            }
        }
        polynomial_terms = temPolynomial.polynomial_terms;
    }
    public void print(){
        String result = "";
        for(int i = 0 ; i<polynomial_terms.size() ; i++){
            result += polynomial_terms.get(i).toString();
            if(i != polynomial_terms.size()-1){
                result+=",";
            }
        }
        System.out.println(result);
    }
}
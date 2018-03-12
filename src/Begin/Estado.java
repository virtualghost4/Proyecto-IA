package Begin;

public class Estado implements Comparable{
    
    public int x;
    public int y;
    public char oper; 
    public Estado predecesor;
    public double calidad;
    
    public Estado(int x, int y, char oper,Estado predecesor) {
        this.x=x;
        this.y=y;
        this.oper=oper;
        this.predecesor=predecesor;
        calidad = 0;
    }
    
    @Override
    public boolean equals(Object x) {
        Estado e=(Estado)x;
        return this.x==e.x && this.y==e.y;
    }
    
    public void distancia(Estado a){
       
       int valorx, valory, x1,x2,y1,y2; 
       x1 = this.x;
       x2 = a.x;
       y1 = this.y;
       y2 = a.y;
       if(x1>x2){
           valorx = x1-x2;
       }else{
           valorx = x2-x1;
       }
       
       if(y1>y2){
           valory= y1-y2;
       }else{
           valory= y2-y1;
       }
       calidad+=valorx+valory;
       
    }

    @Override
    public int compareTo(Object o) {
        Estado e=(Estado)o;
        if(this.calidad==e.calidad)return 0;
        else{
            if(this.calidad>=e.calidad)return 1;
            else return -1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        return hash;
    }
        
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}

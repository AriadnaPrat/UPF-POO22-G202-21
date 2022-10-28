import java.awt.*;
public class World{
    private int width;
    private int height;
    private int marge;
    private int numAgents=10;
    private Agent agents[]= new Agent[numAgents];

    //Este método produce un valor aleatorio dentro del margen del espacio de tipo Vec2D.
    private Vec2D randomPos(){
        double x=this.marge+Math.random()*(this.width-2*this.marge);
        double y=this.marge+Math.random()*(this.height-2*this.marge);
        return new Vec2D(x,y);
    }
    //Este método produce un valor aleatorio de tipo double dentro del margen del espacio.
    private double randomRadius(){
        return 5+Math.random()*(this.marge-5);
    }
    //World() es el constructor de la clase World que incorpora el valot de tipo double weidth, height y marge en los atributos de la clase world.
    //Luego se inicializan los 10 agentes con el constructor Agente() con un bucle y se crea un objetivo aleatorio.
    public World(int width, int height, int marge){
        this.width=width;
        this.height=height;
        this.marge=marge;
        for(int i=0;i<this.numAgents;i++){
            this.agents[i]=new Agent(randomPos(), randomRadius());
            this.agents[i].setTarget(randomPos());
        }
    }
    //En este método hay dos bucles para ir comparando cada agente con los otros agentes y ver que el radio sea mayor que la distancia 
    //entre dos agentes.
    //Si resulta que la suma de los dos radios es mayor a la distancia entre ellos entonces quiere decir que estan pegados. Si es este caso, 
    //entonces se incorpora un nuevo target.
    public void manageCollisions(){
        for(int i=0; i<this.numAgents;i++){
            for (int j=i+1;j<this.numAgents;j++){
                boolean boleano=this.agents[i].isColliding(this.agents[j]);
                if (boleano==true){
                    this.agents[j].setTarget(randomPos());
                    boleano=false;
                }
            }
        }
    }
    //En simulationStep se incorpora el valor de la velocidad con un bucle. Luego se hace un bucle para ir examinando la position de todos los agentes.
    //Y cuando el agente haya llegado a su target, entonces se pone un nuevo target.
    public void simulationStep(){
        for(int i=0;i<this.numAgents;i++){
            this.agents[i].setSpeed(1);
        }
        boolean boleano=false;
        for(int i=0;i<this.numAgents;i++){
            this.agents[i].updatePosition();
            boleano=this.agents[i].targetReached();
            if(boleano==true){
                this.agents[i].setTarget(randomPos());
                boleano=false;
            }
        }
    }

    //En este método se usa la función agentPaint para pintar cada uno de los agentes.
    public void paint(Graphics g){
        for(int i=0;i<this.numAgents; i++){
            this.agents[i].agentPaint(g);
        }
    }
}
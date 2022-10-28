import java.awt.*;
public class Agent{
    private Vec2D pos;
    private double radius;
    private Vec2D dir;
    private Vec2D target;
    private double speed;
    //Este es el constructor de la clase agent que se inicia con el radio y la posición inicial de clase Vec2D para que se pueda pintar 
    //en la pantalla.
    public Agent(Vec2D v, double r){
        this.pos=v;
        this.radius=r;
    }
    //En este método entra un valor de clase Vec2D para incorporarlo a la clase agent además de crear el "dir" normalizando 
    //la diferencia entre la posición y el target.
    public void setTarget(Vec2D target) {
        this.target=target;
        Vec2D dir=new Vec2D(target);
        dir.subtract(this.pos);
        dir.normalize();
        this.dir=dir;
    }
    //Este método incorpora el valor de la velocidad deseada al speed del agente.
    public void setSpeed(double speed){
        this.speed=speed;
    }
    //Este método se encarga de ir sumando al Vec2D pos del agente un incremento de la posición multiplicando la dirección con la velocidad.
    //Y luego devuelve pos con el incremento.
    public Vec2D updatePosition(){
        double x=this.dir.getX()*this.speed;
        double y=this.dir.getY()*this.speed;
        Vec2D v=new Vec2D(x,y);
        this.pos.add(v);
        return this.pos;
    }
    //Este método compara que la diferencia entre el target y la pos sea mayor que el radio del agente. Si es el caso, devuelve un false.
    //Si la diferencia es menor que el radio entonces devuelve true.
    public boolean targetReached(){
        Vec2D v;
        v=new Vec2D(this.target);
        v.subtract(this.pos);
        if (v.length()<this.radius){
            return true;
        }else{
            return false;
        }
    }
    //Compara la suma de los radios con la distancia que hay entre dos agentes. Si la suma de los dos radios es mayor devuelve true.
    //Si la distancia entre los agentes es mayor, devuelve false.
    public boolean isColliding(Agent agent){
        Vec2D v=new Vec2D(this.pos);
        v.subtract(agent.pos);
        double radius=this.radius+agent.radius;
        if (v.length()<radius){
            return true;
        }else{
            return false;
        }
    }
    //Este método pinta el agente en su posición inicial y con el tamaño del agente depende de su radio. 
    public void agentPaint(Graphics g){
            int x=(int)(this.pos.getX()-this.radius);
            int y=(int)(this.pos.getY()-this.radius);
		    int d=(int)(2*this.radius);
		    g.setColor(Color.RED) ;
		    g.fillOval(x,y,d,d);
    }
}
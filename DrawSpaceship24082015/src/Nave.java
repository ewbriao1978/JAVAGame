import java.awt.Image;

public class Nave {

	@Override
	public String toString() {
		return "Nave [nome=" + nome + ", codigo=" + codigo + ", X=" + X
				+ ", Y=" + Y + ", TAM=" + TAM + ", step=" + step + "]";
	}

	private String nome;
	private int codigo;
	private int X;
	private int Y;
	private int TAM;
	private int step;
	private Image sprite;
	
	
	public int getStep() {
		return step;

	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public int getTAM() {
		return TAM;
	}
	public void setTAM(int tAM) {
		TAM = tAM;
	}

	public Nave(String nome, int codigo, int x, int y, int tAM,Image sprite) {
		this.nome = nome;
		this.codigo = codigo;
		this.sprite = sprite;
		X = x;
		Y = y;
		TAM = tAM;
		step = 1;
	}
	
	
	public void setDirection(Direction direction){
		switch (direction){
		case NORTH: Y-=step; break;
		case SOUTH: Y+=step; break;
		case EAST: X+=step; break;
		case WEST: X-=step; break;
		}
		
	}

	boolean colidiu(Nave p){
		
		/*System.out.println("X:" + X);
		System.out.println("Y:" + Y);
		System.out.println("TAM : " + TAM);
		System.out.println("X+TAM:" + X+TAM);
		System.out.println("Y+TAM:" + Y+TAM);
		System.out.println("p.X:" + p.X);
		System.out.println("p.Y:" + p.Y);
		System.out.println("p.TAM:" + p.TAM);*/
		

		if (((X+TAM) >= p.X) && (X+TAM <= (p.X + TAM))){
			if ((Y>=p.Y) && (Y< (p.Y + p.TAM))){
				return true;
			}
			if (((Y + TAM)>=p.Y) && ((Y+TAM) <= p.Y + p.TAM)){
				return true;
			}			
			return false;
		}		

		if (((X) >= p.X) && (X <= (p.X + p.TAM))){
			if ((Y>=p.Y) && (Y< (p.Y + p.TAM))){
				return true;
			}
			if (((Y + TAM)>=p.Y) && ((Y+TAM) <= p.Y + p.TAM)){
				return true;
			}
			return false;
		}
		
		// uma nave dentro da outra
		if ((X <= p.X) && (X+TAM >= p.X + p.TAM)) {
			if ((Y<= p.Y) && (Y+TAM) >= p.Y + p.TAM){
				return true;
			}
		}

		return false;
	}
	
	public Image getSprite() {
		return sprite;
	}
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

}

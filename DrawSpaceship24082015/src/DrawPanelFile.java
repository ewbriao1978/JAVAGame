import java.awt.Color;
import java.awt.Graphics; 
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawPanelFile extends JPanel
{  
	ArrayList<Nave> arraylines;
	int currentSpaceShip;
	boolean movingEnemy;
	boolean changeCourse;
	public DrawPanelFile() {
		changeCourse=false;
		movingEnemy=false;
		currentSpaceShip=0;
		arraylines = new ArrayList<Nave>();

		arraylines.add(new Nave("a1",1,10,10,30, new ImageIcon("nave.png").getImage()));
		arraylines.add(new Nave("a2",2,100,100,30,new ImageIcon("on.jpg").getImage()));
		arraylines.add(new Nave("a3",3,200,20,30,null));
		arraylines.add(new Nave("a4",3,400,500,30,new ImageIcon("nave.png").getImage()));
		this.setFocusable(true);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int myKey = e.getKeyCode();
				
				// s:83
				// c:67
				// up:38
				// down: 40
				// left: 37
				// right:39 
				if (myKey == 83) currentSpaceShip = (++currentSpaceShip) % arraylines.size();


				int X = arraylines.get(currentSpaceShip).getX();
				int Y = arraylines.get(currentSpaceShip).getY();
				System.out.println("Nave X:" + X + "  Y:" + Y);

				System.out.println(e.getKeyCode());


				if (myKey == 38){
					arraylines.get(currentSpaceShip).setY(Y-10);
					Image sprite = arraylines.get(currentSpaceShip).getSprite();
				}else if(myKey ==67){
					changeCourse = !changeCourse;
				}else if (myKey == 40){
					arraylines.get(currentSpaceShip).setY(Y+10);

				}else if (myKey == 37){
					arraylines.get(currentSpaceShip).setX(X-10);

				}else if (myKey == 39){
					arraylines.get(currentSpaceShip).setX(X+10);

				}else if (myKey == 81) System.exit(0);

				repaint();
			}
		});
	}


	public void moving(){
		int X = arraylines.get(3).getX();
		int Y = arraylines.get(3).getY();
		
		if (movingEnemy == false){
			X-=5;
			if (X<1) movingEnemy = true;
		}else{
			X+=5;
			if (X > 799) movingEnemy = false;
		}
		
		if (changeCourse == true){
			Y = (int) (400 +70*Math.sin(Math.toRadians(5*X)));
		}
		
		arraylines.get(3).setX(X);
		arraylines.get(3).setY(Y);
	}

	// -1 : no collision
	//0 or more : collision currents with n ship
	private int testCollision(int currentSS,ArrayList<Nave> a){

		for (int i=0; i<a.size(); i++){

			if ((currentSS != i) && (a.get(currentSS).colidiu(a.get(i)))){
				return i;
			}
		}
		return -1;
	}


	private void DrawOneShip(Nave n, Graphics g){
		if (n.getSprite() == null){
			g.drawRect(n.getX(), n.getY(), n.getTAM(), n.getTAM());
		}else
			g.drawImage(n.getSprite(),n.getX(),n.getY(),n.getTAM(),n.getTAM(),null);
	}

	
	private void upToDateCoords(ArrayList<Nave> a, Graphics g){

		int targetSS;
		if ((targetSS=testCollision(currentSpaceShip,a)) >=0){ 
			System.out.println("Nave " + currentSpaceShip + " se chocou contra + " + targetSS);
			a.get(currentSpaceShip).setSprite(new ImageIcon("explosion.jpg").getImage());
			a.get(targetSS).setSprite(new ImageIcon("explosion.jpg").getImage());
		}

		for (int i =0; i<a.size(); i++){

			DrawOneShip(a.get(i),g);

		}

	}



	public void paintComponent( Graphics g ){

		// default
		super.paintComponent( g );
		Image imagem = new ImageIcon("back.jpg").getImage();          
		g.drawImage(imagem, 0, 0,800,600, this)  ;

		upToDateCoords(arraylines,g);

	} // end method paintComponent
	// end class DrawPanel

}
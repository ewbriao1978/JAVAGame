import javax.swing.JFrame;

public class DrawPanelTest
{
   public static void main( String args[] ) throws InterruptedException
   {
      // create a panel that contains our drawing
      DrawPanelFile panel = new DrawPanelFile();
      
      // create a new frame to hold the panel
      JFrame application = new JFrame();
      
      // set the frame to exit when it is closed
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

      application.add( panel ); // add the panel to the frame      
      application.setSize( 800, 600 ); // set the size of the frame
      application.setVisible( true ); // make the frame visible    
      
      while (true){
    	  panel.moving();
    	  panel.repaint();
    	  Thread.sleep(100);
      }
   } // end main
} // end class DrawPanelTest
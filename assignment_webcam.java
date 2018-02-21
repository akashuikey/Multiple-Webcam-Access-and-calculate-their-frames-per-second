package final_assignment;

import java.awt.EventQueue;
import static com.googlecode.javacv.cpp.opencv_core.cvPutText;

import javax.swing.JFrame;
import javax.swing.JButton;

import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_core.CvFont;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import org.opencv.core.Core;
import org.opencv.core.Scalar;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class assignment_webcam {

	private JFrame frame;
	 int count=0;
	 private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					assignment_webcam window = new assignment_webcam();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public assignment_webcam() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

			 
	private void initialize() {
		frame = new JFrame("Akash Camera Application");
		frame.setBounds(100, 100, 450, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Open Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final IplImage image = cvLoadImage("C:\\Users\\Akash\\Desktop\\Shubhangi-Atre-Poorey-02.jpg");
		         
		        //create canvas frame named 'Demo'
		        final CanvasFrame canvas = new CanvasFrame("Demo");
		         
		        //Show image in canvas frame
		        canvas.showImage(image);
		         
		        //This will close can0vas frame on exit
		        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
			}
			
		});
		btnNewButton.setBounds(52, 351, 130, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Open WebCam");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread webcam = new Thread() {
					public void run() {
				CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);
				opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 300);
				opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 300);
				IplImage grabbedImage = opencv_highgui.cvQueryFrame(capture);
				CanvasFrame frame = new CanvasFrame("Akash Webcam");
				while(frame.isVisible()&& (grabbedImage=opencv_highgui.cvQueryFrame(capture))!=null)
				{	double time1,time2;
					time1=System.currentTimeMillis();
					frame.showImage(grabbedImage);
					time2=System.currentTimeMillis();
					System.out.println("framerate = "+(int)(1/(((time2-time1)/1000)%60))+ " fps");
					 
					 textField.setText("framerate = "+(int)(1/(((time2-time1)/1000)%60))+ " fps");
					 System.out.flush();
				}
				
					}
			};
			webcam.start();
				
			}
		});
		
		
		btnNewButton_1.setBounds(252, 351, 117, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		String[] camera = {"cam1","cam2","cam3"};
		JComboBox comboBox = new JComboBox(camera);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JComboBox cb = (JComboBox)e.getSource();
			        String camstr = (String)cb.getSelectedItem();
			       
			        if(camstr=="cam1") count=0;
			        if(camstr=="cam2") count=1;
			        if(camstr=="cam3") count=2;
			        
				Thread webcam = new Thread() {
					public void run() {
				CvCapture capture = opencv_highgui.cvCreateCameraCapture(count);
				opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 300);
				opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 300);
				IplImage grabbedImage = opencv_highgui.cvQueryFrame(capture);
				CanvasFrame frame = new CanvasFrame("Akash Webcam");
				CvFont * font =new CvFont;
				while(frame.isVisible()&& (grabbedImage=opencv_highgui.cvQueryFrame(capture))!=null)
				{	double time1,time2;
					time1=System.currentTimeMillis();
					frame.showImage(grabbedImage);
					time2=System.currentTimeMillis();
					 System.out.flush();
					 textField.setText("framerate = "+(int)(1/(((time2-time1)/1000)%60))+ " fps");
				}
				
					}
			};
			webcam.start();
			}
		});
	
		
		comboBox.setBounds(164, 281, 117, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(92, 216, 210, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnThisApplicationIs = new JTextPane();
		txtpnThisApplicationIs.setEditable(false);
		txtpnThisApplicationIs.setText("This Application is made on demand of Internship Assignment. ");
		txtpnThisApplicationIs.setBounds(52, 43, 278, 63);
		frame.getContentPane().add(txtpnThisApplicationIs);
		
		JTextPane txtpnFrameRate = new JTextPane();
		txtpnFrameRate.setEditable(false);
		txtpnFrameRate.setText("Frame Rate");
		txtpnFrameRate.setBounds(20, 216, 62, 20);
		frame.getContentPane().add(txtpnFrameRate);
		
		JTextPane txtpnSelectCamera = new JTextPane();
		txtpnSelectCamera.setText("Select Camera");
		txtpnSelectCamera.setBounds(75, 281, 75, 20);
		frame.getContentPane().add(txtpnSelectCamera);
		
		
		
	
	
	}
}

